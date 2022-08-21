package com.devsuperior.curso.dslearn.resources;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.curso.dslearn.dto.NotificationDTO;
import com.devsuperior.curso.dslearn.services.NotificationService;

@RestController
@RequestMapping("/notifications")
public class NotificationResource {
	
	@Autowired
	private NotificationService service;
	
	@GetMapping
	public ResponseEntity<Page<NotificationDTO>> notificationsForCurrentUser(Pageable pageable,
			@RequestParam(name = "unreadOnly",defaultValue = "false") boolean unreadOnly){
		Page<NotificationDTO> page = service.notificationsForCurrentUser(pageable,unreadOnly);
		return ResponseEntity.ok(page);
	}

}
