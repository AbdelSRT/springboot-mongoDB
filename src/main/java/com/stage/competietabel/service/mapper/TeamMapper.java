package com.stage.competietabel.service.mapper;

import com.stage.competietabel.controller.dto.res.TeamResponse;
import com.stage.competietabel.controller.dto.res.TeamsResponse;
import com.stage.competietabel.repository.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public class TeamMapper {
    public static TeamsResponse mapTeams(List<Team> teams){
        return new TeamsResponse(teams.stream().map(TeamMapper::mapTeam).collect(Collectors.toList()));
    }
    public static TeamResponse mapTeam(Team team){
        return new TeamResponse(team.getId(), team.getName(), team.getFoundedYear(), team.getWins(), team.getLoss(), team.getDraw(), team.getPlayedGames());
    }

}
