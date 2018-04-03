package com.metafour.jpa.publications.bean;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import lombok.Data;

@Data
@Entity
@NamedQueries({
	@NamedQuery(name ="au.ByFirstName", query = "SELECT a FROM Author a where UPPER(a.firstName)=UPPER(:firstName)")
})
public class Author {
	@Id
	@GeneratedValue
	private long id;
	private int version;
	private String firstName;
	private String lastName;
	@ManyToMany
	private Set<Publication> publications = new HashSet<>();

	public void addPublication(Publication publication) {
		this.getPublications().add(publication);
	}

	public void removePublication(Publication publication) {
		this.getPublications().remove(publication);
	}
}
