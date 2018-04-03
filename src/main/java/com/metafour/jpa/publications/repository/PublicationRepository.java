package com.metafour.jpa.publications.repository;

import org.springframework.data.repository.CrudRepository;

import com.metafour.jpa.publications.bean.Publication;

public interface PublicationRepository extends CrudRepository<Publication, Long> {

}
