package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.dto.requestdto.StudentRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentMobilNoRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.UpdateStudentMobilNoResponseDto;
import com.example.LibraryManagementSystem.entity.Card;
import com.example.LibraryManagementSystem.entity.Student;
import com.example.LibraryManagementSystem.enums.CardStatus;
import com.example.LibraryManagementSystem.expections.StudentNotFoundException;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;
    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobileNo(studentRequestDto.getMobileNo());
        //generating a new card
        Card card = new Card();
        card.setCardStatus(CardStatus.ACTIVATED);
        card.setStudent(student);
        //set the card for student
        student.setCard(card);
        studentRepository.save(student);
        return "Student added";
    }

    @Override
    public UpdateStudentMobilNoResponseDto updateMobileNo(UpdateStudentMobilNoRequestDto updateStudentMobilNoRequestDto) throws StudentNotFoundException {
        try{
            Student student = studentRepository.findById(updateStudentMobilNoRequestDto.getId()).get();
            student.setMobileNo(updateStudentMobilNoRequestDto.getMobileNo());
            Student updatedStudent = studentRepository.save(student);
            //prepare response DTO
            UpdateStudentMobilNoResponseDto updateStudentMobilNoResponseDto = new UpdateStudentMobilNoResponseDto();
            updateStudentMobilNoResponseDto.setName(updatedStudent.getName());
            updateStudentMobilNoResponseDto.setMobileNo(updatedStudent.getMobileNo());
            return updateStudentMobilNoResponseDto;
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid student ID");
        }
    }
}
