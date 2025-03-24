package com.dct.library_managment_system.service.impl;

import com.dct.library_managment_system.entity.Books;
import com.dct.library_managment_system.entity.Category;
import com.dct.library_managment_system.repository.BooksRepository;
import com.dct.library_managment_system.repository.CategoryRepository;
import com.dct.library_managment_system.service.BookCategoryService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookCategoryServiceImpl implements BookCategoryService {

    private final BooksRepository booksRepository;

    private final CategoryRepository categoryRepository;

    public BookCategoryServiceImpl(BooksRepository booksRepository, CategoryRepository categoryRepository) {
        this.booksRepository = booksRepository;
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Books addBookWithCategories(Books book, List<String> categoryNames) {
        List<Category> categories = categoryNames.stream()
                .filter(name -> name != null && !name.trim().isEmpty()) // Avoid null or empty names
                .map(name -> {
                    Category category = categoryRepository.findByNameIgnoreCase(name);
                    if (category == null) {
                        // Optionally create the category if it doesn't exist
                        category = Category.builder()
                                .name(name)
                                .build();
                        category = categoryRepository.save(category);
                    }
                    return category;
                })
                .collect(Collectors.toList());

        book.setCategories(categories);
        return booksRepository.save(book);
    }
}
