package com.automacao.access_control.resources;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.metadata.TableMetaDataContext;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.automacao.access_control.dto.AuthenticationDTO;
import com.automacao.access_control.dto.EmailDTO;
import com.automacao.access_control.dto.LoginResponseDTO;
import com.automacao.access_control.dto.RegisterDTO;
import com.automacao.access_control.dto.RfidDTO;
import com.automacao.access_control.entities.User;
import com.automacao.access_control.infra.TokenService;
import com.automacao.access_control.repositories.UserRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
public class AuthenticationResource {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired 
	private UserRepository repository;
	
	@Autowired
	private TokenService tokenService;
	

	@PostMapping("/login")
	public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid  AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);
		
		var token = tokenService.generateToken((User) auth.getPrincipal());
		
		return ResponseEntity.ok(new LoginResponseDTO(token));
	}
	
	@PostMapping("/register")
	public ResponseEntity<RegisterDTO> register(@RequestBody @Valid RegisterDTO data) {
		if(this.repository.findByEmail(data.email()) != null) {
			return ResponseEntity.badRequest().build();
		}
		
		String passRegex = "^[a-zA-Z0-9]{8,}$";
		Pattern pattern = Pattern.compile(passRegex);
		Matcher matcher = pattern.matcher(data.password());
		
		if(!matcher.matches()) {
			
			throw new IllegalArgumentException("Password is invalid. It must have at least 8 alphanumeric characters");	
			
		}else {
			
			String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
			User newUser = new User(data.name(), data.email(), encryptedPassword, data.permission());
			newUser.setRfid(data.rfid());
			
			this.repository.save(newUser);
			
			return ResponseEntity.ok().build();
		}
	}
	
	@PostMapping("/register/rfid")
	public ResponseEntity<RegisterDTO> registerRfid(@RequestBody @Valid EmailDTO email){
		if(this.repository.findByEmail(email.email()) == null) {
			return ResponseEntity.badRequest().build();
		}
		
		User user = this.repository.findByEmailUser(email.email());
		
		
		
		user.setRfid(rfid);
	}
}
