package com.metafour.jpa.publications.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.metafour.jpa.publications.bean.Publication;
import com.metafour.jpa.publications.repository.PublicationRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PublicationTest {
	@Autowired
	private PublicationRepository publicationRepository;

	@Test
	public void test1Up() {
		assertThat(publicationRepository).isNotNull();
	}

	private Publication sample() {
		Publication publication = new Publication();
		publication.setTitle("Test");
		publication.setPublicationDate(LocalDate.now());
		return publication;
	}

	@Test
	public void test3InsertBook() {
		Publication publication = publicationRepository.save(sample());
		assertThat(publication).isNotNull();
	}

	@Test
	public void test3FindBook() {
		Iterable<Publication> publications = publicationRepository.findAll();
		assertThat(publications).isNotNull();
		assertThat(publications).isNotEmpty();
	}
}
