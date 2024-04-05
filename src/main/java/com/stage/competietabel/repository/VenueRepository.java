package com.stage.competietabel.repository;

import com.stage.competietabel.repository.model.Venue;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface VenueRepository extends MongoRepository<Venue, String> {
}
