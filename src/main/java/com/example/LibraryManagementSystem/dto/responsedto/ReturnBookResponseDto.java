package com.example.LibraryManagementSystem.dto.responsedto;

import com.example.LibraryManagementSystem.enums.TransactionStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ReturnBookResponseDto {
    private String transactionNumber;
    private TransactionStatus transactionStatus;
    private String bookName;
}
