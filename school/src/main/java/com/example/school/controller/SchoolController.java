package com.example.school.controller;

import com.example.school.dto.StudentDTO;
import com.example.school.entity.StudentEntity;
import com.example.school.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class SchoolController {

    @GetMapping("/hello")
    public String getInfo(){
        return "Hello From School";
    }

    @Autowired
    private StudentService studentService;

    @PostMapping()
    public ResponseEntity<?> createStudent(@RequestBody StudentDTO stuDTO){
        return new ResponseEntity<StudentDTO>(studentService.createStudent(stuDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> readStudentById(@PathVariable("id") Long id){

        return new ResponseEntity<StudentDTO>(studentService.readStudentById(id), HttpStatus.OK);
    }

    @PatchMapping
    public ResponseEntity<?> updateStudent(@RequestBody StudentDTO stuDTO){
        // should we check if id exists?
        if (studentService.updateStudent(stuDTO))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        return new ResponseEntity<StudentDTO>(stuDTO, HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteStudentByID(@PathVariable("id") Long id){
        return new ResponseEntity<StudentDTO>(studentService.deleteStudentById(id), HttpStatus.OK);
    }
}
