package com.devsuperior.curso.dslearn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.curso.dslearn.dto.DeliverRevisionDTO;
import com.devsuperior.curso.dslearn.entities.Deliver;
import com.devsuperior.curso.dslearn.repositories.DeliverRepository;

@Service
public class DeliverService {

	@Autowired
	private DeliverRepository repository;
	
	@Transactional
	public void saveRevision(Long id,DeliverRevisionDTO dto) {
		Deliver entity = repository.getReferenceById(id);
		entity.setCorrectCount(dto.getCorrectCount());
		entity.setFeedback(dto.getFeedback());
		entity.setStatus(dto.getStatus());
		repository.save(entity);
		
	}
	
}
