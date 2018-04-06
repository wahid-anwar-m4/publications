package com.metafour.jpa.publications.bean;

import java.io.Serializable;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@Entity
public class BlogPost extends Publication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6316847922270920024L;
	private String subject;
	private String summary;
	private boolean published;
}
