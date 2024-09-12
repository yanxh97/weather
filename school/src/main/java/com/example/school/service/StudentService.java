package com.example.school.service;

import com.example.school.dto.StudentDTO;
import com.example.school.entity.StudentEntity;
import com.example.school.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentService {
    public StudentDTO createStudent(StudentDTO stuDTO);
    public StudentDTO readStudentById(Long id);
    public boolean updateStudent(StudentDTO stuDTO);
    public StudentDTO deleteStudentById(Long id);
}
