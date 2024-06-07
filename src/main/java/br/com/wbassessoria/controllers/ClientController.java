package br.com.wbassessoria.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.wbassessoria.infrastructure.entities.ClientEntity;
import br.com.wbassessoria.services.ClientService;


@RestController
@RequestMapping(value = "/clients")
public class ClientController {
	
	@Autowired
	ClientService service;
	
	@GetMapping
	public ResponseEntity<List<ClientEntity>> findAll() {
		List<ClientEntity> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<ClientEntity> findById(@PathVariable String id) {
		ClientEntity obj = service.findById(id);
	    return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/email/{email}")
	public ResponseEntity<ClientEntity> findByEmail(@PathVariable String email) {
		ClientEntity obj = service.findByEmail(email);
	    return ResponseEntity.ok().body(obj);
	}
	
	@GetMapping(value = "/cpf/{cpf}")
	public ResponseEntity<ClientEntity> findByCpf(@PathVariable String cpf) {
		ClientEntity obj = service.findByCpf(cpf);
	    return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<ClientEntity> insert(@RequestBody ClientEntity client) {
		ClientEntity obj = service.insert(client);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj).toUri();
	    return ResponseEntity.created(uri).body(obj);
	}

	@PostMapping(value = "/login")
	public ResponseEntity<Void> login(@RequestBody ClientEntity client) {
		service.login(client);
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<ClientEntity> update(@PathVariable String id, @RequestBody ClientEntity client) {
		ClientEntity obj = service.update(id, client);
	    return ResponseEntity.ok().body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable String id) {
		service.delete(id);
	    return ResponseEntity.noContent().build();
	}
}
