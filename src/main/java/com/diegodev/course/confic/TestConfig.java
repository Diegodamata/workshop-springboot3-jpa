package com.diegodev.course.confic;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.diegodev.course.entities.User;
import com.diegodev.course.repositories.UserRepository;

@Configuration //indica que essa classe deve ser utilizada durante a fase de configuração da aplicação
@Profile("test") //significa que essa configuração será aplicada somente quando a aplicação estiver rodando 
//no perfil de ambiente de teste. Isso é útil para criar diferentes configurações para diferentes ambientes, como dev, test e prod.

public class TestConfig implements CommandLineRunner{ //A interface CommandLineRunner permite que você execute um código específico logo após a inicialização da aplicação.

	@Autowired //minha injeção de dependencia, com essa anotação o spring injeta automaticamente a instancia do userRepository
	private UserRepository userRepository;

	@Override
	public void run(String... args) throws Exception {
		
		User u1 = new User(null, "Maria Brown", "maria@gmail.com", "988888888", "123456");
		User u2 = new User(null, "Alex Green", "alex@gmail.com", "977777777", "123456");
		
		userRepository.saveAll(Arrays.asList(u1, u2)); //chamo o userRepository ele chama o metodo da interface JpaRepository, para salvar no banco de dados
	}
}
