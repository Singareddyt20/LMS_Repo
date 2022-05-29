package com.springboot.rest.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.example.model.Book;
import com.springboot.rest.example.model.IssueBook;
import com.springboot.rest.example.model.Student;
import com.springboot.rest.example.service.BookIssueService;
import com.springboot.rest.example.service.BookService;
import com.springboot.rest.example.service.StudentService;

@RestController
@RequestMapping(path = "/booksissue")
public class BookIssueController {

	@Autowired
    private BookIssueService bookIssueService;
	
	@Autowired
    private BookService bookService;
	@Autowired
    private StudentService studentService;
	
	 @PostMapping(path = "/issue")
	    public ResponseEntity<String> issueBook(@RequestBody IssueBook issueBook) {
		 boolean status=true;
		 String message="";
		 Book book=bookService.getBookById(issueBook.getBid());
		 studentService.getStudentById(issueBook.getSid());
		 bookIssueService.checkBookAlreadyIssued(book);
		 try {
		 bookIssueService.issueBook(issueBook);
		 bookService.updateBookWithIssueStatus(book,"N");
		 }
		 catch(Exception e) {
			 status=false;
			 System.out.println("Error while issueing a book to student");
		 }
		 
		 if(status) {
	    	   message="Book with id "+issueBook.getBid()+" issued successfully to student with ID " +issueBook.getSid();
	       }
	       else {
	    	   message="Book with id "+issueBook.getBid()+" could not be issued to student with ID " +issueBook.getSid();
	       }
	        return ResponseEntity.ok(message);
	    }
	 
	 @PostMapping(path = "/returnissuebook")
	    public ResponseEntity<String> returnIssueBook(@RequestBody IssueBook issueBook) {
		 String message="";
		 boolean status=bookIssueService.returnIssuedBooks(issueBook);
		 if(status) {
	    	   message="Book with id "+issueBook.getBid()+" issued to student with ID " +issueBook.getSid()+" is returned successfully";
	       }
	       else {
	    	   message="Book with id "+issueBook.getBid()+" issued to student with ID " +issueBook.getSid()+" is not returned due to some error";
	       }
	        return ResponseEntity.ok(message);
	    }
}
