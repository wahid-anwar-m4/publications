package com.metafour.jpa.publications.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.metafour.jpa.publications.bean.Author;

public interface AuthorRepository extends CrudRepository<Author, Long> {
	
	public Author findByFirstName(@Param("firstName") String firstName);
	
	@Query("SELECT a from Author a where a.firstName=:firstName")
	public Author findByFirstNameSpringData(@Param("firstName") String firstName);
	
	@Query(nativeQuery = true, value = "SELECT a.* from author a where upper(a.first_name)=upper(:firstName)")
	public Author findByFirstNameWithNativeSQL(@Param("firstName") String firstName);
}
