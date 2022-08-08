package com.devsuperior.curso.dslearn.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperior.curso.dslearn.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, Long> {

}
