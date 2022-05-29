package com.springboot.rest.example.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springboot.rest.example.exception.DeleteNotAllowedException;
import com.springboot.rest.example.exception.ResourceNotFoundException;
import com.springboot.rest.example.exception.UpdateBookNotAllowedException;
import com.springboot.rest.example.model.Book;
import com.springboot.rest.example.repository.BookRepository;
import com.springboot.rest.example.service.BookService;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Override
    public Book add(Book book) {
    	
    	book.setBookIssue("N");
    	book = bookRepository.save(book);
        System.out.println("book add : "+book);

        return book;

    }

	@Override
	public Book updateBookWithIssueStatus(Book book, String bookReturn) {
		if("Y".equalsIgnoreCase(bookReturn)) {
			book.setBookIssue("N");
		}else {
			book.setBookIssue("Y");
		}
		
		book = bookRepository.save(book);
		return book;
	}

    /*@Override
    public void deleteStudent(Integer studentId) {
        if (studentId == null) {
            throw new IllegalArgumentException("studentId must not be null");
        }
        Optional<Student> studentOpt = studentRepository.findById(studentId);
        if (!studentOpt.isPresent()) {
            throw new ResourceNotFoundException("student not found");
        }
        studentRepository.deleteById(studentId);
    }*/

    @Override
    public Book getBookById(Integer bookId) {
        if (bookId == null) {
            throw new IllegalArgumentException("bookId must not be null");
        }

        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (!bookOpt.isPresent()) {
            throw new ResourceNotFoundException("book not found");
        }
        Book book = bookOpt.get();

       
        return book;
    }

    @Override
    public List<Book> getAllIssuedBooks() {
    	List<Book> issudedBookList = (List<Book>) bookRepository.findAll();
    	issudedBookList=processIssuedBooksList(issudedBookList);
       
        return issudedBookList;
    }
    
    List<Book> processIssuedBooksList(List<Book> issudedBookList){
    	    	List<Book> issudedBookList1=new ArrayList<Book>();
    	if(issudedBookList !=null && !issudedBookList.isEmpty()){
    		issudedBookList1=issudedBookList.stream().filter(b->b.getBookIssue().equals("Y")).collect(Collectors.toList());
    	}
    	
    	return issudedBookList1;
    }

    @Override
    public Book updateBook(Book book) {
        Optional<Book> bookOpt =bookRepository.findById(book.getBid());

        if (!bookOpt.isPresent()) {
            throw new ResourceNotFoundException("book not found");
        }
        System.out.println("bookOpt : "+bookOpt);

        Book book1 = bookOpt.get();
        if(book1.getBookIssue().equals("Y")) {
        	throw new UpdateBookNotAllowedException("Book can not be updated since it has already been issued. Pls try later once book is available in library");
        }
        System.out.println("book1 : "+book1);

        book1.setAuthorName(book.getAuthorName());
        book1.setBookName(book.getBookName());
        System.out.println("book10 : "+book1.toString());
        book1 = bookRepository.save(book1);
        System.out.println("saves : "+book1);
        return book1;
    }

	@Override
	public List<Book> getAllBooks() 
	{
		List<Book> books = (List<Book>) bookRepository.findAll();
		return books;
		
		
		
	}

	

	@Override
	public boolean deleteBook(Integer bookId) {
		boolean status=true;
		if (bookId == null) {
            throw new IllegalArgumentException("bookId must not be null");
        }

        Optional<Book> bookOpt = bookRepository.findById(bookId);
        if (!bookOpt.isPresent()) {
            throw new ResourceNotFoundException("book not found");
        }
        Book book = bookOpt.get();
        if(book.getBookIssue().equals("Y")) {
        	throw new DeleteNotAllowedException("Book can not be deleted since it has already been issued. Pls try later once book is available in library");
        }
        try {
        bookRepository.delete(book);
        }
        catch(Exception e) {
        	status=false;
        }
       
        return status;
		
	}

  	
	

	
	

  	 @Override
     public List<Book> getAllUnIssuedBooks() {
     	List<Book> unissudedBookList = (List<Book>) bookRepository.findAll();
     	unissudedBookList=processunIssuedBooksList(unissudedBookList);
        
         return unissudedBookList;
     }
     
     List<Book> processunIssuedBooksList(List<Book> unissudedBookList){
     	List<Book> unissudedBookList1=new ArrayList<Book>();
     	if(unissudedBookList !=null && !unissudedBookList.isEmpty()){
     		unissudedBookList1=unissudedBookList.stream().filter(b->b.getBookIssue().equals("N")).collect(Collectors.toList());
     	}
     	
     	return unissudedBookList1;
     }
}