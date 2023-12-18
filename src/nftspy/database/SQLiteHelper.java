package nftspy.database;

import nftspy.exceptions.IdenticalPrimaryKeyException;
import nftspy.post.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper implements DatabaseHelper {
    Connection connection;

    public SQLiteHelper(String databasePath) {
        String jdbcURL = "jdbc:sqlite:" + databasePath;
        try {
            connection = DriverManager.getConnection(jdbcURL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "CREATE TABLE Post (" +
                "url VARCHAR PRIMARY KEY NOT NULL," +
                "title VARCHAR," +
                "content VARCHAR," +
                "tags VARCHAR," +
                "time DATETIME NOT NULL DEFAULT(CURRENT_TIMESTAMP)" +
                ");";
        stmt.executeUpdate(query);
        stmt.close();
    }

    @Override
    public void flush() {
        try {
            Statement stmt = connection.createStatement();
            String query = "DROP TABLE Post;";
            stmt.executeUpdate(query);
            stmt.close();
        } catch (SQLException ignored) {}
    }

    @Override
    public void insert(Post post) throws Exception {
        String query = String.format(
                "INSERT INTO Post (url, title, content) VALUES ('%s', '%s', '%s');",
                post.getUrl().replace("'", "''"),
                post.getTitle().replace("'", "''"),
                post.getContent().replace("'", "''"));

        Statement stmt = connection.createStatement();
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            if (e.getMessage().contains("SQLITE_CONSTRAINT_PRIMARYKEY")) {
                throw new IdenticalPrimaryKeyException("Primary key constrain failed");
            }
        }

        stmt.close();
    }

    @Override
    public void delete(Post post) throws SQLException {

    }

    @Override
    public void search(String query) throws SQLException {

    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    @Override
    public List<Post> getLatestPostList(int numberOfPosts) throws SQLException {
        String query = "SELECT title, content, tags FROM Post" +
                "ORDER BY time DESC";
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        List<Post> posts = new ArrayList<>();
        int count = 1;
        while (results.next() && count <= numberOfPosts) {
            String title = results.getString("title");
            String content = results.getString("content");
            String tags = results.getString("tags");
            String url = results.getString("url");
            posts.add(new Post(title, content, url));
            count++;
        }
        stmt.close();
        return posts;
    }
}
