package com.librarymanager;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface BookRepository {

    Book save(Book book) throws SQLException;

    List<Book> findAll() throws SQLException;

    Optional<Book> findById(Long id) throws SQLException;

    void deleteById(Long id) throws SQLException;
}