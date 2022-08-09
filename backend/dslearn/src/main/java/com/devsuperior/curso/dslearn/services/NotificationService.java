package com.devsuperior.curso.dslearn.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.devsuperior.curso.dslearn.dto.NotificationDTO;
import com.devsuperior.curso.dslearn.entities.Notification;
import com.devsuperior.curso.dslearn.entities.User;
import com.devsuperior.curso.dslearn.repositories.NotificationRepository;

@Service
public class NotificationService {
	
	@Autowired
	private NotificationRepository repository;
	
	@Autowired
	private AuthService authService;
	
	public Page<NotificationDTO> notificationsForCurrentUser(Pageable pageable){
		//Busca usuário logado
		User user =  authService.authenticated();
		//Busca somenete notificações do usuário
		Page<Notification> page = repository.findByUser(user, pageable);
		return page.map(x -> new NotificationDTO(x));
		
	}
	
}
