package com.metafour.jpa.publications.repository;

import java.util.List;

import com.metafour.jpa.publications.bean.Author;
import com.metafour.jpa.publications.bean.Publisher;

public interface AuthorRepositoryCustom {
	public List<Author> getByAge(int age);
	
	public List<Author> getByPublisher(Publisher publisher);
}
