package hust.ite6.group11.main.database;

import hust.ite6.group11.main.post.Post;

import java.sql.SQLException;

public interface DatabaseHelper {
    void initialize() throws SQLException;
    void flush() throws SQLException;
    void insert(Post post) throws SQLException;
    void delete(Post post) throws SQLException;
    void search(String query) throws SQLException;
    void close() throws SQLException;
}
