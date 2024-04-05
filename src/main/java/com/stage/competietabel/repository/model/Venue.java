package com.stage.competietabel.repository.model;


import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Table
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "venue")
public class Venue {
    @Id
    private String id;
    private String name;
    private String address;
    private String city;
    private int capacity;
    private String surface;
    private String image;

}
