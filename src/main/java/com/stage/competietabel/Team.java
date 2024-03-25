package com.stage.competietabel;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Team {
    @Id
    private Integer id;
    @Indexed(unique = true)
    private String name;
    private Integer founded_year;
    private Integer wins;
    private Integer loss;
    private Integer draw;
    private Integer played_games;

    public Team(String name, Integer founded_year, Integer wins,
                Integer loss, Integer draw, Integer played_games) {
        this.name = name;
        this.founded_year = founded_year;
        this.wins = wins;
        this.loss = loss;
        this.draw = draw;
        this.played_games = played_games;
    }

    public Team() {

    }
}
