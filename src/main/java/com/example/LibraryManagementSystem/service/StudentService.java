package com.example.LibraryManagementSystem.service;

import com.example.LibraryManagementSystem.dto.requestdto.StudentRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentAgeRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentEmailRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentMobilNoRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.*;
import com.example.LibraryManagementSystem.entity.Student;
import com.example.LibraryManagementSystem.expections.StudentNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface StudentService {
    public String addStudent(StudentRequestDto studentRequestDto);
    public UpdateStudentMobilNoResponseDto updateMobileNo(UpdateStudentMobilNoRequestDto updateStudentMobilNoRequestDto) throws StudentNotFoundException;
    public UpdateStudentAgeResponseDto updateAge(UpdateStudentAgeRequestDto updateStudentAgeRequestDto) throws StudentNotFoundException;
    public UpdateStudentEmailResponseDto updateEmail(UpdateStudentEmailRequestDto updateStudentEmailRequestDto) throws StudentNotFoundException;
    public FindStudentByIdResponseDto getStudent(int id) throws StudentNotFoundException;
    public FindAllMaleStudentsResponseDto getMaleStudents();
    public FindAllFemaleStudentsResponseDto getFemaleStudents();
    public List<FindAllStudentsResponseDto> getAllStudents();
    public String deleteStudent(int id) throws StudentNotFoundException;
    public String deteleAll();
}
