package com.dct.library_managment_system.service;

import com.dct.library_managment_system.entity.Books;

import java.util.List;

public interface BookCategoryService {
    Books addBookWithCategories(Books book, List<String> categoryNames);
}
