package com.metafour.jpa.publications.bean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Publication {
	@Id
	@GeneratedValue
	private long id;
	private int version;
	private String title;
	private LocalDate publicationDate;
	@ManyToMany(mappedBy = "publications")
	private Set<Author> authors = new HashSet<>();

	public void addAuthor(Author author) {
		this.authors.add(author);
	}

	public void removeAuthor(Author author) {
		this.authors.remove(author);
	}
}
