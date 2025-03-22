package com.dct.library_managment_system.controller;

import com.dct.library_managment_system.dto.BookRequest;
import com.dct.library_managment_system.entity.Books;
import com.dct.library_managment_system.service.BookCategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
@Tag(name = "Book-Categories API", description = "To create books data only")
public class BookCategoryController {

    private final BookCategoryService bookService;

    public BookCategoryController(BookCategoryService bookCategoryService) {
        this.bookService = bookCategoryService;
    }

    @PostMapping("/add")
    @Operation(summary = "add books detail with categories")
    public ResponseEntity<Books> addBook(@RequestBody BookRequest bookRequest) {
        Books savedBook = bookService.addBookWithCategories(
                bookRequest.getBook(),
                bookRequest.getCategoryNames()
        );
        return ResponseEntity.ok(savedBook);
    }

}
