package com.stage.competietabel;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

@SpringBootApplication
public class CompetieTabelApplication {

	public static void main(String[] args) {
		SpringApplication.run(CompetieTabelApplication.class, args);
	}


	@Bean
	CommandLineRunner runner(TeamRepository repository, MongoTemplate mongoTemplate){
		return args -> {
			String name = "Barcelona";
			Team team = new Team(
					name,
					2020,
					0,
					0,
					0,
					0
			);
		/*
			Query query = new Query();
			query.addCriteria(Criteria.where("name").is(name));

			List<Team> teams = mongoTemplate.find(query, Team.class);

			if (teams.size() > 1){
				throw new IllegalStateException("found many team with name " + name);
			}

			if (teams.isEmpty()){
				repository.insert(team);
			}*/
			
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