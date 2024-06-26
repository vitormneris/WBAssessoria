package br.com.wbassessoria.infrastructure.entities.inheritance;

import org.springframework.data.annotation.Id;

public abstract class UserAbstract {
	
	@Id
	protected String id;
	protected String name;
	protected String email;
	protected String password;
		
	public UserAbstract() {
	}
	
	public UserAbstract(String id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
}
