package com.example.LibraryManagementSystem.entity;

import com.example.LibraryManagementSystem.enums.Department;
import com.example.LibraryManagementSystem.enums.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "student")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private Gender gender;
    @Enumerated(EnumType.STRING)
    private Department department;
    private String mobileNo;
    private String email;
    //Bidirectional mapping optional
    @OneToOne(mappedBy = "student", cascade = CascadeType.ALL)
    Card card;
}
