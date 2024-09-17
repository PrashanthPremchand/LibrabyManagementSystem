package com.example.LibraryManagementSystem.entity;

import com.example.LibraryManagementSystem.enums.CardStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "card")
@AllArgsConstructor
@Getter
@Setter
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date issueDate;
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedOn;
    @Temporal(TemporalType.TIMESTAMP)
    private Date validTill;
    // This method will be called after the entity is persisted to set expiryDate
    @PostPersist
    public void addYearsToValidTill(){
        if(issueDate != null){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(issueDate);
            calendar.add(Calendar.YEAR, 4);
            validTill = calendar.getTime();
        }
    }
    @Enumerated(EnumType.STRING)
    private CardStatus cardStatus;
    //mandatory
    @OneToOne
    @JoinColumn
    Student student;
    public Card(){
        addYearsToValidTill();
    }
}
