package com.example.school.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "students")
public class StudentEntity {
    @Id
    // For postgresql, only specifying strategy would lead to null primary key exception
    // Use student_seq int database to generate primary key sequence
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "s_seq")
    @SequenceGenerator(name = "s_seq", sequenceName = "student_seq", allocationSize = 1)
    private Long id;
    private String name;

    public StudentEntity() {}

    public StudentEntity(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}