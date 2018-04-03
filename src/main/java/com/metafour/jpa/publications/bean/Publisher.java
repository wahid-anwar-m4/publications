package com.metafour.jpa.publications.bean;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;

@Data
@Entity
public class Publisher {
	@Id
	@GeneratedValue
	private long id;
	private int version;
	private String name;

	@ManyToMany(mappedBy = "publisher")
	private Set<Book> books;

	public void addBook(Book book) {
		this.books.add(book);
	}

	public void removeBook(Book book) {
		this.books.remove(book);
	}
}
