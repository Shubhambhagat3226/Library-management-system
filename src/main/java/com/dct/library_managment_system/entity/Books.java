package com.dct.library_managment_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "unique_isbn",
                columnNames = "isbn"
        )
)
@ToString(exclude = "borrowRecord")
public class Books {

    @Id
    @SequenceGenerator(
            name = "book_seq",
            sequenceName = "book_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "book_seq"
    )
    @Column(
            name = "book_id"
    )
    private Long id;

    @NotBlank(message = "Book-title is required")
    private String title;

    @NotBlank(message = "Author-name is required")
    private String author;

    @NotBlank(message = "isbn is required")
    @Column(
            unique = true
    )
    private String isbn;

    @Column(
            name = "publish_year"
    )
    private LocalDate publishYear;

    @Length(
            max = 200
    )
    private String description;

    @PositiveOrZero(message = "Total-copies can not be negative")
    @Column(
            name = "total_copies"
    )
    private int totalCopies;

    @PositiveOrZero(message = "Available-copies can not be negative")
    @Column(
            name = "available_copies"
    )
    private int availableCopies;

    @Column(
            name = "shelf_no"
    )
    private int shelfNumber;


    // Many-to-Many Relationship with Category
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id")
    )
    private List<Category> categories;

    // One-To-One with BorrowBook
    @OneToMany(mappedBy = "book")
    @JsonIgnore
    @Transient
    private List<BorrowBook> borrowRecord;
}
