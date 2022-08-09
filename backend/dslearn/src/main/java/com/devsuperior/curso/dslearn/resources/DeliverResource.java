package com.devsuperior.curso.dslearn.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.curso.dslearn.dto.DeliverRevisionDTO;
import com.devsuperior.curso.dslearn.services.DeliverService;

@RestController
@RequestMapping("/deliveries")
public class DeliverResource {
	
	@Autowired
	private DeliverService service;
	
	@PreAuthorize("hasAnyRole('USER', 'INSTRUCTOR')") //Somente admin e instructor podem acessar o endpoint
	@PutMapping("/{id}")
	public ResponseEntity<Void> saveRevision(@PathVariable Long id,@RequestBody DeliverRevisionDTO dto){
		service.saveRevision(id, dto);
		return ResponseEntity.noContent().build();
	}

}
