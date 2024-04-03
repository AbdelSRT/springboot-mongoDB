package com.stage.competietabel.repository.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Team {
    @Id
    private String id;
    @Indexed(unique = true)
    private String name;
    private int foundedYear;
    private String code;
    private String country;
    private String logo;
    private boolean national;
    private int wins = 0;
    private int loss = 0;
    private int draw = 0;
    private int playedGames = 0;



    public Team(String name, boolean national, int foundedYear, String code, String country, String logo, String url, int wins, Integer loss, int draw, int playedGames) {
        this.name = name;
        this.foundedYear = foundedYear;
        this.code = code;
        this.country = country;
        this.logo = logo;
        this.national = national;
        this.wins = wins;
        this.loss = loss;
        this.draw = draw;
        this.playedGames = playedGames;
    }

    public Team() {

    }
}
