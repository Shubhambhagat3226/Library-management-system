package com.dct.library_managment_system.service;

import com.dct.library_managment_system.entity.Books;
import jakarta.validation.Valid;

import java.util.List;

public interface BookService {
    Books addBook(@Valid Books book);
    List<Books> fetchAllBooks();


    Books fetchBookByIsbn(String isbn);

    List<Books> fetchBookByTitle(String title);

    boolean deleteBookById(long id);

    Books updateBookById(long id, Books book);

    Books fetchBookById(Long id);
}

