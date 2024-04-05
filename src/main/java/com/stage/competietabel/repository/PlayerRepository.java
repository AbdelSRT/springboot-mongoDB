package com.stage.competietabel.repository;

import com.stage.competietabel.repository.model.Player;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlayerRepository extends MongoRepository<Player, String> {
}
