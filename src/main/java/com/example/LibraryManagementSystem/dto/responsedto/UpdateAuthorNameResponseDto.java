package com.example.LibraryManagementSystem.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UpdateAuthorNameResponseDto {
    private int id;
    private String name;
}
