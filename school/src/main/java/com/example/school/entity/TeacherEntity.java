package com.example.school.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;

import java.util.List;

@Entity
@Table(name = "teachers")
public class TeacherEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "t_seq")
    @SequenceGenerator(name = "t_seq", sequenceName = "teacher_seq", allocationSize = 1)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "teacher")
    @JsonIgnore
    private List<RelationEntity> relationList;

    public TeacherEntity() {}

    public TeacherEntity(Long id, String name, List<RelationEntity> relationList) {
        this.id = id;
        this.name = name;
        this.relationList = relationList;
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

    public List<RelationEntity> getRelationList() {
        return relationList;
    }

    public void setRelationList(List<RelationEntity> relationList) {
        this.relationList = relationList;
    }
}
