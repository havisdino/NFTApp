package hust.ite6.group11.post;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Post {
    private final String content;
    private String title;
    private final String url;
    private List<String> tags;

    public Post(String content, String url) {
        tags = new ArrayList<>();
        this.url = url;
        this.content = content;
    }

    public Post(String title, String content, String url) {
        this(content, url);
        this.title = title;
    }

    public void addTags(String ...tags) {
        Collections.addAll(this.tags, tags);
    }

    public List<String> getTags() {
        return tags;
    }

    public String getContent() {
        return content;
    }

    public String getUrl() {
        return url;
    }
}
