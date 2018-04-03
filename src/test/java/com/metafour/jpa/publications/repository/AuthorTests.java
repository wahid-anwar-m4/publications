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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import com.metafour.jpa.publications.bean.Author;
import com.metafour.jpa.publications.bean.BlogPost;
import com.metafour.jpa.publications.bean.Publication;
import com.metafour.jpa.publications.repository.AuthorRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Rollback(false)
public class AuthorTests {
	private static long authorId = 0L;
	@Autowired
	private AuthorRepository authorRepository;


	@PersistenceContext
	private EntityManager jpa;

	@Test
	public void test1Up() {
		assertThat(authorRepository).isNotNull();
	}

	private Author sample() {
		Author author = new Author();
		author.setFirstName("Wahid");
		author.setLastName("Anwar");
		return author;
	}

	private BlogPost blogSample() {
		BlogPost blog = new BlogPost();
		blog.setTitle("blog1");
		blog.setSubject("JPA, JPQL and Spring Data");
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
	
	@Test
	public void test5SelectWithJPQL() {
		List<Author> authors = jpa.createQuery("SELECT a FROM Author a", Author.class).getResultList();
		assertThat(authors).isNotNull();
		assertThat(authors).isNotEmpty();
	}
	
	@Test
	public void test6SelectWithNamedQuery() {
		List<Author> authors = jpa.createNamedQuery("au.ByFirstName", Author.class).setParameter("firstName", "wahid")
				.getResultList();
		assertThat(authors).isNotNull();
		assertThat(authors).isNotEmpty();
	}
	
	@Test
	public void test7SelectWithCRUDRepository() {
		Author author = authorRepository.findByFirstName("Wahid");
		assertThat(author).isNotNull();
	}
	
	@Test
	public void test8SelectWithSpringData() {
		Author author = authorRepository.findByFirstNameSpringData("Wahid");
		assertThat(author).isNotNull();
	}
	
	@Test
	public void test9SelectWithNativeCRUD() {
		Author author = authorRepository.findByFirstNameWithNativeSQL("wahid");
		assertThat(author).isNotNull();
	}
	
	@Test
	public void test10SelectWithCriteria() {
		CriteriaBuilder builder = jpa.getCriteriaBuilder();
		CriteriaQuery<Author> query = builder.createQuery(Author.class);
		Root<Author> authorRoot = query.from(Author.class);
		Join<Author, Publication> publicationRoot = authorRoot.join("publications", JoinType.LEFT);
		query.where(builder.equal(publicationRoot.get("title"), "blog1"));
		List<Author> allocations = jpa.createQuery(query.select(authorRoot)).getResultList();
		assertThat(allocations).isNotNull();
		assertThat(allocations).isNotEmpty(); 
	}
	
	@Test
	public void test11SelectWithNative() {
		@SuppressWarnings("unchecked")
		// note that publications list not loaded, threw an exception.
		// with native query one has to iterate through results and populate to 
		// specific model.
		List<Author> authors = jpa.createNativeQuery("SELECT a.* FROM Author a", Author.class).getResultList();
		assertThat(authors).isNotNull();
		assertThat(authors).isNotEmpty();
	}
}
