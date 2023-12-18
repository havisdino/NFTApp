package nftspy.database;

import nftspy.post.Post;

import java.util.List;

public interface DatabaseHelper {
    void initialize() throws Exception;
    void flush() throws Exception;
    void insert(Post post) throws Exception;
    void delete(Post post) throws Exception;
    void search(String query) throws Exception;
    List<Post> getLatestPostList(int numberOfPosts) throws Exception;
    void close() throws Exception;
}
