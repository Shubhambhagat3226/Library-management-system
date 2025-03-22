package com.dct.library_managment_system.error;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(BookNotFound.class)
    public ResponseEntity<Object> bookNotFound(BookNotFound error, WebRequest request) {
        Map<String , String> errMessage = new HashMap<>();
        errMessage.put("error", error.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errMessage);
    }

    @ExceptionHandler(MemberNotFound.class)
    public ResponseEntity<Object> MemberNotFound(MemberNotFound error, WebRequest request) {
        Map<String , String> errMessage = new HashMap<>();
        errMessage.put("error", error.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errMessage);
    }

    @ExceptionHandler(BorrowBookRecordNotFound.class)
    public ResponseEntity<Object> borrowBookNotFound(BorrowBookRecordNotFound error, WebRequest request) {
        Map<String , String> errMessage = new HashMap<>();
        errMessage.put("error", error.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errMessage);
    }

    @ExceptionHandler(FineNotFound.class)
    public ResponseEntity<Object> fineErrorOrMessage(FineNotFound error, WebRequest request) {
        Map<String , String> errMessage = new HashMap<>();
        errMessage.put("error", error.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(errMessage);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> illegalArgumentException(IllegalArgumentException error) {
        Map<String , String> errMessage = new HashMap<>();
        errMessage.put("error", error.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(errMessage);
    }
}