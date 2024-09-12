package com.example.school.repository;

import com.example.school.dto.StudentDTO;
import com.example.school.entity.StudentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
public class StudentRepoImpl implements StudentRepo{
    @Autowired
    private EntityManagerFactory emf;

    @Override
    public StudentDTO createStudent(StudentDTO stuDTO) {
        StudentEntity stuEnt = new StudentEntity();
        EntityManager em = emf.createEntityManager();

        stuEnt.setName(stuDTO.getName());

        em.getTransaction().begin();
        em.persist(stuEnt);
        em.getTransaction().commit();
        em.close();
        stuDTO.setId(stuEnt.getId());
        return stuDTO;
    }

    @Override
    public StudentDTO readStudentById(Long id) {
        StudentEntity stuEnt;
        StudentDTO stuDTO = new StudentDTO();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        stuEnt = em.find(StudentEntity.class, id);
        stuDTO.setId(stuEnt.getId());
        stuDTO.setName(stuEnt.getName());
        em.getTransaction().commit();
        em.close();

        return stuDTO;
    }

    @Override
    public boolean updateStudent(StudentDTO stuDTO) {
        StudentEntity stuEnt;
        StudentEntity stuEnt2 = new StudentEntity();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        stuEnt = em.find(StudentEntity.class, stuDTO.getId());
        if (stuEnt != null) {
            stuEnt.setName(stuDTO.getName());
            em.persist(stuEnt);
        }

        em.getTransaction().commit();
        em.close();
        return stuEnt!=null;
    }

    @Override
    public StudentDTO deleteStudentById(Long id) {
        StudentEntity stuEnt = null;
        StudentDTO stuDTO = new StudentDTO();
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        stuEnt = em.find(StudentEntity.class, id);
        stuDTO.setId(stuEnt.getId());
        stuDTO.setName(stuEnt.getName());
        em.remove(stuEnt);
        em.getTransaction().commit();
        em.close();
        return stuDTO;
    }
}
