package com.metafour.jpa.publications.bean;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Book extends Publication {
	@NonNull
	@OneToOne
	private Publisher publisher;
	
	private int numberOfPage;
	private int numberOfChapter;
	private String isbn;
	
	public Book(Publisher publisher) {
		super();
		this.publisher = publisher;
	}
}
