package com.example.LibraryManagementSystem.dto.responsedto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FindAuthorWithMoreThanNumberOfBooks {
    private List<String> names = new ArrayList<>();
}
