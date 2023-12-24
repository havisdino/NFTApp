package nftspy.database;

import nftspy.data.Post;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHelper implements DatabaseHelper {
    private Connection connection;

    public SQLiteHelper(String databasePath) {
        String jdbcURL = "jdbc:sqlite:" + databasePath;
        try {
            connection = DriverManager.getConnection(jdbcURL);
            initialize();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() throws SQLException {
        createPostTable();
        createPriceTable();
    }

    private void createPostTable() throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "CREATE TABLE IF NOT EXISTS Post (" +
                "url VARCHAR PRIMARY KEY NOT NULL," +
                "title VARCHAR," +
                "content TEXT," +
                "tags VARCHAR," +
                "time DATETIME NOT NULL DEFAULT(CURRENT_TIMESTAMP)" +
                ");";
        stmt.executeUpdate(query);
        stmt.close();
    }

    private void createPriceTable() throws SQLException {
        Statement stmt = connection.createStatement();
        String query = "CREATE TABLE IF NOT EXISTS Price (" +
                "time DATETIME PRIMARY KEY NOT NULL," +
                "price REAL NOT NULL" +
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
        String title = post.getTitle() == null? "No title" : post.getTitle();
        String tags = String.join(" ", post.getTags());
        String query = String.format(
                "INSERT INTO Post (url, title, content, tags, time) VALUES ('%s', '%s', '%s', '%s', '%s');",
                post.getUrl().replace("'", "''"),
                title.replace("'", "''"),
                post.getContent().replace("'", "''"),
                tags.replace("'", "''"),
                post.getTime().toString().replace("'", "''"));

        Statement stmt = connection.createStatement();
        try {
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        stmt.close();
    }

    @Override
    public void delete(Post post) throws SQLException {

    }

    @Override
    public List<Post> search(String input, int numberOfPosts) throws SQLException {
        String query = "SELECT url, title, content, tags FROM Post " +
                "WHERE ";
        String[] terms = input.split(" ");
        for (int i = 0; i < terms.length; i++) {
            terms[i] = "(content LIKE '%" + terms[i].replace("'", "''") + "%'";
            terms[i] += " OR title LIKE '%" + terms[i].replace("'", "''") + "%')";
        }
        String s = String.join(" AND ", terms);
        query += s + ";";
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        List<Post> posts = new ArrayList<>();
        int count = 1;
        while (results.next() && count <= numberOfPosts) {
            String title = results.getString("title");
            String content = results.getString("content");
            String tags = results.getString("tags");
            String url = results.getString("url");
            posts.add(new Post(url, title, content, tags));
            count++;
        }
        stmt.close();
        return posts;
    }

    @Override
    public void close() throws SQLException {
        connection.close();
    }

    @Override
    public List<Post> getLatestPostList(int numberOfPosts) throws SQLException {
        String query = "SELECT url, title, content, tags FROM Post " +
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
            posts.add(new Post(url, title, content, tags));
            count++;
        }
        stmt.close();
        return posts;
    }
}
