package com.stage.competietabel.repository;

import com.stage.competietabel.repository.model.Team;
import com.stage.competietabel.service.dto.TeamData;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TeamRepository extends MongoRepository<Team, String> {
    Optional<Team> findTeamByName(String name);

    Team findByApiId(int teamId);

}

