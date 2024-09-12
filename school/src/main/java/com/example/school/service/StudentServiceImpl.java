package com.example.school.service;

import com.example.school.dto.StudentDTO;
import com.example.school.entity.StudentEntity;
import com.example.school.repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StudentServiceImpl implements StudentService{
    @Autowired
    private StudentRepo studentRepository;

    @Override
    @Transactional
    public StudentDTO createStudent(StudentDTO stuDTO) {
        return studentRepository.createStudent(stuDTO);
    }

    @Override
    @Transactional
    public StudentDTO readStudentById(Long id) {
        return studentRepository.readStudentById(id);
    }

    @Override
    @Transactional
    public boolean updateStudent(StudentDTO stuDTO) {
        return studentRepository.updateStudent(stuDTO);
    }

    @Override
    @Transactional
    public StudentDTO deleteStudentById(Long id) {
        return studentRepository.deleteStudentById(id);
    }

    @Override
    @Transactional
    public List<StudentEntity> readAllStudent() {
        List<StudentEntity> res = studentRepository.readAllStudent();
        return res;
    }
}
