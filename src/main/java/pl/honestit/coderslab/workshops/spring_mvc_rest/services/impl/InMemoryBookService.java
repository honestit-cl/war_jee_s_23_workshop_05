package pl.honestit.coderslab.workshops.spring_mvc_rest.services.impl;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.honestit.coderslab.workshops.spring_mvc_rest.exceptions.ResourceNotFoundException;
import pl.honestit.coderslab.workshops.spring_mvc_rest.model.Book;
import pl.honestit.coderslab.workshops.spring_mvc_rest.services.BookService;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@Primary
public class InMemoryBookService implements BookService {

    private final List<Book> list;

    public InMemoryBookService() {
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
    }

    public List<Book> getList() {
        return list;
    }

    public Book getBookById(Long id) {
        return list.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
    }

    public void addBook(Book added) {
        list.stream()
                .map(Book::getId)
                .sorted((Comparator<Long>) Comparator.naturalOrder().reversed())
                .limit(1)
                .map(id -> id + 1)
                .map(nextId -> { added.setId(nextId); return added;})
                .forEach(list::add);
    }

    public void updateBook(Book updated) {
        list.stream()
                .filter(book -> book.getId().equals(updated.getId()))
                .findFirst()
                .map(book -> updated)
                .ifPresentOrElse(list::add, ResourceNotFoundException::new);
    }

    public void deleteBook(Long id) {
        list.stream()
                .filter(book -> book.getId().equals(id))
                .findFirst()
                .ifPresentOrElse(list::remove, ResourceNotFoundException::new);
    }
}
