package br.com.wbassessoria.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.core.MongoTemplate;

import br.com.wbassessoria.infrastructure.entities.ClientEntity;
import br.com.wbassessoria.repositories.ClientRepository;


@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {
		
    @Autowired
    private MongoTemplate mongoTemplate;
	
	@Autowired
	private ClientRepository clientRepository;
	
	@Override
	public void run(String... args) throws Exception {
        mongoTemplate.getDb().drop();
		
        ClientEntity uc1 = new ClientEntity(null, "951.954.148-88", "Maria Brown", "maria@gmail.com", "11988888888", "65cc4123");
        ClientEntity uc2 = new ClientEntity(null, "152.361.141-64", "Alex Green", "alex@gmail.com", "11977777777", "123cc456");
        ClientEntity uc3 = new ClientEntity(null, "142.141.391-51", "James Red", "james@gmail.com", "11966666666", "654cc321");
		
		clientRepository.saveAll(Arrays.asList(uc1, uc2, uc3));
	}
}