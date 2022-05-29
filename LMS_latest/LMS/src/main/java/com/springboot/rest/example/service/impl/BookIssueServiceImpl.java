package com.springboot.rest.example.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.springboot.rest.example.exception.DeleteNotAllowedException;
import com.springboot.rest.example.exception.ResourceNotFoundException;
import com.springboot.rest.example.model.Book;
import com.springboot.rest.example.model.IssueBook;
import com.springboot.rest.example.model.Student;
import com.springboot.rest.example.repository.BookIssueRepository;
import com.springboot.rest.example.repository.BookRepository;
import com.springboot.rest.example.repository.StudentRepository;
import com.springboot.rest.example.service.BookIssueService;
import com.springboot.rest.example.service.BookService;
import com.springboot.rest.example.service.StudentService;

@Service
public class BookIssueServiceImpl implements BookIssueService {

    @Autowired
    private BookIssueRepository bookissueRepository;
    
    @Autowired
    private BookService bookService;
    
    @Autowired
    private StudentService studentService;

    @Override
    public IssueBook issueBook(IssueBook issueBook) {
    	issueBook = bookissueRepository.save(issueBook);
        return issueBook;

    }

    
   @Override
   public void checkBookAlreadyIssued(Book book){
    	
    	 List<IssueBook> issueBookList = bookissueRepository.findByBid(book.getBid());
         if (issueBookList !=null && !issueBookList.isEmpty()) {
             throw new ResourceNotFoundException("Book is already Issued to Some other Student");
         }

    }
	
	public boolean returnIssuedBooks(IssueBook issueBook) {
		boolean status=true;
		 Book book=bookService.getBookById(issueBook.getBid());
		 Student student=studentService.getStudentById(issueBook.getSid());
		 
		 List<IssueBook> issueBookToStudentList=bookissueRepository.findBySid(student.getStudentId()); 
		 IssueBook issueBookToBeDeleted=null;
	        if(issueBookToStudentList !=null && !issueBookToStudentList.isEmpty()) {
	        	for(IssueBook issueBook1:issueBookToStudentList) {
	        		if(issueBook1.getBid()==issueBook.getBid()) {
	        			issueBookToBeDeleted=issueBook1;
	        			break;
	        		}
	        	}
	        	try {
	        	bookissueRepository.delete(issueBookToBeDeleted);
	        	book=bookService.updateBookWithIssueStatus(book,"Y");
	        	return status;
	        	}catch(Exception e) {
	        		status=false;
	        		System.out.println("Error while deleteing record from issuebook table "+e.getMessage());
	        	}
	        }else {
	        	throw new ResourceNotFoundException("Student with ID "+issueBook.getSid()+" has not been issued any books");
	        }
		 
		 return status;
	}

	public Map<Student,List<Book>> fetchStudentWithAllIssuedBooks(Integer studentId){
		Map<Student,List<Book>> mapStudentToBook=new HashMap<Student,List<Book>>();
		Student student=studentService.getStudentById(studentId);
		List<IssueBook> issueBookToStudentList=bookissueRepository.findBySid(student.getStudentId());
		List<Book> bookList=new ArrayList<>();
		if(issueBookToStudentList !=null && !issueBookToStudentList.isEmpty()) {
			Book book=null;
			for(IssueBook issueBook : issueBookToStudentList) {
				 book=bookService.getBookById(issueBook.getBid());
				 bookList.add(book);
			}
			mapStudentToBook.put(student, bookList);
		}else {
        	throw new ResourceNotFoundException("Student with ID "+studentId+" has not been issued any books");
        }
		return mapStudentToBook;
	}

}