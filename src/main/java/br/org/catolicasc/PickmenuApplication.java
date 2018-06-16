package br.org.catolicasc;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;


@ComponentScan(basePackages= {"br.org.catolicasc"})

@Configuration
@EnableAutoConfiguration
@EntityScan
@SpringBootApplication
public class PickmenuApplication {

	public static void main(String[] args) {
		SpringApplication.run(PickmenuApplication.class, args);
	}
	
	
}
