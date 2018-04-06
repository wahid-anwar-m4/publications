package com.metafour.jpa.publications.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.metafour.jpa.publications.bean.Author;
import com.metafour.jpa.publications.bean.Author.GenderTypeEnum;

public interface AuthorRepository extends CrudRepository<Author, Long>, AuthorRepositoryCustom {
	
	public Author findFirstByFirstName(@Param("firstName") String firstName);
	
	public Author findFirstByFirstNameAndLastNameAndAgeAndGender(@Param("firstName") String firstName,
			@Param("lastName") String lastName, @Param("age") int age, @Param("gender") GenderTypeEnum gender);
	
	@Query("SELECT a FROM Author a WHERE a.firstName=:firstName AND a.lastName=:lastName"
			+ "	AND a.age=:age AND a.gender=:gender")
	public List<Author> findAuthor(@Param("firstName") String firstName,
			@Param("lastName") String lastName, @Param("age") int age, @Param("gender") GenderTypeEnum gender);
	
	@Query("SELECT a FROM Author a WHERE a.firstName=:firstName")
	public List<Author> findByFirstNameSpringDataQuery(@Param("firstName") String firstName);
	
	@Query(nativeQuery = true, value = "SELECT a.* FROM author a WHERE UPPER(a.firstname)=UPPER(?) LIMIT 1")
	public Author findByFirstNameWithNativeSQL(String firstName);
}
