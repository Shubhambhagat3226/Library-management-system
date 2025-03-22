package com.dct.library_managment_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class BorrowRequest {

    private long bookId;
    private long memberId;

}

