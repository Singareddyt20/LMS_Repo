package com.springboot.rest.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.springboot.rest.example.model.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

    public Page<Student> findAll(Pageable pageable);
}