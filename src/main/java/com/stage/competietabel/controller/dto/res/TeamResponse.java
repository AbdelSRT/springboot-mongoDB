package com.stage.competietabel.controller.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stage.competietabel.repository.model.Player;
import com.stage.competietabel.repository.model.Venue;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record TeamResponse(String id, int apiId, String name, int foundedYear, String code, String country, String logo, boolean national, int wins,
                           int loss, int draw, int playedGames, VenueResponse venue, List<PlayerResponse> players) {
}
