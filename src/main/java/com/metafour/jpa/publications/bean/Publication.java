package com.metafour.jpa.publications.bean;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;

import com.metafour.jpa.publications.util.LocalDateAttributeConverter;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "TYPE")
@EqualsAndHashCode
public class Publication {
	@Id
	@GeneratedValue
	private Long id;
	private int version;
	private String title;
	@Convert(converter = LocalDateAttributeConverter.class)
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
