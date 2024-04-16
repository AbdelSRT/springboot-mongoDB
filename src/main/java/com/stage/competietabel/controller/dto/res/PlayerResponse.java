package com.stage.competietabel.controller.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.stage.competietabel.repository.model.Team;
import com.stage.competietabel.service.dto.Birth;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record PlayerResponse(String id, int apiId, String name, String firstname, String lastname, int age, Birth birth, String nationality, String height, String weight, boolean injured, String photo, String teamId, int number, String position) {
}
