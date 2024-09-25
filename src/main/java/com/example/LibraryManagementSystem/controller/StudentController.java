package com.example.LibraryManagementSystem.controller;

import com.example.LibraryManagementSystem.dto.requestdto.StudentRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentAgeRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentEmailRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentMobilNoRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.*;
import com.example.LibraryManagementSystem.entity.Student;
import com.example.LibraryManagementSystem.expections.StudentNotFoundException;
import com.example.LibraryManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PutMapping("/update_age")
    public UpdateStudentAgeResponseDto updateAge(@RequestBody UpdateStudentAgeRequestDto updateStudentAgeRequestDto) throws StudentNotFoundException {
        return studentService.updateAge(updateStudentAgeRequestDto);
    }
    @PutMapping("/update_email")
    public UpdateStudentEmailResponseDto updateEmail(@RequestBody UpdateStudentEmailRequestDto updateStudentEmailRequestDto) throws StudentNotFoundException {
        return studentService.updateEmail(updateStudentEmailRequestDto);
    }

    @GetMapping("/get_student")
    public FindStudentByIdResponseDto getStudent(@RequestParam("id") int id) throws StudentNotFoundException {
        return studentService.getStudent(id);
    }
    @GetMapping("/get_male_students")
    public FindAllMaleStudentsResponseDto getMaleStudents(){
        return studentService.getMaleStudents();
    }
    @GetMapping("/get_female_students")
    public FindAllFemaleStudentsResponseDto getFemaleStudents(){
        return studentService.getFemaleStudents();
    }
    @GetMapping("/get_students")
    public List<FindAllStudentsResponseDto> getAllStudents(){
        return studentService.getAllStudents();
    }

    @DeleteMapping("/delete_student")
    public String deleteStudent (@RequestParam("id") int id) throws StudentNotFoundException {
        return studentService.deleteStudent(id);
    }
    @DeleteMapping("/delete_all")
    public String deleteAll(){
        return studentService.deteleAll();
    }
}
