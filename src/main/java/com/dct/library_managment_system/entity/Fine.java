package com.dct.library_managment_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Fine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fine_id")
    private long id;

    @OneToOne
    @JoinColumn(name = "id", referencedColumnName = "id", nullable = false)
    private BorrowBook record;

    @PositiveOrZero
    @Column(name = "fine_amt", nullable = false)
    private double amount;

    private boolean isPaid;
}
