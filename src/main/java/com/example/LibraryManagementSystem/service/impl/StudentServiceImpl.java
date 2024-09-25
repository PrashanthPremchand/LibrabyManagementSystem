package com.example.LibraryManagementSystem.service.impl;

import com.example.LibraryManagementSystem.dto.requestdto.StudentRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentAgeRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentEmailRequestDto;
import com.example.LibraryManagementSystem.dto.requestdto.UpdateStudentMobilNoRequestDto;
import com.example.LibraryManagementSystem.dto.responsedto.*;
import com.example.LibraryManagementSystem.entity.Card;
import com.example.LibraryManagementSystem.entity.Student;
import com.example.LibraryManagementSystem.enums.CardStatus;
import com.example.LibraryManagementSystem.enums.Gender;
import com.example.LibraryManagementSystem.expections.StudentNotFoundException;
import com.example.LibraryManagementSystem.repository.StudentRepository;
import com.example.LibraryManagementSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentRepository studentRepository;

    @Override
    public String addStudent(StudentRequestDto studentRequestDto) {
        Student student = new Student();
        student.setName(studentRequestDto.getName());
        student.setAge(studentRequestDto.getAge());
        student.setGender(studentRequestDto.getGender());
        student.setDepartment(studentRequestDto.getDepartment());
        student.setMobileNo(studentRequestDto.getMobileNo());
        student.setEmail(studentRequestDto.getEmail());
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

    @Override
    public UpdateStudentAgeResponseDto updateAge(UpdateStudentAgeRequestDto updateStudentAgeRequestDto) throws StudentNotFoundException {
        try{
            Student student = studentRepository.findById(updateStudentAgeRequestDto.getId()).get();
            student.setAge(updateStudentAgeRequestDto.getAge());
            Student updatedStudent = studentRepository.save(student);
            //prepare response dto
            UpdateStudentAgeResponseDto updateStudentAgeResponseDto = new UpdateStudentAgeResponseDto();
            updateStudentAgeResponseDto.setName(updatedStudent.getName());
            updateStudentAgeResponseDto.setAge(updatedStudent.getAge());
            return updateStudentAgeResponseDto;
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid student ID");
        }

    }

    @Override
    public UpdateStudentEmailResponseDto updateEmail(UpdateStudentEmailRequestDto updateStudentEmailRequestDto) throws StudentNotFoundException {
        try{
            Student student = studentRepository.findById(updateStudentEmailRequestDto.getId()).get();
            student.setEmail(updateStudentEmailRequestDto.getEmail());
            Student updatedStudent = studentRepository.save(student);
            //prepare response dto
            UpdateStudentEmailResponseDto updateStudentEmailResponseDto = new UpdateStudentEmailResponseDto();
            updateStudentEmailResponseDto.setName(updatedStudent.getName());
            updateStudentEmailResponseDto.setEmail(updatedStudent.getEmail());
            return updateStudentEmailResponseDto;
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid student ID");
        }
    }

    @Override
    public FindStudentByIdResponseDto getStudent(int id) throws StudentNotFoundException {
        Student student;
        try{
            student = studentRepository.findById(id).get();
        }
        catch (Exception e){
            throw new StudentNotFoundException("Invalid student ID");
        }

        //prepare response dto
        FindStudentByIdResponseDto findStudentByIdResponseDto = new FindStudentByIdResponseDto();
        findStudentByIdResponseDto.setName(student.getName());
        findStudentByIdResponseDto.setMobileNo(student.getMobileNo());

        return findStudentByIdResponseDto;
    }

    @Override
    public FindAllMaleStudentsResponseDto getMaleStudents() {
        List<Student> maleStudents = studentRepository.findByGender(Gender.MALE);
        //prepare response dto
        FindAllMaleStudentsResponseDto findAllMaleStudentsResponseDto = new FindAllMaleStudentsResponseDto();
        List<String> names = new ArrayList<>();
        for (Student currentStudent : maleStudents) {
            names.add(currentStudent.getName());
        }
        findAllMaleStudentsResponseDto.setNames(names);

        return findAllMaleStudentsResponseDto;
    }

    @Override
    public FindAllFemaleStudentsResponseDto getFemaleStudents() {
        List<Student> femaleStudents = studentRepository.findByGender(Gender.FEMALE);
        //prepare response dto
        FindAllFemaleStudentsResponseDto findAllFemaleStudentsResponseDto = new FindAllFemaleStudentsResponseDto();
        List<String> names = new ArrayList<>();
        for(Student currentStudent : femaleStudents){
            names.add(currentStudent.getName());
        }
        findAllFemaleStudentsResponseDto.setNames(names);

        return findAllFemaleStudentsResponseDto;
    }

    @Override
    public List<FindAllStudentsResponseDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        //prepare response dto
        List<FindAllStudentsResponseDto> findAllStudentsResponseDtoList = new ArrayList<>();
        for(Student currentStudent : students){
            FindAllStudentsResponseDto findAllStudentsResponseDto = new FindAllStudentsResponseDto();
            findAllStudentsResponseDto.setName(currentStudent.getName());
            findAllStudentsResponseDto.setGender(currentStudent.getGender());
            findAllStudentsResponseDto.setMobileNo(currentStudent.getMobileNo());
            findAllStudentsResponseDtoList.add(findAllStudentsResponseDto);
        }

        return findAllStudentsResponseDtoList;
    }

    @Override
    public String deleteStudent(int id) throws StudentNotFoundException {
        if(studentRepository.existsById(id)){
            studentRepository.deleteById(id);
            return "Student successfully deleted";
        } else {
            throw new StudentNotFoundException("Invalid student ID");
        }
    }

    @Override
    public String deteleAll() {
        studentRepository.deleteAll();
        return "All students have been deleted";
    }
}
