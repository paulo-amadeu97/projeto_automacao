package com.automacao.access_control.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.automacao.access_control.entities.User;
import com.automacao.access_control.repositories.UserRepository;

@Controller
@RequestMapping(value = "/user")
public class UserResource {
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		User obj = repository.findById(id).orElse(null);
		if(obj != null) {
			return ResponseEntity.ok().body(obj);
		}
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping("/")
	public ResponseEntity<Void> saveUser(@RequestBody User data) {
		User obj = new User(data);
		repository.save(obj);
		return ResponseEntity.ok().build();
	}
}
