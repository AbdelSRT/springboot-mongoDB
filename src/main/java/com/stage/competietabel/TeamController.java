package com.stage.competietabel;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/teams")
@AllArgsConstructor
public class TeamController {

    private final TeamService teamService;
    private final TeamRepository teamRepository;

    @GetMapping
    public List<Team> fetchAllTeams(){
        return teamService.getAllTeams();
    }


    @PostMapping
    public Team addTeam(@RequestBody TeamService.NewTeamRequest team){
        return teamService.addTeam(team);
    }

    @DeleteMapping("/{id}")
    public void deleteTeam(@PathVariable("id") Integer id){
        teamService.delTeam(id);
    }
}
