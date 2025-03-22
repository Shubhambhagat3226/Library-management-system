package com.dct.library_managment_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.validator.constraints.UniqueElements;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(
        uniqueConstraints = @UniqueConstraint(
                name = "unique_category_name",
                columnNames = "name"
        )
)
@ToString(exclude = "books")
public class Category {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    @Column(
            name = "categories_id"
    )
    private int id;

    @Column(nullable = false, unique = true)
    private String name;


    // Many-to-Many Relationship with Books
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "book_category",
            joinColumns = @JoinColumn(name = "category_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    @JsonIgnore
    private List<Books> books;

}
