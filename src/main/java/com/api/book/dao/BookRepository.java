package com.api.book.dao;

//****************************************DATABASE USED HERE IS: springbootjpa *******************************************************

import org.springframework.data.repository.CrudRepository;

import com.api.book.Entities.Book;

public interface BookRepository extends CrudRepository<Book, Integer> {
	public Book findById(int id);
	
}
