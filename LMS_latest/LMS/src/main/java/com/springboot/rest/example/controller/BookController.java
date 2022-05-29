package com.springboot.rest.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.rest.example.model.Book;

import com.springboot.rest.example.service.BookService;


@RestController
@RequestMapping(path = "/books")
public class BookController {

	@Autowired
    private BookService bookService;
	
	 @PostMapping(path = "/add")
	    public ResponseEntity<Book> addBook(@RequestBody Book book) {
		 Book book1 = bookService.add(book);
	        return ResponseEntity.ok(book1);
	    }
	 
	 @GetMapping(path = "/allissuedBooks")
	    public ResponseEntity<List<Book>> getAllIssuedBooks() {
	        List<Book> issuedBooksList = bookService.getAllIssuedBooks();
	        return ResponseEntity.ok(issuedBooksList);
	    }
	
	 
	 @PostMapping(path = "/update")
	    public ResponseEntity<Book> updateBook(
	            @RequestBody  Book book) {
	        Book book1 = bookService.updateBook(book);
	        return ResponseEntity.ok(book1);
	    }

		/*@RequestMapping("/update-book/{id}")
		public String updateBook(@PathVariable("id") Long id, Book book, BindingResult result, Model model) {
			if (result.hasErrors()) {
				book.setBid(id);
				return "update-book";
			}

			bookService.updateBook(book);
			model.addAttribute("books", bookService.findAllBooks());
			return "redirect:/books";
		}*/


	    @DeleteMapping(path = "/{bookId}/delete")
	    public ResponseEntity<String> deleteBook(@PathVariable(name = "bookId") Integer bookId) {
	    	String message="";
	       boolean status=bookService.deleteBook(bookId);
	       if(status) {
	    	   message="Book with id "+bookId+"deleted successfully";
	       }
	       else {
	    	   message="Book with id "+bookId+"not deleted";
	       }
	       return ResponseEntity.ok(message);
	    }
	    

		 @GetMapping(path = "/allunissuedBooks")
		    public ResponseEntity<List<Book>> getBooks1() {
		        List<Book> unissuedBooksList = bookService.getAllUnIssuedBooks();
		        return ResponseEntity.ok(unissuedBooksList);
		    }

		    @GetMapping(path = "/{bookId}/search")
		    public ResponseEntity<Book> searchBook(@PathVariable(name = "bookId") Integer bookId) {
		    	String message="";
		       Book book=bookService.getBookById(bookId);
		       		     return ResponseEntity.ok(book);
		    }
		    
	    


}
