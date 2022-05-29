package com.library.managment.LMS;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.stubbing.OngoingStubbing;
import org.springframework.boot.test.context.SpringBootTest;

import com.springboot.rest.example.model.Book;
import com.springboot.rest.example.repository.BookRepository;
import com.springboot.rest.example.service.BookService;
import com.springboot.rest.example.service.impl.BookServiceImpl;

@SpringBootTest(classes= {ServiceMocitoTests.class})
public class ServiceMocitoTests {
	@Mock
	BookRepository bookrepo;
	@InjectMocks
	 BookServiceImpl bookserviceimpl;
		
	public List<Book> mybooks;
	
	@Test
	@Order(1)
	public void findByIdTest()
	{
		
		Book boook1=new Book(1,"c","balaguruswamy","Y");
		int bookId=1;
		boook1.setBid(bookId);
		
		when(bookrepo.findById(bookId)).thenReturn(Optional.of(boook1));//mocking
		assertEquals(bookId,bookserviceimpl.getBookById(bookId).getBid());
		
	}
	
	@Test @Order(2)
	public void test_getAllBooks() {
		List<Book> mybooks = new ArrayList<Book>();
		mybooks.add(new Book(2,"c++","bjarne","N"));
		mybooks.add(new Book(3,"java","james","N"));
		when(bookrepo.findAll()).thenReturn(mybooks);//mocking
		assertEquals(2,bookserviceimpl.getAllBooks().size());
		
	}
	
	@Test @Order(3)
	public void test_add() {
		Book book = new Book(4,"python","martin","N");
		when(bookrepo.save(book)).thenReturn(book);//mocking
		assertEquals(book,bookserviceimpl.add(book));
	}
	
	@SuppressWarnings("unchecked")
	@Test @Order(4)
	public void test_updateBook() {
		String authorName = "james shron";
		String bookName = "r";
		Book oldBook = new Book(4,"oldName","oldName","N");
		Optional<Book> oldBookOpt = Optional.ofNullable(oldBook);
		Book book1 = new Book(4,bookName,authorName,"N");
		//int bookId=4;
		//book1.setBid(bookId);
		Mockito.when(bookrepo.findById(book1.getBid())).thenReturn(oldBookOpt);
		//Mockito.when(bookrepo.findById(book1.getBid()).get()).thenReturn(oldBook);

		//mocking
		//Mockito.when(oldBookOpt.get()).thenReturn(oldBook);

		when(bookrepo.save(book1)).thenReturn(book1);
		
		//System.out.println("Optional.of(boook1) : "+bookserviceimpl.updateBook(book1));
		//verify(book1, times(1)).setAuthorName(book1.getAuthorName());
		//verify(book1, times(1)).setBookName(book1.getBookName());
		assertEquals(book1,bookserviceimpl.add(book1));
		assertEquals(book1,bookserviceimpl.updateBook(book1));
	}
	

}
