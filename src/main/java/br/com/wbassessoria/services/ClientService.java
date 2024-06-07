package br.com.wbassessoria.services;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.wbassessoria.infrastructure.entities.ClientEntity;
import br.com.wbassessoria.repositories.ClientRepository;
import br.com.wbassessoria.services.exceptions.InvalidFormatException;
import br.com.wbassessoria.services.exceptions.LoginInvalidException;
import br.com.wbassessoria.services.exceptions.ResourceNotFoundException;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	
	public List<ClientEntity> findAll() {		
		return repository.findAll();
	}
	
	public ClientEntity findById(String id) {
		try {
			return repository.findById(id).get();
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id", id);
		}
	}
	
	public ClientEntity findByCpf(String cpf) {
		try {
			return repository.findByCpf(cpf).get();
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("CPF", cpf);
		}
	}
	
	public ClientEntity findByEmail(String email) {
		try {
			return repository.findByEmail(email).get();
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("E-mal", email);
		}
	}
	
	public ClientEntity insert(ClientEntity client) {
		try {
			client.setId(null);
			checkFields(client);
			return repository.save(client);
		} catch (InvalidFormatException e){
			throw new InvalidFormatException(e.getMessage());	
		}
	}
	
	public ClientEntity update(String id, ClientEntity client) {
		try {
			ClientEntity obj = repository.findById(id).get();
			updateData(obj, client);
			obj.setId(id);
			checkFields(obj);
			return repository.save(obj);
		} catch (InvalidFormatException e){
			throw new InvalidFormatException(e.getMessage());	
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id", id);
		}
	}
	
	@Transactional
	public void delete(String id) {
		try {
			ClientEntity client = repository.findById(id).get();
			repository.delete(client);
		} catch (NoSuchElementException e) {
			throw new ResourceNotFoundException("Id", id);
		}
	}

	public boolean login(ClientEntity client) {
		ClientEntity clientDatabase = repository.findByEmail(client.getEmail()).orElseThrow(LoginInvalidException::new);
		if (clientDatabase.getPassword().equals(client.getPassword()))
			return true;
		return false;
	}
	
	private void updateData(ClientEntity obj, ClientEntity client) {
		obj.setCpf((client.getCpf() == null) ? obj.getCpf() : client.getCpf());
		obj.setName((client.getName() == null) ? obj.getName() : client.getName());
		obj.setEmail((client.getEmail() == null) ? obj.getEmail() : client.getEmail());
		obj.setPhone((client.getPhone() == null) ? obj.getPhone() : client.getPhone());
		obj.setPassword((client.getPassword() == null) ? obj.getPassword() : client.getPassword());
	}
	
	private void checkFields(ClientEntity client) throws InvalidFormatException {
		if (client == null) throw new InvalidFormatException("Os campos não podem ser nulos");
		
		isNullOrBlank(client.getCpf(), "CPF");
		if (!client.getCpf().matches("^[0-9]{3}.[0-9]{3}.[0-9]{3}-[0-9]{2}$")) 
			throw new InvalidFormatException("CPF", client.getCpf());
		
		isNullOrBlank(client.getName(), "name");
		if (!client.getName().matches("^[a-zA-Z ]+$")) 
			throw new InvalidFormatException("Name", client.getName());
		
		isNullOrBlank(client.getEmail(), "e-mail");
		if (!client.getEmail().matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$")) 
			throw new InvalidFormatException("E-mail", client.getEmail());
		
		isNullOrBlank(client.getPhone(), "phone");
		if (!client.getPhone().matches("\\(?\\d{2}\\)? ?(?:\\d{4,5}-?\\d{4}|\\d{4}-?\\d{4})$")) 
			throw new InvalidFormatException("Phone", client.getPhone());
		
		isNullOrBlank(client.getPassword(), "password");
		if (!(client.getPassword().length() >= 8))
			throw new InvalidFormatException("Password");
	}
	
	private void isNullOrBlank(String string, String field) throws InvalidFormatException {
		if (string == null || string.isBlank()) 
			throw new InvalidFormatException("The " + field + " can not be null.");
	}
}

