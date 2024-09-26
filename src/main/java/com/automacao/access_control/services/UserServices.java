package com.automacao.access_control.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automacao.access_control.entities.User;
import com.automacao.access_control.repositories.UserRepository;

@Service
public class UserServices {

	@Autowired
	private UserRepository repository;
	
	public User findById(Long id) {
		Optional<User> obj = repository.findById(id);
		return obj.get();
	}
}
