package hust.ite6.group11.main.utils;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import hust.ite6.group11.main.exceptions.NullConfigException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Config {
    private static Config INSTANCE = null;

    private final transient String APPDATA_DIRECTORY;
    private final transient String CONFIG_PATH;
    private final transient String DATABASE_PATH;
    private final transient String CRAWLED_DIRECTORY;
    private String TWITTER_USERNAME;
    private String TWITTER_PASSWORD;
    private String CHROME_PATH;
    private String CHROME_DRIVER_PATH;

    {
        String OS = (System.getProperty("os.name")).toUpperCase();
        if (OS.contains("WIN")) {
            APPDATA_DIRECTORY = System.getenv("AppData") + "/NFTApp/";
        } else {
            APPDATA_DIRECTORY = System.getProperty("user.home") + "/Library/Application Support/NFTApp/";
        }

        CONFIG_PATH = APPDATA_DIRECTORY + "config/config.json";
        CRAWLED_DIRECTORY = APPDATA_DIRECTORY + "crawled/";
        DATABASE_PATH = APPDATA_DIRECTORY + "database/nft.db";

        File file = new File(APPDATA_DIRECTORY);
        if (!file.exists()) {
            initializeAppDataDirectories();
        }
    }

    private Config() {}

    private static Config fromJson(String path) throws IOException {
        Gson gson = new Gson();
        JsonReader reader = new JsonReader(new FileReader(path));

        Config config = gson.fromJson(reader, Config.class);
        return config;
    }

    public static Config getInstance() throws IOException, NullConfigException {
        if (Config.INSTANCE == null) {
            try {
                INSTANCE = new Config();
                INSTANCE = Config.fromJson(INSTANCE.getConfigPath());
                if (INSTANCE.getTwitterPassword() == null
                        || INSTANCE.getTwitterUsername() == null
                        || INSTANCE.getChromeDriverPath() == null
                        || INSTANCE.getChromePath() == null) {
                    throw new NullConfigException("Null configuration found");
                }
            } catch (IOException e) {
                throw new IOException("No configuration found");
            }
        }
        return Config.INSTANCE;
    }

    private void initializeAppDataDirectories() {
        new File(APPDATA_DIRECTORY).mkdir();
        new File(CRAWLED_DIRECTORY).mkdir();
        new File(DATABASE_PATH).getParentFile().mkdir();
        new File(CONFIG_PATH).getParentFile().mkdir();
    }

    public String getDatabasePath() {
        return DATABASE_PATH;
    }

    public String getConfigPath() {
        return CONFIG_PATH;
    }

    public String getCrawledDirectory() {
        return CRAWLED_DIRECTORY;
    }

    public String getTwitterUsername() {
        return TWITTER_USERNAME;
    }

    public String getTwitterPassword() {
        return TWITTER_PASSWORD;
    }

    public String getChromePath() {
        return CHROME_PATH;
    }

    public String getChromeDriverPath() {
        return CHROME_DRIVER_PATH;
    }
}
