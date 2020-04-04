package pl.honestit.coderslab.workshops.spring_mvc_rest.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.honestit.coderslab.workshops.spring_mvc_rest.model.Book;
import pl.honestit.coderslab.workshops.spring_mvc_rest.services.BookService;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


}
