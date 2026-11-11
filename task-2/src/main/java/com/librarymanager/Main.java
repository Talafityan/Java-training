package com.librarymanager;
import java.sql.SQLException;
import java.util.ArrayList;

public class Main {

    public static ArrayList<Book> getAllBooks() throws SQLException {
        BookRepository repository = new JdbcBookRepository();

        return new ArrayList<>(repository.findAll());
    }

    public static void main(String[] args) {

        BookRepository repository = new JdbcBookRepository();
        BookService service = new BookService(repository);

        try {
            ArrayList<Book> books = getAllBooks();

            books.forEach(System.out::println);

            Book newBook = new Book(null, "Clean Code", "Robert Martin", 2008, true);
            Book savedBook = repository.save(newBook);
            System.out.println(savedBook);

            System.out.println(service.getById(savedBook.id()));

            Book updatedBook = new Book(savedBook.id(), "Java Programming", "Robert Martin", 2020, true);
            System.out.println(service.update(savedBook.id(), updatedBook));

            service.searchByAuthor("robert martin").forEach(System.out::println);

            service.getAvailableSortedByYear().forEach(System.out::println);

            System.out.println(service.countBooksByAuthor());

            service.delete(savedBook.id());

            service.getById(-1L);

        } catch (BookNotFoundException e) {
            System.out.println(e.getMessage());

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
