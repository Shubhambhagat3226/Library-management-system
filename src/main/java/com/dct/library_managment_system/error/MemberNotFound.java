package com.dct.library_managment_system.error;

public class MemberNotFound extends RuntimeException {
    public MemberNotFound(String message) {
        super(message);
    }
}
