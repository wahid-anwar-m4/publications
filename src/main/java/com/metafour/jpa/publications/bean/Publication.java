package com.metafour.jpa.publications.bean;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@Entity
//@Inheritance(strategy = InheritanceType.JOINED)
public class Publication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4967995887102879893L;
	@Id
	@GeneratedValue
	private Long id;
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
	
	@Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Publisher)) {
            return false;
        }
        Publisher other = (Publisher) object;
        if ((this.id == null && other.getId()!= null) || (this.id != null && !this.id.equals(other.getId()))) {
            return false;
        }
        return true;
    }
}
