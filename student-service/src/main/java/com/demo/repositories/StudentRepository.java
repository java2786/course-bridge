package com.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.demo.entities.Student;


public interface StudentRepository extends JpaRepository<Student, Integer>{

    // @Query(value="select s from Student s where s.name=?1", nativeQuery = false)
    @Query(value = "select s from Student s where s.name LIKE %?1%", nativeQuery = false)
    public List<Student> findByName(String name);

}

