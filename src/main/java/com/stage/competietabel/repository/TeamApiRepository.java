package com.stage.competietabel.repository;

import com.stage.competietabel.repository.model.TeamApi;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeamApiRepository extends MongoRepository<TeamApi, String> {
}
