package com.metafour.jpa.publications.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Iterator;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.metafour.jpa.publications.bean.Author;
import com.metafour.jpa.publications.bean.Book;
import com.metafour.jpa.publications.bean.Publisher;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PublisherTest {
	private static final String PUBLISHER_NAME = "Metafour";
	
	@Resource
	private PublisherRepository publisherRepository;
	
	@Resource
	private BookRepository bookRepository;
	
	@Resource
	private AuthorRepository authorRepository;

	@Test
	public void test1Up() {
		assertThat(publisherRepository).isNotNull();
		assertThat(bookRepository).isNotNull();
	}

	private Publisher sample() {
		Publisher publisher = new Publisher();
		publisher.setName(PUBLISHER_NAME);
		return publisher;
	}
	
	private Book bookSample() {
		Publisher publisher = new Publisher();
		publisher.setName("Metafour");
		publisher = publisherRepository.save(publisher);
		Author author = authorRepository.findFirstByFirstName("Wahid");
		Book book = new Book(publisher);
		book.setPublicationDate(LocalDate.now());
		book.setTitle("JPA workshop");
		book.addAuthor(author);
		return book;
	}

	@Test
	public void test2InsertPublisher() {
		Publisher publisher = publisherRepository.save(sample());
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
	public void test4UpdatePublisher() {
		Iterable<Book> bookList = bookRepository.findAll();
		if(!bookList.iterator().hasNext())
			bookRepository.save(bookSample());
		
		assertThat(bookList).isNotEmpty();
		Publisher publisher = publisherRepository.findFirstByName(PUBLISHER_NAME);
		assertThat(publisher).isNotNull();
		Publisher publisher1 = publisher;
		Iterator<Book> iterator = bookList.iterator();
		
		while(iterator.hasNext()) {
			publisher1.addBook(iterator.next());
		}
		publisher = publisherRepository.save(publisher);
		assertThat(publisher).isNotNull();
		assertThat(publisher.getBooks()).isNotEmpty();
	}
}
