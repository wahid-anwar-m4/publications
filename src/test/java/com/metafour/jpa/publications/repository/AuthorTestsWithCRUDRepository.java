package com.metafour.jpa.publications.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.metafour.jpa.publications.bean.Author;
import com.metafour.jpa.publications.bean.Author.GenderTypeEnum;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class AuthorTestsWithCRUDRepository {
	private static final String FIRST_NAME = "Wahid";
	private static final String LAST_NAME = "Anwar";
	private static final int AGE = 35;
	@Autowired
	private AuthorRepository authorRepository;
	
	@Test
	public void test1Up() {
		assertThat(authorRepository).isNotNull();
	}

	@Test
	public void test2SelectWithCRUDRepository() {
		Author author = authorRepository.findByFirstName(FIRST_NAME);
		assertThat(author).isNotNull();
	}

	@Test
	public void test3SelectWithLongMethodName() {
		Author author = authorRepository.findByFirstNameAndLastNameAndAgeAndGender(FIRST_NAME, LAST_NAME, AGE,
				GenderTypeEnum.M);
		assertThat(author).isNotNull();
	}
	
	@Test
	public void test4SelectWithQuery() {
		Author author = authorRepository.findAuthor(FIRST_NAME, LAST_NAME, AGE,
				GenderTypeEnum.M);
		assertThat(author).isNotNull();
	}

	@Test
	public void test5SelectWithSpringData() {
		Author author = authorRepository.findByFirstNameSpringDataQuery(FIRST_NAME);
		assertThat(author).isNotNull();
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
}
