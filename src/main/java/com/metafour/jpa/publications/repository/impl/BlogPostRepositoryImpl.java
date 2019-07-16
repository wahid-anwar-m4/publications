package com.metafour.jpa.publications.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;

import com.metafour.jpa.publications.bean.Author;
import com.metafour.jpa.publications.repository.BlogCustomRepository;

public class BlogPostRepositoryImpl implements BlogCustomRepository {

	@Autowired
	private EntityManager entityManager;
	
	@Override
	public List<Author> findAuthorByPost(String tags) {
		List<String> tagList = java.util.Arrays.asList(tags.split(","));
		String query = "SELECT b.authors from BlogPost b, b.keyWords k"
				+ " WHERE k in :keys";
		return entityManager.createQuery(query, Author.class).setParameter("keys", tagList).getResultList();
	}
}
