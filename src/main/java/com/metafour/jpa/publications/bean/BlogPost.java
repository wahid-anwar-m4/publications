package com.metafour.jpa.publications.bean;

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
	private boolean published;
}
