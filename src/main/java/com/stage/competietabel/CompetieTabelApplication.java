package com.stage.competietabel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CompetieTabelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetieTabelApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(TeamRepository repository){
		return arg -> {
			Team team = new Team(
					"Barcelona",
					2020,
					0,
					0,
					0,
					0
			);
			repository.insert(team);
		};

	}
}
