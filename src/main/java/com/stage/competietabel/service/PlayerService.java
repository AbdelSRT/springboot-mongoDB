package com.stage.competietabel.service;

import com.stage.competietabel.controller.dto.res.PlayerResponse;
import com.stage.competietabel.repository.PlayerRepository;
import com.stage.competietabel.repository.model.Player;
import com.stage.competietabel.service.mapper.PlayerMapper;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlayerService {

    public final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public PlayerResponse getPlayerById(String id) {
        Optional<Player> byId = playerRepository.findPlayerById(id);
        return byId.map(PlayerMapper::mapPlayer).orElse(null);
    }
}
