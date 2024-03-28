package com.stage.competietabel.service;

import com.stage.competietabel.controller.dto.req.NewTeamRequest;
import com.stage.competietabel.controller.dto.req.UpdateTeamRequest;
import com.stage.competietabel.controller.dto.res.TeamResponse;
import com.stage.competietabel.controller.dto.res.TeamsResponse;
import com.stage.competietabel.controller.dto.res.UpdateTeamResponse;
import com.stage.competietabel.repository.TeamRepository;
import com.stage.competietabel.repository.model.Team;
import com.stage.competietabel.service.mapper.TeamMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public TeamsResponse getAllTeams() {
        return TeamMapper.mapTeams(teamRepository.findAll());
    }

    public TeamResponse getTeam(String id) {
        Optional<Team> byId = teamRepository.findById(id);
        return byId.map(TeamMapper::mapTeam).orElse(null);

    }


    public TeamResponse addTeam(NewTeamRequest request){
        Team team = new Team();
        team.setName(request.name());
        team.setFoundedYear(request.foundedYear());
        return TeamMapper.mapTeam(teamRepository.save(team));
    }

    public void delTeam(String id){
        teamRepository.deleteById(id);
    }

    public void updateTeamRecord(String id, UpdateTeamRequest request){
        Optional<Team> myTeam = teamRepository.findById(id);
        if (myTeam.isPresent()) {
            Team team = myTeam.get();
            team.setName(request.name());
            team.setFoundedYear(request.foundedYear());
            team.setDraw(request.draw());
            team.setWins(request.wins());
            team.setLoss(request.loss());
            team.setPlayedGames(request.playedGames());
            teamRepository.save(team);
        }

    }
}
