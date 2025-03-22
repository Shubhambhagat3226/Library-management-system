package com.dct.library_managment_system.repository;

import com.dct.library_managment_system.entity.Books;
import com.dct.library_managment_system.entity.BorrowBook;
import com.dct.library_managment_system.entity.Members;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BorrowBookRepository extends JpaRepository<BorrowBook, Long> {
    boolean existsByMember(Members member);

    BorrowBook findByMember(Members member);

    Object findAllByMember(Members member);

    Optional<BorrowBook> findFirstByBookAndMember(Books book, Members member);

    List<BorrowBook> findAllByBookAndMember(Books book, Members member);
}
