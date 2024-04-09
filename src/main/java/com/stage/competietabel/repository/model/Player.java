package com.stage.competietabel.repository.model;

import com.stage.competietabel.service.dto.Birth;
import com.stage.competietabel.service.dto.TeamData;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

@Document(collection = "players")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    @Id
    private String id;
    private int apiId;
    private String name;
    private String firstname;
    private String lastname;
    private int age;
    private Birth birth;
    private String nationality;
    private String height;
    private String weight;
    private boolean injured;
    private String photo;
    @DocumentReference(collection = "teams")
    private Team team;
}
