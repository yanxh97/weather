package com.example.school.repository;

import com.example.school.dto.StudentDTO;
import com.example.school.entity.StudentEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface StudentRepo {
    public StudentDTO createStudent(StudentDTO stuDTO);
    public StudentDTO readStudentById(Long id);
    public boolean updateStudent(StudentDTO stuDTO);
    public StudentDTO deleteStudentById(Long id);
    public List<StudentEntity> readAllStudent();
}
