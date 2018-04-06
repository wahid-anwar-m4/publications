package com.metafour.jpa.publications.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
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
import com.metafour.jpa.publications.bean.Book;
import com.metafour.jpa.publications.bean.Publication;
import com.metafour.jpa.publications.bean.Publisher;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback(false)
public class AuthorTestsWithEntityManagerSelects {
	private static final String ISBN = "test book 1";
	private static final String PUBLISHER_NAME = "Metafour";
	private static final String FIRST_NAME = "Wahid";
	private static final String LAST_NAME = "Anwar";
	private static final int AGE = 35;
	private static final String BLOG_TITLE = "blog1";
	private static final String BLOG_SUBJECT = "JPA, JPQL and Spring Data";
	
	@PersistenceContext
	private EntityManager jpa;
	
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
	public void test0Up() {
		assertThat(jpa).isNotNull();
		persistSampleData();
	}
	
	private void persistSampleData() {
		Author author = sample();
		jpa.persist(author);
		Publisher publisher = publisherSample();
		jpa.persist(publisher);
		Book book = bookSample();
		book.setPublisher(publisher);
		jpa.persist(book);
		BlogPost blogPost = blogSample();
		jpa.persist(blogPost);
		author.addPublication(book);
		author.addPublication(blogPost);
		jpa.merge(author);
	}
	
	@Test
	@Transactional
	public void test2SelectWithJPQL() {
		List<Author> authors = jpa.createQuery("SELECT a FROM Author a", Author.class).getResultList();
		assertThat(authors).isNotNull();
		assertThat(authors).isNotEmpty();
	}

	@Test
	public void test3SelectWithNamedQuery() {
		List<Author> authors = jpa.createNamedQuery("au.ByFirstName", Author.class).setParameter("firstName", "wahid")
				.getResultList();
		assertThat(authors).isNotNull();
		assertThat(authors).isNotEmpty();
	}

	@Test
	public void test4SelectWithCriteria() {
		CriteriaBuilder builder = jpa.getCriteriaBuilder();
		CriteriaQuery<Author> query = builder.createQuery(Author.class);
		Root<Author> authorRoot = query.from(Author.class);
		Join<Author, Publication> publicationRoot = authorRoot.join("publications", JoinType.LEFT);
		query.where(builder.equal(publicationRoot.get("title"), BLOG_TITLE));
		List<Author> allocations = jpa.createQuery(query.select(authorRoot)).getResultList();
		assertThat(allocations).isNotNull();
		assertThat(allocations).isNotEmpty();
	}
	
	@Test
	public void test5SelectWithNative() {
		@SuppressWarnings("unchecked")
		// note that publications list not loaded, threw an exception.
		// with native query one has to iterate through results and populate to
		// specific model.
		List<Author> authors = jpa.createNativeQuery("SELECT a.* FROM Author a", Author.class).getResultList();
		assertThat(authors).isNotNull();
		assertThat(authors).isNotEmpty();
	}
}
