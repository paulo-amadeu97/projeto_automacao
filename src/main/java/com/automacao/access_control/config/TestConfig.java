package com.automacao.access_control.config;

//import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import com.automacao.access_control.dto.RegisterDTO;
import com.automacao.access_control.enuns.UserPermissions;
import com.automacao.access_control.resources.AuthenticationResource;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner{

	
	@Autowired
	private AuthenticationResource auth;
	
	@Override
	public void run(String... args) throws Exception {
		
		//StateData stdt1 = new StateData(35.0, 60.0);
		
		RegisterDTO registerTest = new RegisterDTO("Usuario teste", "teste@teste.com", "12345678", UserPermissions.ADMIN);
		
		registerTest = registerTest.withRfid("c39fe7f6");
		
		auth.register(registerTest);
		
		//stateDataRepository.saveAll(Arrays.asList(stdt1));
	}
}
