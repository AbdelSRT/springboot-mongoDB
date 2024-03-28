package com.stage.competietabel.controller;

import com.stage.competietabel.controller.dto.req.UpdateTeamRequest;
import com.stage.competietabel.controller.dto.res.TeamResponse;
import com.stage.competietabel.controller.dto.res.TeamsResponse;
import com.stage.competietabel.repository.model.Team;
import com.stage.competietabel.controller.dto.req.NewTeamRequest;
import com.stage.competietabel.repository.TeamRepository;
import com.stage.competietabel.service.TeamService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teams")
@AllArgsConstructor
@CrossOrigin(origins = "*")
public class TeamController {

    private final TeamService teamService;
    private final TeamRepository teamRepository;

    @GetMapping
    public TeamsResponse fetchAllTeams(){
        return teamService.getAllTeams();
    }

    @GetMapping("/{id}")
    public TeamResponse fetchTeamById(@PathVariable("id") String id){
        return teamService.getTeam(id);
    }


    @PostMapping
    public TeamResponse addTeam(@RequestBody NewTeamRequest team){
        return teamService.addTeam(team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable("id") String id){
        teamService.delTeam(id);
    }

    @PutMapping("/{id}")
    public void updateTeam(@PathVariable("id") String id, @RequestBody UpdateTeamRequest team){
        teamService.updateTeamRecord(id ,team);
    }
}
