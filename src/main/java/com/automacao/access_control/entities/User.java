package com.automacao.access_control.entities;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	private String email;
	private String password;
	private char status = 'A';
	private Instant dateCreate;
	private Long userCreateId;
	
	public User() {
		
	}

	public User(String name, String email, String password, Long userCreateId) {
		super();
		this.name = name;
		this.setEmail(email);;
		this.setPassword(password);
		this.dateCreate = Instant.now();
		this.userCreateId = userCreateId;
	}
	
	public User(User data) {
		super();
		this.name = data.name;
		this.setEmail(data.email);;
		this.password = data.password;
		this.status = data.status;
		this.dateCreate = Instant.now();
		this.userCreateId = data.userCreateId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if(this.isValidEmail(email)) {
			this.email = email;
		} else {
			throw new IllegalArgumentException("Email invalid");
		}
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		if(password == null) {
			throw new IllegalArgumentException("Password cannot be null");
		} else {
			String passRegex = "^[a-zA-Z0-9]{8,}$";
			Pattern pattern = Pattern.compile(passRegex);
			Matcher matcher = pattern.matcher(password);
			if(matcher.matches()) {
				this.password = this.encryptPassword(password);
			} else {
				throw new IllegalArgumentException("Password is invalid. It must have at least 8 alphanumeric characters");
			}
		}
		
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		if(status != 'I' || status != 'A') {
			throw new IllegalArgumentException("Status Invalid");
		} else {
			this.status = status;
		}
	}

	public Long getUserCreateId() {
		return userCreateId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(id, other.id);
	}
	
	private boolean isValidEmail(String email) {
		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
		
		Pattern pattern = Pattern.compile(emailRegex);
		if(email == null) {
			return false;
		}
		
		Matcher matcher = pattern.matcher(email);
		return matcher.matches();
	}
	
	private String encryptPassword(String password) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-256");
			
			byte[] hash = digest.digest(password.getBytes());
			
			StringBuilder hexString = new StringBuilder();
			
			for(byte b : hash) {
				String hex = Integer.toHexString(0xff & b);
				if (hex.length() == 1) {
					hexString.append('0');
				}
				hexString.append(hex);
			}
			
			return hexString.toString();
			
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("error to encrypt", e);
		}
	}

	public Instant getDateCreate() {
		return dateCreate;
	}
}
