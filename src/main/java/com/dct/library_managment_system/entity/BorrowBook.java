package com.dct.library_managment_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "fine")
public class BorrowBook {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "book_id", nullable = false,unique = false)
    private Books book;

    @ManyToOne
    @JoinColumn(name = "member_id", nullable = false, unique = false)
    private Members member;


    private LocalDate borrowDate;
    private LocalDate dueDate;
    private LocalDate returnDate;


    @OneToOne(mappedBy = "record")
    @Transient
    @JsonIgnore
    private Fine fine;
}
