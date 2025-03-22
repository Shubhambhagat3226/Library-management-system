package com.dct.library_managment_system.repository;

import com.dct.library_managment_system.entity.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

    Books findByIsbn(String isbn);

    boolean existsByIsbn(String isbn);


    boolean existsByTitleContaining(String title);

    @Query(
            value = "SELECT * FROM books b WHERE b.title LIKE CONCAT('%', ?1, '%')",
            nativeQuery = true
    )
    List<Books> findByTitleLike(String title);
}
