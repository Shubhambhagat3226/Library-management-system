package com.dct.library_managment_system.service.impl;

import com.dct.library_managment_system.dto.BorrowRequest;
import com.dct.library_managment_system.entity.Books;
import com.dct.library_managment_system.entity.BorrowBook;
import com.dct.library_managment_system.entity.Fine;
import com.dct.library_managment_system.entity.Members;
import com.dct.library_managment_system.error.BookNotFound;
import com.dct.library_managment_system.error.BorrowBookRecordNotFound;
import com.dct.library_managment_system.error.MemberNotFound;
import com.dct.library_managment_system.repository.BooksRepository;
import com.dct.library_managment_system.repository.BorrowBookRepository;
import com.dct.library_managment_system.repository.MembersRepository;
import com.dct.library_managment_system.service.BorrowBookService;
import com.dct.library_managment_system.service.FineService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

@Service
public class BorrowBookServiceImpl implements BorrowBookService {

    private final BooksRepository booksRepo;
    private final MembersRepository membersRepo;
    private final BorrowBookRepository borrowBookRepo;

    private final FineService fineService;

    public BorrowBookServiceImpl(BooksRepository booksRep, MembersRepository membersRepo, BorrowBookRepository borrowBookRepo, FineService fineService) {
        this.booksRepo = booksRep;
        this.membersRepo = membersRepo;
        this.borrowBookRepo = borrowBookRepo;
        this.fineService = fineService;
    }

    @Override
    public BorrowBook createBorrowRecord(BorrowRequest request) {
        BorrowBook borrowBook = new BorrowBook();

        // Map books by IDs
        Books book = booksRepo.findById(request.getBookId())
                        .orElseThrow(() -> new BookNotFound("Book not found: " + request.getBookId()));



        // Map member by ID
        Members member = membersRepo.findById(request.getMemberId())
                .orElseThrow(() -> new MemberNotFound("Member not found: " + request.getMemberId()));


        if (book.getAvailableCopies() <= 0) {
            throw new BookNotFound("No available copies for this book.");
        }

        // Reduce available copies
        book.setAvailableCopies(book.getAvailableCopies() - 1);
        borrowBook.setBook(book);
        borrowBook.setMember(member);

        // Set dates
        borrowBook.setBorrowDate(LocalDate.now());
        borrowBook.setDueDate(LocalDate.now().plusDays(-10));

        return borrowBookRepo.save(borrowBook);
    }

    @Override
    public List<BorrowBook> fetchAllRecord() {
        return borrowBookRepo.findAll();
    }

    @Transactional
    public void deleteRecordById(Long id) {
        BorrowBook borrowBook = borrowBookRepo.findById(id)
                .orElseThrow(() -> new BorrowBookRecordNotFound("Record not found"));

        // Now delete the BorrowBook record
        borrowBookRepo.deleteById(id);
    }

    @Override
    public void returnedBorrowBook(BorrowRequest request) throws BorrowBookRecordNotFound{
        Books book = booksRepo.findById(request.getBookId())
                .orElseThrow(() -> {throw new BookNotFound("Book id not found");});
        Members member = membersRepo.findById(request.getMemberId())
                .orElseThrow(() -> {throw new MemberNotFound("Member-id not found");});

        List<BorrowBook> borrowBook = borrowBookRepo.findAllByBookAndMember(book, member);

        if (borrowBook.isEmpty()) {
            throw new BorrowBookRecordNotFound("There is no record of this borrow");
        }

        for (BorrowBook borrow : borrowBook) {
            if (borrow.getReturnDate() == null) {
                borrow.setReturnDate(LocalDate.now());

                Period remainingDays = Period.between(borrow.getReturnDate(), borrow.getDueDate());
                if (remainingDays.isNegative()) {
                    int days = Math.abs(remainingDays.getDays());
                    int fineAmount = days * 10;
                    Fine fine = fineService.createFine(borrow, fineAmount);
                    borrow.setFine(fine);
                }

                book.setAvailableCopies(book.getAvailableCopies() + 1);
                booksRepo.save(book);

                borrowBookRepo.save(borrow);
                return;
            }
        }

        throw new BorrowBookRecordNotFound("This book is already returned");

    }
}
