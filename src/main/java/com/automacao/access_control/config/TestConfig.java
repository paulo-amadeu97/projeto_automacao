package com.automacao.access_control.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.automacao.access_control.entities.StateData;
import com.automacao.access_control.entities.User;
import com.automacao.access_control.repositories.StateDataRepository;
import com.automacao.access_control.repositories.UserRepository;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	@Autowired
	StateDataRepository stateDataRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@Override
	public void run(String... args) throws Exception {
		
		StateData stdt1 = new StateData(35.0, 60.0);
		
		User user1 = new User("Paulo Mendonca", "paulo.mendonca@gmail.com", "12345678", null);
		
		stateDataRepository.saveAll(Arrays.asList(stdt1));
		userRepository.saveAll(Arrays.asList(user1));
		
	}
}
