package com.stage.competietabel.controller.dto.res;

public record TeamResponse(String Id ,String name, Integer foundedYear,String code, String country, String logo, Integer wins,
                           Integer loss, Integer draw, Integer playedGames) {
}
