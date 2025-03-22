package com.dct.library_managment_system.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "borrowBooks")
public class Members {

    @Id
    @SequenceGenerator(
            name = "member_Seq",
            sequenceName = "member_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "member_seq"
    )
    @Column(
            name = "member_id"
    )
    private Long id;

    @NotBlank(message = "Name is required")
    private String name;

    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$",
            message = "Email is invalid"
)
    @Column(
            unique = true,
            name = "email_id"
    )
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "phone-no is invalid!")
    @Column(name = "phone_no")
    private String phoneNumber;


    @OneToMany(mappedBy = "member")
    @JsonIgnore
    private List<BorrowBook> borrowBooks;
}
