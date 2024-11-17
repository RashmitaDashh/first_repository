package com.api.book.Services;

//******************** DATABSE USED HERE IS : springbootjpa ****************************************

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.api.book.Entities.Book;
import com.api.book.dao.BookRepository;
@Component	//you can also use @Service
public class BookService {
	
	@Autowired
	private BookRepository bookRepo;
	
	public List<Book> getAllBooks(){
		List<Book> books = (List<Book>) this.bookRepo.findAll();
		return books;
	}
	
	//get a particular book by it's id
	public Book getBookById(int id) {
		Book book = null;
		try {
			book = this.bookRepo.findById(id);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return book;
	}
	
	//add a book into inventory
	public void addBook(Book b) {
		bookRepo.save(b);
	}
	
	//add more than one book into inventory
	public List<Book> addBooks(List<Book> bookList) {
		bookRepo.saveAll(bookList);
		List<Book> books = (List<Book>) this.bookRepo.findAll();
		return books;
	}
	
	//delete a book
	public void deleteBook(int id) {
		bookRepo.deleteById(id);
		//bookRepo.deleteAll();   //to delete all the books from inventory
	}
	
	public Book updateBook(Book book, int bid) {
		book.setId(bid);	//in order to make sure that the respective book with it's id gets saved(or you can say updated)
		bookRepo.save(book);
		return book;
	}
}
