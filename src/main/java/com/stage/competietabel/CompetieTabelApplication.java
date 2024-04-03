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



	@Bean
	CommandLineRunner runner(TeamRepository repository, MongoTemplate mongoTemplate){
		return args -> {


			String name = "Sifaw United";
			Team team = new Team(
					name,
					false,
					2020,
					"SIF",
					"Morooco",
					"hftc",
					"https://sifaw-league.be/wp-content/uploads/2019/11/sifaw.png",
					0,
					0,
					0,
					0
			);

			repository.findTeamByName(name)
					.ifPresentOrElse(s -> {
						System.out.println(s + " already exist");
					} , ()-> {
						System.out.println("Inserting team " + team);
						repository.insert(team);
					});
		};

	}
}