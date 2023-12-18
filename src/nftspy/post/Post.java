package nftspy.post;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Post {
    private final String content;
    private final String title;
    private final String url;
    private List<String> tags;

    public Post(String url, String title, String content) {
        this.content = content;
        this.title = title;
        this.url = url;
        this.tags = extractHashtags(content);
    }

    public List<String> getTags() {
        return tags;
    }

    public String getContent() {
        return content;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public static List<String> extractHashtags(String input) {
        List<String> hashtags = new ArrayList<>();
        Pattern pattern = Pattern.compile("#\\w+");
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String hashtag = matcher.group().substring(1);
            hashtags.add(hashtag);
        }
        return hashtags;
    }
}
