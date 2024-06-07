package br.com.wbassessoria.repositories;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.wbassessoria.infrastructure.entities.ClientEntity;


public interface ClientRepository extends MongoRepository<ClientEntity, String> {
	
	public Optional<ClientEntity> findByCpf(String cpf);
	
	public Optional<ClientEntity> findByEmail(String email);
}