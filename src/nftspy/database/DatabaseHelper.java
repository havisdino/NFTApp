package nftspy.database;

import nftspy.data.Post;

import java.util.List;

public interface DatabaseHelper {
    void initialize() throws Exception;
    void flush() throws Exception;
    void insert(Post post) throws Exception;
    void delete(Post post) throws Exception;
    List<Post> search(String input, int numberOfPosts) throws Exception;
    List<Post> getLatestPostList(int numberOfPosts) throws Exception;
    List<String> getLatestTags(int numberOfTags) throws Exception;
    void close() throws Exception;
}
