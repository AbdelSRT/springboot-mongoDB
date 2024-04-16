package com.stage.competietabel.service.mapper;

import com.stage.competietabel.controller.dto.res.PlayerResponse;
import com.stage.competietabel.controller.dto.res.PlayersResponse;
import com.stage.competietabel.controller.dto.res.TeamResponse;
import com.stage.competietabel.controller.dto.res.TeamsResponse;
import com.stage.competietabel.repository.model.Player;
import com.stage.competietabel.repository.model.Team;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerMapper {

    public static PlayersResponse mapPlayers(List<Player> players) {
        return new PlayersResponse(players.stream().map(PlayerMapper::mapPlayer).collect(Collectors.toList()));
    }

    public static PlayerResponse mapPlayer(Player player) {
        return new PlayerResponse(player.getId(), player.getApiId(), player.getName(), player.getFirstname(), player.getLastname(), player.getAge(), player.getBirth(), player.getNationality(), player.getHeight(), player.getWeight(), player.isInjured(), player.getPhoto(), player.getTeamId(), player.getNumber(), player.getPosition());
    }
}
