package test;

import nftspy.exceptions.NullConfigException;
import nftspy.utils.Config;

import java.io.IOException;

public class ConfigTest {
    public static void main(String[] args) {
        try {
            Config config = Config.getInstance();
            System.out.println(config.getChromePath());
        } catch (IOException | NullConfigException e) {
            System.out.println(e.getMessage());
        }
    }
}
