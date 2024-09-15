package com.diegodev.course;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication //essa anotação faz com que o spring boot saiba que essa classe é a responsavel por iniciar a aplicação
public class CourseApplication {

	public static void main(String[] args) {
		
		//no modelo em camandas essa é a minha aplicação, classe principal
		
		//Quando o programa é executado é aqui que é ponto de partida
		
		//SpringApplication informo que é uma aplicação em spring e quando eu chamo o metodo rum()
		//le configura todo o ambiente todos os componentes necessários, le os arquivos de configurações ex "aplication.properties"
		//e ajusta a aplicação com base nessas configurações 
		
		//inicia um servidor embutido ex(tomcat) faz com que sua aplicação receba requisições http
		
		//O Spring faz o component scanning, identificando classes com anotações como @Controller, @Service, @Repository,
		//e configura automaticamente essas classes no contexto da aplicação.
		
		// e no metodo run() eu passo a classe principal, sendo assim transformando essa classe como o ponto central 
		//para iniciar a aplicação e carrega todas as configurações e componentes.
		SpringApplication.run(CourseApplication.class, args);
		
		
	}
}
