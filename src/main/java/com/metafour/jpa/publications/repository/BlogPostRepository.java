package com.metafour.jpa.publications.repository;

import org.springframework.data.repository.CrudRepository;

import com.metafour.jpa.publications.bean.BlogPost;

public interface BlogPostRepository extends CrudRepository<BlogPost, Long>, BlogCustomRepository {
	
}
