package com.stage.competietabel;

import com.stage.competietabel.repository.TeamRepository;
import com.stage.competietabel.repository.model.Team;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;

@SpringBootApplication
public class CompetieTabelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetieTabelApplication.class, args);
	}
}