package com.dct.library_managment_system.service.impl;

import com.dct.library_managment_system.entity.Books;
import com.dct.library_managment_system.error.BookNotFound;
import com.dct.library_managment_system.repository.BooksRepository;
import com.dct.library_managment_system.service.BookService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private final BooksRepository bookRepository;

    public BookServiceImpl(BooksRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//    public Books addBookWithCategories(Books book, List<String> categoryNames) {
//        List<Category> categories = new ArrayList<>();
//
//        for (String name : categoryNames) {
//            Category category = categoryRepository
//                    .findByName(name)
//                    .orElseGet(() -> Category
//                            .builder()
//                            .name(name)
//                            .books(new ArrayList<>())
//                            .build()
//                    );
//            category.getBooks().add(book);
//            categories.add(category);
//        }
//
//        book.setCategories(categories);
//        return bookRepository.save(book);
//    }

    @Override
    public List<Books> fetchAllBooks() {
        return bookRepository.findAll();
    }

    @Override
    public Books fetchBookByIsbn(String isbn) throws BookNotFound{

        if (!bookRepository.existsByIsbn(isbn)) {
            throw new BookNotFound("Isbn_no. not found");
        }
        return bookRepository.findByIsbn(isbn);
    }

    @Override
    public List<Books> fetchBookByTitle(String title) throws BookNotFound{
        System.out.println("title>>>>"+ title);
        if (!bookRepository.existsByTitleContaining(title)) {
            throw new BookNotFound("Title not found");
        }
        return bookRepository.findByTitleLike(title);
    }

    @Override
    public boolean deleteBookById(long id) {
        if (!bookRepository.existsById(id)) {
            return false;
        } else {
            bookRepository.deleteById(id);
            return true;
        }
    }

    @Override
    public Books updateBookById(long id, Books newBook) {
        if (!bookRepository.existsById(id)) {
            throw new BookNotFound("Book-id not found");
        }

        Books book = bookRepository.findById(id).get();

        /* book title */
        if (newBook.getTitle() != null &&
                !newBook.getTitle().isBlank() &&
                !newBook.getTitle().equalsIgnoreCase(book.getTitle())
        ) {
            book.setTitle(newBook.getTitle());
        }

        /* book author */
        if (newBook.getAuthor() != null &&
                !newBook.getAuthor().isBlank() &&
                !newBook.getAuthor().equalsIgnoreCase(book.getAuthor())
        ) {
            book.setAuthor(newBook.getAuthor());
        }

        /* book description */
        if (newBook.getDescription() != null &&
                !newBook.getDescription().isBlank() &&
                !newBook.getDescription().equalsIgnoreCase(book.getDescription())
        ) {
            book.setDescription(newBook.getDescription());
        }

        /* book total-copies */
        if (!(newBook.getTotalCopies() <= 0)) {
            book.setTotalCopies(newBook.getTotalCopies());
        }

        /* book available-copies */
        if (!(newBook.getAvailableCopies() <= 0)) {
            book.setAvailableCopies(newBook.getAvailableCopies());
        }

        /* book shelf-no */
        if (newBook.getShelfNumber() <= 0) {
            book.setShelfNumber(newBook.getShelfNumber());
        }

        return bookRepository.save(book);

    }

    @Override
    public Books addBook(@Valid Books book) {
        return bookRepository.save(book);
    }
}
