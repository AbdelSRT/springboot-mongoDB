package com.stage.competietabel.controller.dto.res;

import com.stage.competietabel.repository.model.Team;
import com.stage.competietabel.service.dto.Birth;

public record PlayerResponse(String Id, int api_id, String name, String firstname, String lastname, int age, Birth birth, String nationality, String height, String weight, boolean injured, String photo, Team team) {
}
