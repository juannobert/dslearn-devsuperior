package com.devsuperior.curso.dslearn.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.curso.dslearn.entities.User;
import com.devsuperior.curso.dslearn.repositories.UserRepository;
import com.devsuperior.curso.dslearn.services.exceptions.ForbiddenException;
import com.devsuperior.curso.dslearn.services.exceptions.UnauthorizedException;

@Service
public class AuthService {
	
	@Autowired
	private UserRepository repository;
	
	@Transactional(readOnly = true)
	public User authenticated() {
		try {
		//Busca username do usuário logado
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		User user = repository.findByEmail(username);
		return user;
		//Caso usuário não exista 
		}catch(Exception e) {
			throw new UnauthorizedException("Invalid user");
		}
	}
	
	public void validateSelfOrAdmin(Long userId) {
		User user = authenticated();
		//Verifica se o id do usuário é diferente e ele não é admin 
		if(!user.getId().equals(userId) && !user.hasHole("ROLE_ADMIN") ) {
			throw new ForbiddenException("Access denied");
		}
	}
}
