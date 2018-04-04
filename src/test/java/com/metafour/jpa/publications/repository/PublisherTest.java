package com.metafour.jpa.publications.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.metafour.jpa.publications.bean.Book;
import com.metafour.jpa.publications.bean.Publisher;
import com.metafour.jpa.publications.repository.PublisherRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PublisherTest {
	private static long PUBLISHER_ID = 0L;
	
	@Autowired
	private PublisherRepository publisherRepository;
	
	@Autowired
	private BookRepository bookRepository;

	@Test
	public void test1Up() {
		assertThat(publisherRepository).isNotNull();
		assertThat(bookRepository).isNotNull();
	}

	private Publisher sample() {
		Publisher publisher = new Publisher();
		publisher.setName("Metafour");
		return publisher;
	}

	@Test
	public void test2InsertPublisher() {
		Publisher publisher = publisherRepository.save(sample());
		PUBLISHER_ID = publisher.getId();
		assertThat(publisher).isNotNull();
	}

	@Test
	public void test3FindPublisher() {
		Iterable<Publisher> publishers = publisherRepository.findAll();
		assertThat(publishers).isNotNull();
		assertThat(publishers).isNotEmpty();
	}
	
	@Test
	@Transactional
	public void test3UpdatePublisher() {
		Iterable<Book> bookList = bookRepository.findAll();
		Optional<Publisher> publisherOp = publisherRepository.findById(10l);
		if (publisherOp.isPresent()) {
			Publisher publisher = publisherOp.get();
			bookList.forEach(book -> publisher.addBook(book));
			publisherRepository.save(publisher);
			
			assertThat(publisher).isNotNull();
		}
		assertThat(bookList).isNotEmpty();
	}
}
