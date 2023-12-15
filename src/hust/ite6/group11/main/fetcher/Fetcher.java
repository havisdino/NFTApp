package hust.ite6.group11.main.fetcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import hust.ite6.group11.main.database.DatabaseHelper;
import hust.ite6.group11.main.database.SQLiteHelper;
import hust.ite6.group11.main.exceptions.IdenticalPrimaryKeyException;
import hust.ite6.group11.main.exceptions.NullConfigException;
import hust.ite6.group11.main.post.Post;
import hust.ite6.group11.main.scraper.Scraper;
import hust.ite6.group11.main.scraper.ScraperFactory;
import hust.ite6.group11.main.scraper.ScraperType;
import hust.ite6.group11.main.utils.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fetcher {
    private List<Scraper> scrapers = new ArrayList<>();

    public Fetcher(ScraperType ...types) throws IOException, NullConfigException {
        if (Arrays.stream(types).findAny().isEmpty()) {
            throw new IllegalArgumentException("Number of types must be larger than 0");
        }
        for (ScraperType type : types) {
            scrapers.add(ScraperFactory.getScraper(type));
        }
    }

    private void saveAsJson(List<Post> posts, String prefix) throws IOException, NullConfigException {
        String crawledDirectory = Config.getInstance().getCrawledDirectory();
        String saveDirectory = crawledDirectory + prefix + System.currentTimeMillis() + ".json";
        Gson gson = new GsonBuilder().create();
        FileWriter fileWriter = new FileWriter(saveDirectory, false);
        fileWriter.flush();
        gson.toJson(posts, fileWriter);
        fileWriter.close();
    }

    private void saveInDatabase(List<Post> posts) throws Exception {
        String databasePath = Config.getInstance().getDatabasePath();
        DatabaseHelper databaseHelper = new SQLiteHelper("jdbc:sqlite:" + databasePath);
        for (Post post : posts) {
            try {
                databaseHelper.insert(post);
            } catch (IdenticalPrimaryKeyException ignored) {
            } catch (Exception e) {
                System.out.println(e.getMessage());
                databaseHelper.flush();
                databaseHelper.initialize();
                databaseHelper.insert(post);
            }
        }
    }

    public void fetch() throws Exception {
        for (Scraper scraper : scrapers) {
            List<Post> posts = scraper.browse();
            String scraperName =  scraper.getClass().getSimpleName();
            String prefix = "[" + scraperName + "] ";

            System.out.println(prefix + posts.size() + " posts found");
            System.out.print(prefix + "Dumping to json ... ");
            saveAsJson(posts, scraperName);
            System.out.println("Done");
            System.out.print(prefix + "Saving into database ... ");
            saveInDatabase(posts);
            System.out.println("Done");
            scraper.close();
        }
    }
}
