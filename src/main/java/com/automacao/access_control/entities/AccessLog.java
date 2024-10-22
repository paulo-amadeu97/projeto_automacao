package com.automacao.access_control.entities;
import java.io.Serializable;
import java.time.Instant;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_accesslog")
public class AccessLog implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String userName;
	private String email;
	private Instant timestamp = Instant.now();
	
	
	public AccessLog(User data) {
		super();
		this.userName = data.getName();
		this.email = data.getEmail();
	}


	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getPassword() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
