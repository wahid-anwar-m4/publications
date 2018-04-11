package com.metafour.jpa.publications.bean;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
@Entity
public class BlogPost extends Publication {
	private String subject;
	private String summary;
	private String body;
	private int pageStart;
	private int pageEnd;
	private int wordCount;
	private String about;
	@ElementCollection
	private List<String> keyWords;
	private boolean published;
}
