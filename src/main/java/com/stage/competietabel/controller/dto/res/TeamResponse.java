package com.stage.competietabel.controller.dto.res;

import com.stage.competietabel.repository.model.Venue;

public record TeamResponse(String Id, int api_id, String name, int foundedYear, String code, String country, String logo, boolean national, int wins,
                           int loss, int draw, int playedGames, Venue venue) {
}
