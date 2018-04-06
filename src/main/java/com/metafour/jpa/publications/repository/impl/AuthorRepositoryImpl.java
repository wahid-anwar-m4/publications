package com.metafour.jpa.publications.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.metafour.jpa.publications.bean.Author;
import com.metafour.jpa.publications.bean.Publisher;
import com.metafour.jpa.publications.repository.AuthorRepositoryCustom;

public class AuthorRepositoryImpl implements AuthorRepositoryCustom {

	private static final String AUTHOR_QUERY = new StringBuilder()
			.append(" SELECT a from Author a WHERE a.age=:age")
			.toString();
	
	private static final String AUTHOR_GET_BY_PUBLISHER_QUERY = new StringBuilder()
			.append(" SELECT a from Author a JOIN treat(a.publications AS Book) p")
			.append(" WHERE p.publisher=:publisher")
			.toString();
	
	@PersistenceContext
	private EntityManager jpa;
	
	@Override
	public List<Author> getByAge(int age) {
		return jpa.createQuery(AUTHOR_QUERY, Author.class).setParameter("age", age).getResultList();
	}

	@Override
	public List<Author> getByPublisher(Publisher publisher) {
		return jpa.createQuery(AUTHOR_GET_BY_PUBLISHER_QUERY, Author.class).setParameter("publisher", publisher)
				.getResultList();
	}
}
