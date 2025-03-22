package com.dct.library_managment_system.controller;

import com.dct.library_managment_system.dto.BorrowRequest;
import com.dct.library_managment_system.entity.BorrowBook;
import com.dct.library_managment_system.service.BorrowBookService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/borrow")
@Tag(name = "Borrowed Book Records API")
public class BorrowBookController {

    private final BorrowBookService borrowBookService;

    public BorrowBookController(BorrowBookService borrowBookService) {
        this.borrowBookService = borrowBookService;
    }

    @PostMapping("/add")
    public ResponseEntity<BorrowBook> addBorrowBookRecord(@RequestBody BorrowRequest request) {
        BorrowBook borrowBook = borrowBookService.createBorrowRecord(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(borrowBook);
    }

    @GetMapping("/")
    public ResponseEntity<List<BorrowBook>> fetchBorrowBooksRecord() {
        return ResponseEntity.ok(borrowBookService.fetchAllRecord());
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteRecordById(@RequestParam long id) {
        borrowBookService.deleteRecordById(id);

        return ResponseEntity.ok("Record Delete successfully");
    }

    @PostMapping("/return")
    public ResponseEntity<String> returnBook(@RequestBody BorrowRequest request) {
        borrowBookService.returnedBorrowBook(request);
        return ResponseEntity.ok("Book Returned");
    }
}
