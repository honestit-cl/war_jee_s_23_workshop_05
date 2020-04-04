package pl.honestit.coderslab.workshops.spring_mvc_rest.services.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger log = LoggerFactory.getLogger(InMemoryBookService.class);

    private final List<Book> list;

    public InMemoryBookService() {
        log.info("Tworzenie listy książek...");
        list = new ArrayList<>();
        list.add(new Book(1L, "9788324631766", "Thinking in Java", "Bruce Eckel",
                "Helion", "programming"));
        list.add(new Book(2L, "9788324627738", "Rusz glowa, Java.",
                "Sierra Kathy, Bates Bert", "Helion", "programming"));
        list.add(new Book(3L, "9780130819338", "Java 2. Podstawy",
                "Cay Horstmann, Gary Cornell", "Helion", "programming"));
        log.info("Utworzono listę książek: {} ", list);
    }

    @Override
    public List<Book> getBooks() {
        return list;
    }

    @Override
    public Book getBookById(Long bookId) {
        log.debug("Pobieranie książki o id: {}", bookId);
        Book foundBook = list.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .orElseThrow(ResourceNotFoundException::new);
        log.debug("Znalezion książka: {}", foundBook);
        return foundBook;
    }

    @Override
    public Book addBook(Book bookToAdd) {
        log.debug("Dodawanie książki: {}", bookToAdd);
        Long nextId = list.stream()
                .map(Book::getId)
                .sorted((Comparator<Long>) Comparator.naturalOrder().reversed())
                .limit(1)
                .map(id -> id + 1)
                .findFirst().get();
        log.debug("Ustalone id dla książki: {}", nextId);
        bookToAdd.setId(nextId);
        list.add(bookToAdd);
        return bookToAdd;
    }

    @Override
    public void updateBook(Book bookToUpdated) {
        log.debug("Aktualizacja książki: {}", bookToUpdated);
        log.debug("Liczba książek przed aktualizacją: {}", list.size());
        list.stream()
                .filter(book -> book.getId().equals(bookToUpdated.getId()))
                .map(list::indexOf)
                .findFirst()
                .ifPresentOrElse(index -> list.set(index, bookToUpdated), () -> {
                    throw new ResourceNotFoundException();
                });
        log.debug("Lista książek po aktualizacji: {}", list.size());
    }

    @Override
    public void deleteBook(Long bookId) {
        log.debug("Liczba książek przed usunięciem: {}", list.size());
        list.stream()
                .filter(book -> book.getId().equals(bookId))
                .findFirst()
                .ifPresentOrElse(list::remove, () -> {
                    throw new ResourceNotFoundException();
                });
        log.debug("Liczba książek po usunięciu: {}", list.size());
    }
}
