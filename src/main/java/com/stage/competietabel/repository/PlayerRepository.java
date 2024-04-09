package com.stage.competietabel.repository;

import com.stage.competietabel.repository.model.Player;
import com.stage.competietabel.repository.model.Team;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface PlayerRepository extends MongoRepository<Player, String> {

}
