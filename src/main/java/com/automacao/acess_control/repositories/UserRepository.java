package com.automacao.acess_control.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automacao.acess_control.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
