package com.metafour.jpa.publications.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.metafour.jpa.publications.bean.Book;
import com.metafour.jpa.publications.bean.Publisher;

public interface BookRepository extends CrudRepository<Book, Long> {
	public List<Book> findByPublisher(Publisher publisher);
}
