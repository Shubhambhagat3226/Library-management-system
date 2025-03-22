package com.dct.library_managment_system.repository;

import com.dct.library_managment_system.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

    Category findByNameIgnoreCase(String name);
}
