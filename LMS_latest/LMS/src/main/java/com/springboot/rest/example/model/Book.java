package com.springboot.rest.example.model;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
@Entity
@Table(name = "book")
@EntityListeners(AuditingEntityListener.class)
public class Book {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bid;
	
	String bookName;
	String authorName;
	String bookIssue;
	@CreatedDate
    private LocalDateTime created;
	@LastModifiedDate
    private LocalDateTime modified;
	
	
    
	
	public Book(int bid, String bookName, String authorName, String bookIssue) {
		// TODO Auto-generated constructor stub
		this.bid = bid;
		this.bookName = bookName;
		this.authorName = authorName;
		this.bookIssue = bookIssue;
	} 
	public Integer getBid() {
		return bid;
	}
	public void setBid(Integer id) {
		this.bid = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getAuthorName() {
		return authorName;
	}
	public void setAuthorName(String authorName) {
		this.authorName = authorName;
	}
	public LocalDateTime getCreated() {
		return created;
	}
	public void setCreated(LocalDateTime created) {
		this.created = created;
	}
	public LocalDateTime getModified() {
		return modified;
	}
	public void setModified(LocalDateTime modified) {
		this.modified = modified;
	}
	public String getBookIssue() {
		return bookIssue;
	}
	public void setBookIssue(String bookIssue) {
		this.bookIssue = bookIssue;
	}
	public void setBid1(Long id) {
		// TODO Auto-generated method stub
		
	}
		
}
