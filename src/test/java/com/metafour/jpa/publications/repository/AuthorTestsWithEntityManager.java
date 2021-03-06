package com.metafour.jpa.publications.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.metafour.jpa.publications.bean.Author;
import com.metafour.jpa.publications.bean.Author.GenderTypeEnum;
import com.metafour.jpa.publications.bean.BlogPost;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback(false)
public class AuthorTestsWithEntityManager {
	private static final String FIRST_NAME = "Wahid";
	private static final String LAST_NAME = "Anwar";
	private static final int AGE = 35;
	private static final String BLOG_TITLE = "blog1";
	private static final String BLOG_SUBJECT = "JPA, JPQL and Spring Data";
	
	private static long authorId = 0L;
	
	@PersistenceContext
	private EntityManager jpa;

	@Test
	public void test0Up() {
		assertThat(jpa).isNotNull();
	}

	private Author sample() {
		Author author = new Author();
		author.setFirstName(FIRST_NAME);
		author.setLastName(LAST_NAME);
		author.setAge(AGE);
		author.setGender(GenderTypeEnum.M);
		return author;
	}

	private BlogPost blogSample() {
		BlogPost blog = new BlogPost();
		blog.setTitle(BLOG_TITLE);
		blog.setSubject(BLOG_SUBJECT);
		blog.setPublicationDate(LocalDate.now());
		return blog;
	}
	
	@Test
	@Transactional
	public void test1Persist() {
		Author author = sample();
		jpa.persist(author);
		authorId = author.getId();
		assertThat(author).isNotNull();
	}

	@Test
	public void test2Find() {
		Author author1 = jpa.find(Author.class, authorId);
		assertThat(author1).isNotNull();
	}

	@Test
	@Transactional
	public void test3Merge() {
		Author author1 = jpa.find(Author.class, authorId);
		assertThat(author1).isNotNull();
		author1.setVersion(1);
		BlogPost blog = blogSample();
		jpa.persist(blog);
		author1.addPublication(blog);
		jpa.merge(author1);
		author1 = jpa.find(Author.class, authorId);
		assertThat(author1.getVersion() == 1).isTrue();
		assertThat(author1.getPublications()).isNotEmpty();
	}

	@Test
	@Transactional
	public void test4Remove() {
		Author author1 = jpa.find(Author.class, authorId);
		jpa.remove(author1);

		author1 = jpa.find(Author.class, authorId);
		assertThat(author1).isNull();
	}
}
