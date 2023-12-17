package test;

import nftspy.fetcher.Fetcher;
import nftspy.scraper.ScraperType;

public class FetcherTest {
    public static void main(String[] args) throws Exception {
        Fetcher fetcher = new Fetcher(ScraperType.AIRNTFS);
        try {
            fetcher.fetch();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
}
