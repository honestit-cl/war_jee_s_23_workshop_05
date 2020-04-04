package pl.honestit.coderslab.workshops.spring_mvc_rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.honestit.coderslab.workshops.spring_mvc_rest.exceptions.ResourceNotFoundException;
import pl.honestit.coderslab.workshops.spring_mvc_rest.model.Book;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @GetMapping(value = "/hello", produces = "application/json;charset=UTF-8")
    public String sayHelloInJSON() {
        return "{ \"message\": \"Hello, world\"}";
    }

    @GetMapping("/hello-book")
    public Book showBookInJSON() {
        Book sampleBook = new Book(1L, "898989:909090", "Książka o JSONach", "CodersLab", "CodersLab", "PDF");
        return sampleBook;
    }

    @GetMapping("/{bookId:\\d+}")
    public Book get(@PathVariable Long bookId) {
        if (bookId % 2 == 0) {
            throw new ResourceNotFoundException("Nie ma książki o id: " +bookId);
        }
        return new Book(bookId, "898989:909090", "Książka o JSONach", "CodersLab", "CodersLab", "PDF");
    }

    @GetMapping("/wiecej-kontroli/{bookId:\\d+")
    public ResponseEntity<?> getWithMoreControl(@PathVariable Long bookId) {
        if (bookId % 2 == 0) {
            return ResponseEntity.notFound().build();
        }
        else {
            Book book = new Book(bookId, null, "Książka o JSONach", "CodersLab", "CodersLab", "PDF");
            return ResponseEntity.ok(book);
        }
    }

    @GetMapping("/hello-books")
    public List<Book> showTestBooks() {
        return Arrays.asList(
                new Book(1L, "898989:909090", "Książka o JSONach", "CodersLab", "CodersLab", "PDF"),
                new Book(2L, null, "Książka o JSONach", "CodersLab", "CodersLab", "PDF")
        );
    }
}
