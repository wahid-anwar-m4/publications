package com.metafour.jpa.publications.bean;

import java.io.Serializable;
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
	@NamedQuery(name ="au.ByFirstName", query = "SELECT a FROM Author a WHERE UPPER(a.firstName)=UPPER(:firstName)")
})
public class Author implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 595178816754472472L;
	@Id
	@GeneratedValue
	private Long id;
	private int version;
	private String firstName;
	private String lastName;
	private int age;
	private GenderTypeEnum gender;
	
	public enum GenderTypeEnum {
		M, F
	}
	
	@ManyToMany
	private Set<Publication> publications = new HashSet<>();

	public void addPublication(Publication publication) {
		this.getPublications().add(publication);
	}

	public void removePublication(Publication publication) {
		this.getPublications().remove(publication);
	}
}
