package com.stage.competietabel.service;

import com.stage.competietabel.controller.dto.req.NewTeamRequest;
import com.stage.competietabel.controller.dto.req.UpdateTeamRequest;
import com.stage.competietabel.controller.dto.res.PlayerResponse;
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
import com.stage.competietabel.service.mapper.TeamMapper;
import org.springframework.stereotype.Service;

import static javax.swing.JOptionPane.showMessageDialog;

import java.util.ArrayList;
import java.util.Optional;

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


    public TeamResponse addTeam(NewTeamRequest request) {
        Team team = new Team();
        team.setName(request.name());
        /* team.setFoundedYear(request.foundedYear()); */
        return TeamMapper.mapTeam(teamRepository.save(team));
    }
    public TeamResponse addApiTeam(NewTeamRequest teamRequest) {
        TeamRecord request = soccerProviderService.getTeam(teamRequest.name());
        if (request != null){
            TeamData apiTeam = request.teamData();
            VenueData apiVenue = request.venue();
            System.out.println("Apicall is gelukt");
            Team team = new Team();
            team.setApi_id(apiTeam.id());
            team.setName(apiTeam.name());
            team.setFoundedYear(apiTeam.founded());
            team.setCode(apiTeam.code());
            team.setCountry(apiTeam.country());
            team.setLogo(apiTeam.logo());
            team.setNational(apiTeam.national());
            Venue venue = new Venue();
            venue.setName(apiVenue.name());
            venue.setAddress(apiVenue.address());
            venue.setCity(apiVenue.city());
            venue.setCapacity(apiVenue.capacity());
            venue.setSurface(apiVenue.surface());
            venue.setImage(apiVenue.image());
            Venue savedVenue = venueRepository.save(venue);
            team.setVenue(savedVenue);
            Team savedTeam = teamRepository.save(team);
            System.out.println("Team added successfully: " + savedTeam.getName());
            return TeamMapper.mapTeam(savedTeam);
        } else {
            System.out.println("Team does not exist!" + teamRequest.name());
            return null;
        }

    }

    public ArrayList<Player> getPlayers(int ApiId){
        ArrayList<PlayersTeam> playersTeamList = soccerProviderService.getPlayers(ApiId);
        ArrayList<Player> players = new ArrayList<>();
        for (PlayersTeam playersTeam : playersTeamList) {
            Player player = new Player();
            player.setName(playersTeam.player().getName());
            player.setAge(playersTeam.player().getAge());
            player.setFirstname(playersTeam.player().getFirstname());
            player.setLastname(playersTeam.player().getLastname());
            player.setBirth(playersTeam.player().getBirth());
            player.setApi_id(playersTeam.player().getApi_id());
            player.setNationality(playersTeam.player().getNationality());
            player.setBirth(playersTeam.player().getBirth());
            player.setHeight(playersTeam.player().getHeight());
            player.setWeight(playersTeam.player().getWeight());
            player.setPhoto(playersTeam.player().getPhoto());
            player.setInjured(playersTeam.player().isInjured());
            playerRepository.save(player);
        }
        return players;
    }

    public void delTeam(String id){
        if (id != null) {
            Team team = teamRepository.findById(id).get();
            teamRepository.delete(team);
            venueRepository.deleteById(team.getVenue().getId());
        }
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
