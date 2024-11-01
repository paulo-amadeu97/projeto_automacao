package com.automacao.access_control.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.automacao.access_control.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{
	User findByEmail(String email);
	User findByRfid(String rfid);
	//User findByEmailUser(String email);
}
