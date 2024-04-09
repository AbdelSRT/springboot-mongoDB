package com.stage.competietabel.service;

import com.stage.competietabel.controller.dto.req.NewTeamRequest;
import com.stage.competietabel.controller.dto.req.UpdateTeamRequest;
import com.stage.competietabel.controller.dto.res.PlayerResponse;
import com.stage.competietabel.controller.dto.res.PlayersResponse;
import com.stage.competietabel.controller.dto.res.TeamResponse;
import com.stage.competietabel.controller.dto.res.TeamsResponse;
import com.stage.competietabel.repository.PlayerRepository;
import com.stage.competietabel.repository.TeamRepository;
import com.stage.competietabel.repository.VenueRepository;
import com.stage.competietabel.repository.model.Player;
import com.stage.competietabel.repository.model.Team;
import com.stage.competietabel.repository.model.Venue;
import com.stage.competietabel.service.dto.PlayersTeam;
import com.stage.competietabel.service.dto.TeamData;
import com.stage.competietabel.service.dto.TeamRecord;
import com.stage.competietabel.service.dto.VenueData;
import com.stage.competietabel.service.mapper.PlayerMapper;
import com.stage.competietabel.service.mapper.TeamMapper;
import org.springframework.stereotype.Service;

import static javax.swing.JOptionPane.showMessageDialog;

import java.util.*;

@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final VenueRepository venueRepository;
    private final SoccerProviderService soccerProviderService;
    private final PlayerRepository playerRepository;

    public TeamService(TeamRepository teamRepository, VenueRepository venueRepository, SoccerProviderService soccerProviderService, PlayerRepository playerRepository) {
        this.teamRepository = teamRepository;
        this.venueRepository = venueRepository;
        this.soccerProviderService = soccerProviderService;
        this.playerRepository = playerRepository;
    }

    public TeamsResponse getAllTeams() {
        return TeamMapper.mapTeams(teamRepository.findAll());
    }

    public TeamResponse getTeam(String id) {
        Optional<Team> byId = teamRepository.findById(id);
        return byId.map(TeamMapper::mapTeam).orElse(null);
    }


    public TeamResponse addApiTeam(NewTeamRequest teamRequest) {
        TeamRecord request = soccerProviderService.getTeam(teamRequest.name());
        if (request != null) {
            TeamData apiTeam = request.teamData();
            System.out.println("Apicall is gelukt");
            Team team = new Team();
            team.setApiId(apiTeam.id());
            team.setName(apiTeam.name());
            team.setFoundedYear(apiTeam.founded());
            team.setCode(apiTeam.code());
            team.setCountry(apiTeam.country());
            team.setLogo(apiTeam.logo());
            team.setNational(apiTeam.national());
            team.setVenue(addVenue(request));
            List<Player> players = addPlayersByTeam(apiTeam.id());
            team.setPlayers(players);
            Team savedTeam = teamRepository.save(team);
            System.out.println("Team added successfully: " + savedTeam.getName());
            return TeamMapper.mapTeam(savedTeam);
        } else {
            System.out.println("Team does not exist!" + teamRequest.name());
            return null;
        }

    }

    public Venue addVenue(TeamRecord request) {
        VenueData apiVenue = request.venue();
        Venue venue = new Venue();
        venue.setName(apiVenue.name());
        venue.setAddress(apiVenue.address());
        venue.setCity(apiVenue.city());
        venue.setCapacity(apiVenue.capacity());
        venue.setSurface(apiVenue.surface());
        venue.setImage(apiVenue.image());
        return venueRepository.save(venue);
    }

    public ArrayList<Player> addPlayersByTeam(int teamId) {
        ArrayList<PlayersTeam> playersTeamList = soccerProviderService.getPlayers(teamId);
        Team team = teamRepository.findByApiId(teamId);
        ArrayList<Player> players = new ArrayList<>();
        System.out.println("Test" + team);
        for (PlayersTeam playersTeam : playersTeamList) {
            Player player = new Player();
            player.setName(playersTeam.player().name());
            player.setAge(playersTeam.player().age());
            player.setFirstname(playersTeam.player().firstname());
            player.setLastname(playersTeam.player().lastname());
            player.setBirth(playersTeam.player().birth());
            player.setApiId(playersTeam.player().id());
            player.setNationality(playersTeam.player().nationality());
            player.setBirth(playersTeam.player().birth());
            player.setHeight(playersTeam.player().height());
            player.setWeight(playersTeam.player().weight());
            player.setPhoto(playersTeam.player().photo());
            player.setInjured(playersTeam.player().injured());
            player.setTeam(team);
            playerRepository.save(player);
            players.add(player);
        }
        return players;
    }


    public void delTeam(String id) {
        if (id != null) {
            Optional<Team> teamOptional = teamRepository.findById(id);
            if (teamOptional.isPresent()) {
                Team team = teamOptional.get();
                teamRepository.delete(team);
                venueRepository.deleteById(team.getVenue().getId());
                playerRepository.deleteAll(team.getPlayers());
            }

        }
    }


    public void updateTeamRecord(String id, UpdateTeamRequest request) {
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

    public PlayersResponse getPlayersByTeam(String id) {
        Optional<Team> myTeam = teamRepository.findById(id);
        if (myTeam.isPresent()) {
            List<Player> players = myTeam.get().getPlayers();
            return PlayerMapper.mapPlayers(players);
        }
        return new PlayersResponse(Collections.emptyList());
    }
}
