package com.metafour.jpa.publications.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.metafour.jpa.publications.bean.Book;
import com.metafour.jpa.publications.bean.Publisher;
import com.metafour.jpa.publications.repository.BookRepository;
import com.metafour.jpa.publications.repository.PublisherRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BookTest {
	private static Long bookId = 0L;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Test
	public void test1Up() {
		assertThat(bookRepository).isNotNull();
		assertThat(publisherRepository).isNotNull();
	}
	
	private Book sample() {
		Publisher publisher = new Publisher();
		publisher.setName("Metafour");
		publisher = publisherRepository.save(publisher);
		Book book = new Book(publisher);
		book.setPublicationDate(LocalDate.now());
		book.setTitle("JPA workshop");
		return book;
	}
	
	@Test
	public void test2InsertBook() {
		Book book = bookRepository.save(sample());
		bookId = book.getId();
		assertThat(book).isNotNull();
	}
	
	@Test
	public void test3FindBook() {
		Optional<Book> publications= bookRepository.findById(bookId);
		assertThat(publications).isNotNull();
		assertThat(publications.isPresent()).isTrue();
	}
}
