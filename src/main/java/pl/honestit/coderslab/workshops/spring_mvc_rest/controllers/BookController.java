package pl.honestit.coderslab.workshops.spring_mvc_rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.honestit.coderslab.workshops.spring_mvc_rest.model.Book;

@RestController
@RequestMapping("/books")
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
}
