package com.example.LibraryManagementSystem.dto.requestdto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateAuthorAgeRequestDto {
    private int id;
    private int age;
}
