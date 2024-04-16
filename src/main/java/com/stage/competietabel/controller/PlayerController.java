package com.stage.competietabel.controller;


import com.stage.competietabel.controller.dto.res.PlayerResponse;
import com.stage.competietabel.service.PlayerService;
import com.stage.competietabel.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/players")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class PlayerController {

    private final PlayerService playerService;

    @GetMapping("/{id}")
    public PlayerResponse getPlayerById(@PathVariable("id") String id) {
        return playerService.getPlayerById(id);
    }

}
