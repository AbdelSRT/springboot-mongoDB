package com.stage.competietabel.service.dto;

import com.stage.competietabel.repository.model.Team;

public record TeamData(int id, String name, String code, String country, int founded, String logo, boolean national) {
}
