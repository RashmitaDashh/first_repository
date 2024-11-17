package com.api.book.Entities;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name= "author")
public class Author {
	
	@Id
	private int authorid;
	private String firstName;
	private String lastName;
	private String language;
	
	@OneToOne(mappedBy = "author")
	@JsonBackReference
	private Book book;
	
	
	public Author() {
		super();
	}
	public Author(int authorid, String firstName, String lastName, String language) {
		super();
		this.authorid = authorid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.language = language;
	}
	
	
	public int getAuthorId() {
		return authorid;
	}
	public void setAuthorId(int authorid) {
		this.authorid = authorid;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
		
}