package com.stage.competietabel.service.dto;

import com.stage.competietabel.repository.model.Team;

public record ApiPlayer(int id, String name, String firstname, String lastname, int age, Birth birth, String nationality, String height, String weight, boolean injured, String photo, Team team) {
}
