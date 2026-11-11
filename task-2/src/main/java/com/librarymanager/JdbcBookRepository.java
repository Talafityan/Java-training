
package com.librarymanager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcBookRepository implements BookRepository {

    @Override
    public Book save(Book book) throws SQLException {

        if (book.id() == null) {
            String sql = "INSERT INTO books (title, author, year, available) VALUES (?, ?, ?, ?) RETURNING id";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, book.title());
                statement.setString(2, book.author());
                statement.setInt(3, book.year());
                statement.setBoolean(4, book.available());

                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return new Book(
                                resultSet.getLong("id"),
                                book.title(),
                                book.author(),
                                book.year(),
                                book.available()
                        );
                    }
                }
            }
        } else {
            String sql = "UPDATE books SET title = ?, author = ?, year = ?, available = ? WHERE id = ?";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement = connection.prepareStatement(sql)) {

                statement.setString(1, book.title());
                statement.setString(2, book.author());
                statement.setInt(3, book.year());
                statement.setBoolean(4, book.available());
                statement.setLong(5, book.id());

                statement.executeUpdate();
                return book;
            }
        }

        return null;
    }

    @Override
    public List<Book> findAll() throws SQLException {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM books";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                books.add(mapRow(resultSet));
            }
        }

        return books;
    }

    @Override
    public Optional<Book> findById(Long id) throws SQLException {
        String sql = "SELECT * FROM books WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return Optional.of(mapRow(resultSet));
                }
            }
        }

        return Optional.empty();
    }

    @Override
    public void deleteById(Long id) throws SQLException {
        String sql = "DELETE FROM books WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            statement.executeUpdate();
        }
    }

    private Book mapRow(ResultSet resultSet) throws SQLException {
        return new Book(
                resultSet.getLong("id"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getInt("year"),
                resultSet.getBoolean("available")
        );
    }
}
