package com.example.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

@Entity
@Table(name = "teaching")
public class RelationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "r_seq")
    @SequenceGenerator(name = "r_seq", sequenceName = "teaching_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")

    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "student_id")
    @JsonIgnore
    private StudentEntity student;

    public RelationEntity() {}

    public RelationEntity(long id, TeacherEntity teacher, StudentEntity student) {
        this.id = id;
        this.teacher = teacher;
        this.student = student;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TeacherEntity getTeacher() {
        return teacher;
    }

    public void setTeacher(TeacherEntity teacher) {
        this.teacher = teacher;
    }

    public StudentEntity getStudent() {
        return student;
    }

    public void setStudent(StudentEntity student) {
        this.student = student;
    }
}