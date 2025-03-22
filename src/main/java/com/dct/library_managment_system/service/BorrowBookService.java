package com.dct.library_managment_system.service;

import com.dct.library_managment_system.dto.BorrowRequest;
import com.dct.library_managment_system.entity.BorrowBook;

import java.util.List;

public interface BorrowBookService {
    BorrowBook createBorrowRecord(BorrowRequest request);

    List<BorrowBook> fetchAllRecord();

    void deleteRecordById(Long recordId);

    void returnedBorrowBook(BorrowRequest request);
}
