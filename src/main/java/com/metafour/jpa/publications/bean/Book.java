package com.metafour.jpa.publications.bean;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@Entity(name = "Book")
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
public class Book extends Publication {
	/**
	 * 
	 */
	private static final long serialVersionUID = 493861595777097124L;

	@NonNull
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "publisher_id")
	private Publisher publisher;
	
	private int numberOfPage;
	private int numberOfChapter;
	private String isbn;
	
	public Book(Publisher publisher) {
		super();
		this.publisher = publisher;
	}
}
