package com.metafour.jpa.publications.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.metafour.jpa.publications.bean.Author;
import com.metafour.jpa.publications.repository.AuthorCustomRepository;

public class AuhtorRepositoryImpl implements AuthorCustomRepository {

	private static final String AUTHOR_QUERY = new StringBuilder()
			.append(" SELECT a from Author a WHERE a.age=:age")
			.toString();
	
	@PersistenceContext
	private EntityManager jpa;
	
	@Override
	public List<Author> getByAge(int age) {
		return jpa.createQuery(AUTHOR_QUERY, Author.class).setParameter("age", age).getResultList();
	}
}
