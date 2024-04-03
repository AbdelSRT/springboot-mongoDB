package com.stage.competietabel.service;

import com.stage.competietabel.controller.dto.req.NewTeamRequest;
import com.stage.competietabel.controller.dto.req.UpdateTeamRequest;
import com.stage.competietabel.controller.dto.res.TeamResponse;
import com.stage.competietabel.controller.dto.res.TeamsResponse;
import com.stage.competietabel.repository.TeamRepository;
import com.stage.competietabel.repository.model.Team;
import com.stage.competietabel.service.dto.TeamData;
import com.stage.competietabel.service.dto.TeamRecord;
import com.stage.competietabel.service.mapper.TeamMapper;
import org.springframework.stereotype.Service;

import static javax.swing.JOptionPane.showMessageDialog;

import java.io.IOException;
import java.util.Optional;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final SoccerProviderService soccerProviderService;

    public TeamService(TeamRepository teamRepository, SoccerProviderService soccerProviderService) {
        this.teamRepository = teamRepository;
        this.soccerProviderService = soccerProviderService;
    }

    public TeamsResponse getAllTeams() {
        return TeamMapper.mapTeams(teamRepository.findAll());
    }

    public TeamResponse getTeam(String id) {
        Optional<Team> byId = teamRepository.findById(id);
        return byId.map(TeamMapper::mapTeam).orElse(null);

    }

/*
    public TeamResponse addTeam(NewTeamRequest request){
        Team team = new Team();
        team.setName(request.name());
        team.setFoundedYear(request.foundedYear());
        return TeamMapper.mapTeam(teamRepository.save(team));
    }*/
    public TeamResponse addTeam(NewTeamRequest teamRequest) {
        TeamRecord request = soccerProviderService.getTeam(teamRequest.name());
        if (request != null){
            TeamData apiTeam = request.teamData();
            System.out.println("Apicall is gelukt");
            Team team = new Team();
            team.setName(apiTeam.name());
            team.setFoundedYear(apiTeam.founded());
            team.setCode(apiTeam.code());
            team.setCountry(apiTeam.country());
            team.setLogo(apiTeam.logo());
            team.setNational(apiTeam.national());
            Team savedTeam = teamRepository.save(team);
            System.out.println("Team added successfully: " + savedTeam.getName());
            return TeamMapper.mapTeam(savedTeam);
        } else {
            System.out.println("Team does not exist!" + teamRequest.name());
            return null;
        }

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
