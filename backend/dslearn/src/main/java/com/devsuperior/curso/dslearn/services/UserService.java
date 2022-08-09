package com.devsuperior.curso.dslearn.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.curso.dslearn.dto.UserDTO;
import com.devsuperior.curso.dslearn.entities.User;
import com.devsuperior.curso.dslearn.repositories.UserRepository;
import com.devsuperior.curso.dslearn.services.exceptions.ResourceNotFoundException;

@Service
public class UserService  implements UserDetailsService{
	
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthService authService;
	private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		authService.validateSelfOrAdmin(id);
		
		User entity = repository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not found"));
		return new UserDTO(entity);
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if(user == null) {
			logger.error("Email not found: " + username );
			throw new UsernameNotFoundException("Email not found");
		}
		logger.info("Email found: " + username);
		return user;
	}

}
