package com.stage.competietabel.service.mapper;

import com.stage.competietabel.controller.dto.res.VenueResponse;
import com.stage.competietabel.repository.model.Venue;

public class VenueMapper {
    public static VenueResponse mapVenue(Venue venue) {
        return new VenueResponse(venue.getId(), venue.getName(), venue.getAddress(), venue.getCity(), venue.getCapacity(), venue.getSurface(), venue.getImage());
    }
}
