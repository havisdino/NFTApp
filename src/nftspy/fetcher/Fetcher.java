package nftspy.fetcher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import nftspy.database.DatabaseHelper;
import nftspy.database.SQLiteHelper;
import nftspy.exceptions.IdenticalPrimaryKeyException;
import nftspy.exceptions.NullConfigException;
import nftspy.data.Post;
import nftspy.scraper.Scraper;
import nftspy.scraper.ScraperFactory;
import nftspy.scraper.ScraperType;
import nftspy.utils.Config;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Fetcher {
    private final List<Scraper> scrapers = new ArrayList<>();

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
        String savePath = crawledDirectory + prefix + System.currentTimeMillis() + ".json";
        Gson gson = new GsonBuilder().create();
        FileWriter fileWriter = new FileWriter(savePath, false);
        fileWriter.flush();
        gson.toJson(posts, fileWriter);
        fileWriter.close();
    }

    private void saveInDatabase(List<Post> posts) throws Exception {
        String databasePath = Config.DATABASE_PATH;
        DatabaseHelper databaseHelper = new SQLiteHelper(databasePath);
        try {
            databaseHelper.initialize();
        } catch (Exception ignored) {}

        for (Post post : posts) {
            try {
                databaseHelper.insert(post);
            } catch (IdenticalPrimaryKeyException e) {
                System.out.println(e.getMessage());
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
