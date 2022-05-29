package com.springboot.rest.example.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springboot.rest.example.model.Book;
import com.springboot.rest.example.model.Student;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    Object bookOpt = null;

	public Page<Book> findAll(Pageable pageable);

	
	
}