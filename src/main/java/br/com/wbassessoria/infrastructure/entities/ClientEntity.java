package br.com.wbassessoria.infrastructure.entities;

import org.springframework.data.mongodb.core.mapping.Document;

import br.com.wbassessoria.infrastructure.entities.inheritance.UserAbstract;

@Document(collection = "client")
public class ClientEntity extends UserAbstract {
	
	private String cpf;
	private String phone;
	
	public ClientEntity() {
	}
	
	public ClientEntity(String id, String cpf, String name, String email, String phone, String password) {
		super(id, name, email, password);
		this.cpf = cpf;
		this.phone = phone;
	}
	
    public ClientEntity(Builder builder) {
    	id = builder.id;
    	cpf = builder.cpf;
    	name = builder.name;
    	email = builder.email;
    	phone = builder.phone;
    	password = builder.password;
    }
		
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public static class Builder {

        private String id;
        private String cpf;
        private String name;
        private String email;
        private String phone;
        private String password;

        public Builder id(String value) {
        	id = value;
            return this;
        }

        public Builder cpf(String value) {
        	cpf = value;
            return this;
        }


        public Builder name(String value) {
        	name = value;
            return this;
        }

        public Builder email(String value) {
        	email = value;
            return this;
        }

        public Builder phone(String value) {
        	phone = value;
            return this;
        }

        public Builder password(String value) {
        	password = value;
            return this;
        }

        public ClientEntity build() {
            return new ClientEntity(this);
        }
    }
}

