package com.example.labwork_8_v13;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BlogDAO {
    private final String jdbcURL = "jdbc:postgresql://localhost:5432/blogs";
    private final String jdbcUsername = "postgres";
    private final String jdbcPassword = "root";

    private static final String INSERT_BLOGS_SQL = "INSERT INTO blogs (title, content) VALUES (?, ?);";
    private static final String SELECT_ALL_BLOGS = "SELECT id, title, content FROM blogs";

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }

    public void insertBlog(Blog blog) throws SQLException {
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BLOGS_SQL)) {
            preparedStatement.setString(1, blog.getTitle());
            preparedStatement.setString(2, blog.getContent());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Blog> selectAllBlogs() {
        List<Blog> blogs = new ArrayList<>();
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BLOGS)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                blogs.add(new Blog(id, title, content));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return blogs;
    }
}
