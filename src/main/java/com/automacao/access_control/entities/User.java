package com.automacao.access_control.entities;

import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.engine.spi.ExtendedSelfDirtinessTracker;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.automacao.access_control.enuns.UserPermissions;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_user")
public class User implements Serializable, UserDetails{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String name;
	
	@Column(unique = true)
	private String email;
	private String password;
	
	@Column(unique = true)
	private String rfid;
	private char status = 'A';
	private Instant dateCreate;
	private Long userCreateId;
	
	private UserPermissions permissions;
	
	public User() {
		
	}

	public User(String name, String email, String password, UserPermissions permission) {
		super();
		this.name = name;
		this.setEmail(email);;
		this.password = password;
		this.dateCreate = Instant.now();
		this.permissions = permission;
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

	public String getRfid() {
		return rfid;
	}

	public void setRfid(String i) {
		this.rfid = i;
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

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		if(status != 'I' && status != 'A') {
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
	

	public Instant getDateCreate() {
		return dateCreate;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.permissions == UserPermissions.ADMIN) {
			return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
		} else {
			return List.of(new SimpleGrantedAuthority("ROLE_USER"));
		}
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}
}
