package pl.honestit.coderslab.workshops.spring_mvc_rest.services;

import pl.honestit.coderslab.workshops.spring_mvc_rest.model.Book;

import java.util.List;

public interface BookService {

    Book getBookById(Long bookId);

    Book addBook(Book bookToAdd);

    void updateBook(Book bookToUpdated);

    void deleteBook(Long bookId);

    List<Book> getBooks();
}
