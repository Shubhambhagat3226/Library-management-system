package com.dct.library_managment_system.contoller;

import com.dct.library_managment_system.dto.BookRequest;
import com.dct.library_managment_system.entity.Books;
import com.dct.library_managment_system.service.BookCategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookCategoryController {

    private final BookCategoryService bookService;

    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookService = bookCategoryService;
    }

    @PostMapping("/book/add")
    public ResponseEntity<Books> addBook(@RequestBody BookRequest bookRequest) {
        Books savedBook = bookService.addBookWithCategories(
                bookRequest.getBook(),
                bookRequest.getCategoryNames()
        );
        return ResponseEntity.ok(savedBook);
    }

}
