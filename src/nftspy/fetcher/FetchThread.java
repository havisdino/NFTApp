package nftspy.fetcher;

public class FetchThread extends Thread {
    private Fetcher fetcher;

    public FetchThread(Fetcher fetcher) {
        this.fetcher = fetcher;
    }

    @Override
    public void run() {
        try {
            fetcher.fetch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
