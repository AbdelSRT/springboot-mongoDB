package com.stage.competietabel.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stage.competietabel.repository.model.Venue;

public record TeamRecord(@JsonProperty("team") TeamData teamData, VenueData venue) {
}
