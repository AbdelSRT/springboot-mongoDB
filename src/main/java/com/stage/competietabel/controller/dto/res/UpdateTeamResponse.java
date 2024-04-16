package com.stage.competietabel.controller.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record UpdateTeamResponse(String name, Integer foundedYear, Integer wins,
                                 Integer loss, Integer draw, Integer playedGames) {
}
