package com.metafour.jpa.publications.repository;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.metafour.jpa.publications.bean.Publisher;
import com.metafour.jpa.publications.repository.PublisherRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PublisherTest {
	@Autowired
	private PublisherRepository publisherRepository;

	@Test
	public void test1Up() {
		assertThat(publisherRepository).isNotNull();
	}

	private Publisher sample() {
		Publisher publisher = new Publisher();
		publisher.setName("Metafour");
		return publisher;
	}

	@Test
	public void test3InsertBook() {
		Publisher publisher = publisherRepository.save(sample());
		assertThat(publisher).isNotNull();
	}

	@Test
	public void test3FindBook() {
		Iterable<Publisher> publishers = publisherRepository.findAll();
		assertThat(publishers).isNotNull();
		assertThat(publishers).isNotEmpty();
	}
}
