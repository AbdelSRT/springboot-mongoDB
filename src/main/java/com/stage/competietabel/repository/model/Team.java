package com.stage.competietabel.repository.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.List;

@Document(collection = "teams")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Team {
    @Id
    private String id;
    private int apiId;
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
    @OneToOne(cascade = CascadeType.ALL)
    @DocumentReference(collection = "venues")
    private Venue venue;
    @OneToMany(cascade = CascadeType.ALL)
    @DocumentReference(collection = "players")
    private List<Player> players;
}
