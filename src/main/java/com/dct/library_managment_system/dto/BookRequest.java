package com.dct.library_managment_system.dto;

import com.dct.library_managment_system.entity.Books;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookRequest {
    private Books book;
    private List<String> categoryNames;
}
