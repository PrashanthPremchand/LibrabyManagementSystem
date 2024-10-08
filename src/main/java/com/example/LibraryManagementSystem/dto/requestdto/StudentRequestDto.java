package com.example.LibraryManagementSystem.dto.requestdto;

import com.example.LibraryManagementSystem.enums.Department;
import com.example.LibraryManagementSystem.enums.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class StudentRequestDto {
    private String name;
    private int age;
    private Gender gender;
    private Department department;
    private String mobileNo;
    private String email;
}
