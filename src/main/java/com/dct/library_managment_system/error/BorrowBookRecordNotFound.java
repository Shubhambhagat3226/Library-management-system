package com.dct.library_managment_system.error;

public class BorrowBookRecordNotFound extends RuntimeException {
    public BorrowBookRecordNotFound(String message) {
        super(message);
    }
}
