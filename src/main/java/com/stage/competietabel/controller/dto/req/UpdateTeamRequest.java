package com.stage.competietabel.controller.dto.req;

public record UpdateTeamRequest(String name, Integer foundedYear, Integer wins,
                                Integer loss, Integer draw, Integer playedGames) {
}
