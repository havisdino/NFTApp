package hust.ite6.group11.test;

import hust.ite6.group11.main.fetcher.Fetcher;
import hust.ite6.group11.main.scraper.ScraperType;

public class FetcherTest {
    public static void main(String[] args) throws Exception {
        Fetcher fetcher = new Fetcher(ScraperType.AIRNTFS, ScraperType.AIRNTFS);
        try {
            fetcher.fetch();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(2);
        }
    }
}
