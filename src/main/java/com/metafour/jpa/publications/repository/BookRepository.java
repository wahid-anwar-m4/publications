package com.metafour.jpa.publications.repository;

import org.springframework.data.repository.CrudRepository;

import com.metafour.jpa.publications.bean.Book;

public interface BookRepository extends CrudRepository<Book, Long> {
	
}
