package com.dct.library_managment_system.controller;

import com.dct.library_managment_system.entity.Books;
import com.dct.library_managment_system.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/")
    public String fetchAllBooks(Model model) {
        List<Books> books = bookService.fetchAllBooks();
        model.addAttribute("books", books);
        return "books/book_list";
    }

    @GetMapping("/add")
    public String showAddBookForm(Model model) {
        model.addAttribute("book", new Books());
        return "books/add_book";
    }

    @PostMapping("/add")
    public String createBook(@Valid @ModelAttribute("book") Books book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "books/add_book";
        }
        bookService.addBook(book);
        return "redirect:/books/";
    }

    @GetMapping("/edit/{id}")
    public String showEditBookForm(@PathVariable("id") Long id, Model model) {
        try {
            Books book = bookService.fetchBookById(id);
            model.addAttribute("book", book);
            return "books/edit_book";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "error";
        }
    }

    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable("id") Long id, @Valid @ModelAttribute("book") Books book, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "books/edit_book";
        }
        try {
            bookService.updateBookById(id, book);
            return "redirect:/books/";
        } catch (IllegalArgumentException e) {
            model.addAttribute("errorMessage", e.getMessage());
            return "books/edit_book";
        }
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Long id) {
        bookService.deleteBookById(id);
        return "redirect:/books/";
    }
}