package com.stage.competietabel.service.mapper;

import com.stage.competietabel.controller.dto.res.TeamResponse;
import com.stage.competietabel.controller.dto.res.TeamsResponse;
import com.stage.competietabel.repository.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {
    public static TeamsResponse mapTeams(List<Team> teams) {
        return new TeamsResponse(teams.stream().map(team -> TeamMapper.mapTeam(team, false)).collect(Collectors.toList()));
    }

    public static TeamResponse mapTeam(Team team, boolean compact) {
        return new TeamResponse(team.getId(),
            team.getApiId(),
            team.getName(),
            team.getFoundedYear(),
            team.getCode(),
            team.getCountry(),
            team.getLogo(),
            team.isNational(),
            team.getWins(),
            team.getLoss(),
            team.getDraw(),
            team.getPlayedGames(),
            VenueMapper.mapVenue(team.getVenue()),
            compact ? null : team.getPlayers().stream().map(PlayerMapper::mapPlayer).collect(Collectors.toList()));
        
    }
}
