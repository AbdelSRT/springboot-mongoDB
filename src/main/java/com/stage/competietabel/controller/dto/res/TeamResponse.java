package com.stage.competietabel.controller.dto.res;

import com.stage.competietabel.repository.model.Player;
import com.stage.competietabel.repository.model.Venue;

import java.util.List;

public record TeamResponse(String Id, int api_id, String name, int foundedYear, String code, String country, String logo, boolean national, int wins,
                           int loss, int draw, int playedGames, VenueResponse venue, List<Player> players) {
}
