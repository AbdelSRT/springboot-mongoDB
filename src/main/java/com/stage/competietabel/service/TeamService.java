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
import com.stage.competietabel.service.dto.*;
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

    public TeamResponse getTeam(String id, boolean compact) {
        Optional<Team> byId = teamRepository.findById(id);
        if (byId.isPresent()) {
            return TeamMapper.mapTeam(byId.get(), compact);
        }
        return null;
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
            Team savedTeam = teamRepository.save(team);
            List<Player> players = addPlayersByTeam(savedTeam);
            savedTeam.setPlayers(players);
            savedTeam = teamRepository.save(savedTeam);
            System.out.println("Team added successfully: " + savedTeam.getName());
            return TeamMapper.mapTeam(savedTeam, false);
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

    /*
    public ArrayList<Player> addPlayersByTeam(int teamId) {
        ArrayList<PlayersTeam> playersStatsTeamList = soccerProviderService.getPlayersStats(teamId);
        ArrayList<SecondApiPlayer> secondPlayersList = soccerProviderService.getPlayers(teamId).players();
        Team team = teamRepository.findByApiId(teamId);
        ArrayList<Player> players = new ArrayList<>();
        for (PlayersTeam playersTeam : playersStatsTeamList) {
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
            Optional<SecondApiPlayer> secondPlayer = secondPlayersList.stream().filter(secondApiResponse -> secondApiResponse.id() == playersTeam.player().id()).findFirst();
            if (secondPlayer.isPresent()) {
                player.setNumber(secondPlayer.get().number());
                player.setPosition(secondPlayer.get().position());
            }
            player.setTeam(team);
            playerRepository.save(player);
            players.add(player);
        }
        return players;
    }
    */
    public ArrayList<Player> addPlayersByTeam(Team team) {
        ArrayList<PlayersTeam> playersStatsTeamList = soccerProviderService.getPlayersStats(team.getApiId());
        ArrayList<SecondApiPlayer> secondPlayersList = soccerProviderService.getPlayers(team.getApiId()).players();
        ArrayList<Player> players = new ArrayList<>();
        for (SecondApiPlayer secondPlayer : secondPlayersList) {
            Player player = new Player();
            player.setApiId(secondPlayer.id());
            player.setName(secondPlayer.name());
            player.setAge(secondPlayer.age());
            player.setPosition(secondPlayer.position());
            player.setPhoto(secondPlayer.photo());
            player.setNumber(secondPlayer.number());
            Optional<PlayersTeam> matchingPlayerStats = playersStatsTeamList.stream()
                .filter(stats -> stats.player().id() == secondPlayer.id())
                .findFirst();
            if (matchingPlayerStats.isPresent()) {
                PlayersTeam playerStats = matchingPlayerStats.get();
                player.setFirstname(playerStats.player().firstname());
                player.setLastname(playerStats.player().lastname());
                player.setBirth(playerStats.player().birth());
                player.setHeight(playerStats.player().height());
                player.setWeight(playerStats.player().weight());
                player.setInjured(playerStats.player().injured());
            }
            player.setTeamId(team.getId());
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
