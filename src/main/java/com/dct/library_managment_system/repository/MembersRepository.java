package com.dct.library_managment_system.repository;

import com.dct.library_managment_system.entity.Members;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MembersRepository extends JpaRepository<Members, Long> {
    Optional<List<Members>> findAllByNameContaining(String name);

    boolean existsByEmail(@Email(message = "Email should be valid") String email);
}
