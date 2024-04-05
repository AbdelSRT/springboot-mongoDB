package com.stage.competietabel.repository.model;

import com.stage.competietabel.service.dto.Birth;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "player-details")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Player {
    private String id;
    private int api_id;
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
}
