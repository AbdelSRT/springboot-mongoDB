package com.stage.competietabel.controller.dto.res;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record VenueResponse(String id, String name, String address, String city, int capacity, String surface, String image) {
}
