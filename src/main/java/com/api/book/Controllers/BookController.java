package com.api.book.Controllers;

//****************************************THE DATABSE USED HERE IS : springbootrestjpa **********************************************

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.api.book.Entities.Book;
import com.api.book.Services.BookService;

//@Controller
//you can skip @ResponseBody if you are using @RestController instead of @Controller
@RestController 
public class BookController {

	@Autowired
	private BookService bookService;
	
	//GET ALL BOOKS IN INVENTORY
	@GetMapping("/books")
	public ResponseEntity<List<Book>> getBooks() {
		List<Book> bookList = this.bookService.getAllBooks();
		if(bookList.size()==0) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(bookList);
	}
	
	//GET A BOOK'S DETAILS BY IT'S ID
	@GetMapping("/book/{id}")
	public ResponseEntity<Book> getBook(@PathVariable("id") int id) {
		Book book = this.bookService.getBookById(id);
		if(book == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.of(Optional.of(book));
	}
	
	//ADD A SINGLE BOOK
	@PostMapping("/book")
	public ResponseEntity<Void> addBook(@RequestBody Book book) {
		try {
			this.bookService.addBook(book);
			return ResponseEntity.status(HttpStatus.CREATED).build();	//displays only created status in postman
			//return ResponseEntity.of(Optional.of(bklist));	//displays the new list of books in inventory with the newly added book
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	//ADD A LIST OF BOOKS
	@PostMapping("/books")
	public ResponseEntity<List<Book>> addBooks(@RequestBody List<Book> book) {
		try {
			List<Book> bklist = this.bookService.addBooks(book);
			System.out.println(bklist);
			//return ResponseEntity.of(Optional.of(bklist));
			return ResponseEntity.status(HttpStatus.CREATED).body(bklist);
		} catch(Exception e) {
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	}
	
	//DELETE A BOOK BY ID   and return the new updated list of books after deleting
		@DeleteMapping("/book/{bookid}")
		public ResponseEntity<Void> deleteBook(@PathVariable("bookid") int bookId) {  //you can also have return type in bookService and the handler
			try {
				this.bookService.deleteBook(bookId);
				//return ResponseEntity.ok().build();
				return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			} catch(Exception e) {
				e.printStackTrace();
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			}
			//return this.bookService.deleteBook(bookId);
		}
	
	//UPDATE A BOOK
	@PutMapping("/book/{bookId}")
	public ResponseEntity<Book> updateBook(@RequestBody Book book, @PathVariable("bookId") int bookid){
		try {
			Book newBook = this.bookService.updateBook(book, bookid);
			System.out.println(newBook);
			return ResponseEntity.ok().body(newBook);
		} catch(Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
}
