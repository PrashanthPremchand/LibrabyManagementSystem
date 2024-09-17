package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.requestdto.StudentRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentMobilNoRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.UpdateStudentMobilNoResponseDto;
import com.example.LibraryManagementSystem.entity.Student;
import com.example.LibraryManagementSystem.expections.StudentNotFoundException;
import com.example.LibraryManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    StudentService studentService;
    @PostMapping("/add")
    public String addStudent(@RequestBody StudentRequestDto studentRequestDto){
        return studentService.addStudent(studentRequestDto);
    }
    @PutMapping("/update_mobile")
    public UpdateStudentMobilNoResponseDto updateMobileNo(@RequestBody UpdateStudentMobilNoRequestDto updateStudentMobilNoRequestDto) throws StudentNotFoundException {
        return studentService.updateMobileNo(updateStudentMobilNoRequestDto);
    }
}
