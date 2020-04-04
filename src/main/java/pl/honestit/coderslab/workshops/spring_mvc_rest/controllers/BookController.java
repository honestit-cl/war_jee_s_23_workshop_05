package pl.honestit.coderslab.workshops.spring_mvc_rest.controllers;

import org.springframework.web.bind.annotation.*;
import pl.honestit.coderslab.workshops.spring_mvc_rest.model.Book;
import pl.honestit.coderslab.workshops.spring_mvc_rest.services.BookService;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getBooks() {
        return bookService.getBooks();
    }

    @GetMapping("/{bookId:\\d+}")
    public Book getById(@PathVariable Long bookId) {
        return bookService.getBookById(bookId);
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return bookService.addBook(book);
    }

    @PutMapping("/{bookId:\\d+}")
    public Book updateBook(@RequestBody Book book, @PathVariable Long bookId) {
        book.setId(bookId); // Robimy ten krok, aby id na pewno odpowiadało ścieżce
        bookService.updateBook(book);
        return book;
    }

    @DeleteMapping("/{bookId:\\d+}")
    public void deleteBook(@PathVariable Long bookId) {
        bookService.deleteBook(bookId);
    }
}
