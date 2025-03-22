package com.dct.library_managment_system.service;

import com.dct.library_managment_system.entity.BorrowBook;
import com.dct.library_managment_system.entity.Fine;

import java.util.List;

public interface FineService {

    public Fine paidFine(Long fineId , double payment);
    public Fine createFine(BorrowBook record, double fineAmount);

    List<Fine> fetchAllFine();

    Fine fetchFineById(long id);
}
