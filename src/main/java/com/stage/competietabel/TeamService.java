package com.stage.competietabel;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.Inet4Address;
import java.util.List;

@AllArgsConstructor
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    record NewTeamRequest(String name, Integer founded_year){}
    public Team addTeam(NewTeamRequest request){
        Team team = new Team();
        team.setName(request.name);
        team.setFounded_year(request.founded_year);
        return teamRepository.save(team);
    }

    public Team delTeam(Integer id){
        return teamRepository.deleteById(id);
    }
}
