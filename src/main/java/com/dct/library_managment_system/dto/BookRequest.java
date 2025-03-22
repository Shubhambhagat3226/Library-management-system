package com.dct.library_managment_system.dto;

import com.dct.library_managment_system.entity.Books;
import lombok.Data;

import java.util.List;

@Data
public class BookRequest {
    private Books book;
    private List<String> categoryNames;
}
