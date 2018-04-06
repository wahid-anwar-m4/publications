package com.metafour.jpa.publications.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.metafour.jpa.publications.bean.Author;
import com.metafour.jpa.publications.bean.Author.GenderTypeEnum;
import com.metafour.jpa.publications.bean.Book;
import com.metafour.jpa.publications.bean.Publisher;


@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorTestsWithCRUDRepository {
	private static final String FIRST_NAME = "Wahid";
	private static final String LAST_NAME = "Anwar";
	private static final int AGE = 35;
	private static final String PUBLISHER_NAME = "Metafour";
	
	private static final String ISBN = "test book 2";
	
	private static Long publisherId = 0L;
	@Resource
	private AuthorRepository authorRepository;
	
	@Resource
	private BookRepository bookRepository;
	
	@Resource
	private PublisherRepository publisherRepository;
	
	private Author sample() {
		Author author = new Author();
		author.setFirstName(FIRST_NAME);
		author.setLastName(LAST_NAME);
		author.setAge(AGE);
		author.setGender(GenderTypeEnum.M);
		return author;
	}
	
	private Book bookSample() {
		Book book = new Book();
		book.setIsbn(ISBN);
		book.setNumberOfChapter(6);
		book.setNumberOfPage(50);
		book.setPublicationDate(LocalDate.now());
		return book;
	}
	
	private Publisher publisherSample() {
		Publisher publisher = new Publisher();
		publisher.setName(PUBLISHER_NAME);
		return publisher;
	}
	
	private void persistSampleDate() {
		Publisher publisher = publisherSample();
		publisher = publisherRepository.save(publisherSample());
		publisherId = publisher.getId();
		Book book = bookSample();
		book.setPublisher(publisher);
		book = bookRepository.save(book);
		Author author = sample();
		author.addPublication(book);
		author = authorRepository.save(author);
	}
	
	@Test
	public void test1Save() {
		assertThat(authorRepository).isNotNull();
		assertThat(publisherRepository).isNotNull();
		persistSampleDate();
	}

	@Test
	public void test2SelectWithCRUDRepository() {
		Author author = authorRepository.findFirstByFirstName(FIRST_NAME);
		assertThat(author).isNotNull();
	}

	@Test
	public void test3SelectWithLongMethodName() {
		Author author = authorRepository.findFirstByFirstNameAndLastNameAndAgeAndGender(FIRST_NAME, LAST_NAME, AGE,
				GenderTypeEnum.M);
		assertThat(author).isNotNull();
	}
	
	@Test
	public void test4SelectWithQuery() {
		List<Author> authors = authorRepository.findAuthor(FIRST_NAME, LAST_NAME, AGE,
				GenderTypeEnum.M);
		assertThat(authors).isNotNull();
		assertThat(authors).isNotEmpty();
	}

	@Test
	public void test5SelectWithSpringData() {
		List<Author> authors = authorRepository.findByFirstNameSpringDataQuery(FIRST_NAME);
		assertThat(authors).isNotNull();
		assertThat(authors).isNotEmpty();
		assertThat(authors.stream().findFirst().get().getFirstName().equalsIgnoreCase(FIRST_NAME)).isTrue();
	}

	@Test
	public void test6SelectWithNativeCRUD() {
		Author author = authorRepository.findByFirstNameWithNativeSQL(FIRST_NAME);
		assertThat(author).isNotNull();
	}
	
	@Test
	public void test7SelectWithCustomImpl() {
		List<Author> author = authorRepository.getByAge(AGE);
		assertThat(author).isNotNull();
		assertThat(author).isNotEmpty();
	}
	
	@Test
	public void test8SelectWithCustomImpl() {
		Publisher publisher = publisherRepository.findOne(publisherId);
		if (publisher != null) {
			List<Author> author = authorRepository.getByPublisher(publisher);
			assertThat(author).isNotNull();
			assertThat(author).isNotEmpty();
		}
	}
}
