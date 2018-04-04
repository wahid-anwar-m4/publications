package com.metafour.jpa.publications.repository;

import java.util.List;

import com.metafour.jpa.publications.bean.Author;

public interface AuthorCustomRepository {
	public List<Author> getByAge(int age);
}
