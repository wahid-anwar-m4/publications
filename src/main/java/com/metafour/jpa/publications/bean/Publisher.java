package com.metafour.jpa.publications.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@Entity
@EqualsAndHashCode(exclude = "books")
public class Publisher {
	@Id
	@GeneratedValue
	private Long id;
	private int version;
	
	private String name;
	 
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "publisher")
	private Set<Book> books = new HashSet<>();

	public void addBook(Book book) {
		this.books.add(book);
	}

	public void removeBook(Book book) {
		this.books.remove(book);
	}
}
