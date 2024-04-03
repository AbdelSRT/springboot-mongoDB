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
    private Integer foundedYear;
    private String code;
    private String country;
    private String logo;
    private Integer wins = 0;
    private Integer loss = 0;
    private Integer draw = 0;
    private Integer playedGames = 0;



    public Team(String name, Integer foundedYear, String code, String country, String logo, Integer wins, Integer loss, Integer draw, Integer playedGames) {
        this.name = name;
        this.foundedYear = foundedYear;
        this.code = code;
        this.country = country;
        this.logo = logo;
        this.wins = wins;
        this.loss = loss;
        this.draw = draw;
        this.playedGames = playedGames;
    }

    public Team() {

    }
}
