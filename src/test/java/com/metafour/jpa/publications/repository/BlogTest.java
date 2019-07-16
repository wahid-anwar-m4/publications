package com.metafour.jpa.publications.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.metafour.jpa.publications.bean.Author;
import com.metafour.jpa.publications.bean.BlogPost;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BlogTest {
	
	private static final String BLOG_TITLE = "blog1";
	private static final String BLOG_SUBJECT = "JPA, JPQL and Spring Data";
	private static final String KEY_WORDS = "JPA, JPQL, Spring Data, Criteria";
	
	@Autowired
	private BlogPostRepository blogPostRepository;
	
	private BlogPost sample() {
		BlogPost blog = new BlogPost();
		blog.setTitle(BLOG_TITLE);
		blog.setSubject(BLOG_SUBJECT);
		blog.setKeyWords(Arrays.asList(KEY_WORDS.split(",")));
		blog.setPublicationDate(LocalDate.now());
		return blog;
	}
	
	@Test
	public void testBlog() {
		blogPostRepository.save(sample());
		List<Author> authors = blogPostRepository.findAuthorByPost("JPA,JPQL");
		assertThat(authors).isNotNull();
		assertThat(authors).isNotEmpty();
	}
}
