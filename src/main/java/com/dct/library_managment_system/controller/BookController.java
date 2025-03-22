package com.dct.library_managment_system.controller;

import com.dct.library_managment_system.entity.Books;
import com.dct.library_managment_system.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/add")
    public ResponseEntity<Books> createBook(@Valid @RequestBody Books book) {
        Books savedBook = bookService.addBook(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBook);
    }

    @GetMapping("/")
    public ResponseEntity<List<Books>> fetchAllBook() {
        return ResponseEntity.ok(bookService.fetchAllBooks());
    }

    @GetMapping("/isbn")
    public ResponseEntity<Books> fetchBookByIsbn(@RequestParam String isbn) {
        return ResponseEntity.ok(bookService.fetchBookByIsbn(isbn));
    }

    @GetMapping("/title")
    public ResponseEntity<List<Books>> fetchBookByName(@RequestParam String title) {
        return ResponseEntity.ok(bookService.fetchBookByTitle(title));
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteBookById(@RequestParam long id) {
        if (bookService.deleteBookById(id)) {
            return ResponseEntity.status(HttpStatus.OK).body("Delete successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Book id is invalid");
        }
    }

    @PutMapping("/id")
    public ResponseEntity<Books> updateBookById(@RequestParam long id,
                                                @RequestBody Books book) {
        Books b = bookService.updateBookById(id, book);
        return ResponseEntity.status(HttpStatus.OK).body(b);
    }
}

