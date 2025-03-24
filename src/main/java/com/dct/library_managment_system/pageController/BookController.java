//package com.dct.library_managment_system.pageController;
//
//import com.dct.library_managment_system.entity.Books;
//import com.dct.library_managment_system.error.BookNotFound;
//import com.dct.library_managment_system.service.BookService;
//import jakarta.validation.Valid;
//import org.springframework.stereotype.Controller;  // Changed from RestController
//import org.springframework.ui.Model;  // Import Model
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.List;
//
//@Controller
//@RequestMapping("/books")
//public class BookController {
//
//    private final BookService bookService;
//
//    public BookController(BookService bookService) {
//        this.bookService = bookService;
//    }
//
//    @GetMapping("/add")
//    public String showAddBookForm(Model model) {
//        model.addAttribute("book", new Books()); // Add an empty book object for the form
//        return "books/add_book"; // Return the template name
//    }
//
//    @PostMapping("/add")
//    public String createBook(@Valid @ModelAttribute("book") Books book, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            // If there are validation errors, return to the form with errors
//            return "books/add_book";
//        }
//
//        bookService.addBook(book);
//        return "redirect:/books/"; // Redirect to the book list after successful creation
//    }
//
//
//    @GetMapping("/")
//    public String fetchAllBooks(Model model) {
//        List<Books> books = bookService.fetchAllBooks();
//        model.addAttribute("books", books);  // Add the list of books to the model
//        return "books/book_list"; // Return the template name
//    }
//
//    @GetMapping("/edit/{id}")
//    public String showEditBookForm(@PathVariable("id") long id, Model model) {
//        try {
//            Books book = bookService.updateBookById(id, new Books()); // Retrieve the book by ID
//            model.addAttribute("book", book); // Add the book to the model
//            return "books/edit_book"; // Return the edit form template
//        } catch (Exception e) {
//            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found", e);
//        }
//    }
//
//    @PostMapping("/update/{id}")
//    public String updateBook(@PathVariable("id") long id, @Valid @ModelAttribute("book") Books book, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            // If there are validation errors, return to the form with errors
//            return "books/edit_book";
//        }
//        bookService.updateBookById(id, book); // Update the book
//        return "redirect:/books/"; // Redirect to the book list after successful update
//    }
//
//    @GetMapping("/delete/{id}")
//    public String deleteBook(@PathVariable("id") long id) {
//        bookService.deleteBookById(id);
//        return "redirect:/books/"; // Redirect to the book list after successful deletion
//    }
//
//    @ExceptionHandler(BookNotFound.class)
//    public String handleBookNotFoundException(BookNotFound ex, Model model) {
//        model.addAttribute("errorMessage", ex.getMessage());
//        return "error";  // Return the name of your error template
//    }
//
//}