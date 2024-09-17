package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.requestdto.StudentRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentMobilNoRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.UpdateStudentMobilNoResponseDto;
import com.example.LibraryManagementSystem.entity.Student;
import com.example.LibraryManagementSystem.expections.StudentNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

public interface StudentService {
    public String addStudent(StudentRequestDto studentRequestDto);
    public UpdateStudentMobilNoResponseDto updateMobileNo(UpdateStudentMobilNoRequestDto updateStudentMobilNoRequestDto) throws StudentNotFoundException;
}
