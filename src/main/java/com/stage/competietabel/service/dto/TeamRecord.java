package com.stage.competietabel.service.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record TeamRecord(@JsonProperty("team") TeamData teamData) {
}
