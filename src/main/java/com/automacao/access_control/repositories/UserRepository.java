package com.automacao.access_control.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automacao.access_control.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
