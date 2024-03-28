package com.stage.competietabel.controller.dto.res;

public record UpdateTeamResponse(String name, Integer foundedYear, Integer wins,
                                 Integer loss, Integer draw, Integer playedGames) {
}
