package com.dct.library_managment_system.service.impl;

import com.dct.library_managment_system.entity.BorrowBook;
import com.dct.library_managment_system.entity.Fine;
import com.dct.library_managment_system.error.FineNotFound;
import com.dct.library_managment_system.repository.BorrowBookRepository;
import com.dct.library_managment_system.repository.FineRepository;
import com.dct.library_managment_system.service.FineService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FineServiceImpl implements FineService {

    private final FineRepository fineRepository;
    private final BorrowBookRepository borrowBookRepository;


    public FineServiceImpl(FineRepository fineRepository, BorrowBookRepository borrowBookRepository) {
        this.fineRepository = fineRepository;
        this.borrowBookRepository = borrowBookRepository;
    }


    public Fine createFine(BorrowBook record, double fineAmount) {
        Fine fine = Fine.builder()
                .amount(fineAmount)
                .record(record)
                .isPaid(false)
                .build();

        return fineRepository.save(fine);
    }

    @Override
    public List<Fine> fetchAllFine() {
        return fineRepository.findAll();
    }

    @Override
    public Fine fetchFineById(long id) {
        return fineRepository.findById(id).orElseThrow(() -> {
            throw new FineNotFound("Fine-id not found: " + id);
        });
    }

    public Fine paidFine(Long fineId ,double payment) throws FineNotFound {
        Fine fine = fineRepository.findById(fineId).orElseThrow(() -> {
            throw new FineNotFound("Fine-id is invalid");
        });

        double amount = fine.getAmount() - payment;

        if (amount == 0) {
            fine.setAmount(0);
            fine.setPaid(true);
        } else if (amount > 0) {
            fine.setAmount(amount);
        } else {
            throw new FineNotFound("Payment amount is more then required");
        }

        return fineRepository.save(fine);

    }
}
