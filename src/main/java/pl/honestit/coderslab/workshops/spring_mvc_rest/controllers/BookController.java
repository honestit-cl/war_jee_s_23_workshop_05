package pl.honestit.coderslab.workshops.spring_mvc_rest.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/books")
public class BookController {

    @GetMapping(value = "/hello", produces = "application/json;charset=UTF-8")
    public String sayHelloInJSON() {
        return "{ \"message\": \"Hello, world\"}";
    }
}
