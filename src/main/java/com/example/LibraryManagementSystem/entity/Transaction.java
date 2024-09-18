package com.example.LibraryManagementSystem.entity;

import com.example.LibraryManagementSystem.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CurrentTimestamp;

import java.util.Date;

@Entity
@Table(name="transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String transactionNumber;
    @CurrentTimestamp
    @Temporal(TemporalType.DATE)
    private Date transactionDate;
    private boolean isIssue;
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;
    @ManyToOne
    @JoinColumn
    Card card;
    @ManyToOne
    @JoinColumn
    Book book;
}
