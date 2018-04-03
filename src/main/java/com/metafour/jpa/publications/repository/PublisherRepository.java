package com.metafour.jpa.publications.repository;

import org.springframework.data.repository.CrudRepository;

import com.metafour.jpa.publications.bean.Publisher;

public interface PublisherRepository extends CrudRepository<Publisher, Long> {

}
