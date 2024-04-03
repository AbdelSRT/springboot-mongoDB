package com.stage.competietabel.service;
import com.stage.competietabel.service.dto.ApiTeamRes;
import com.stage.competietabel.service.dto.TeamRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class SoccerProviderService {



    private static final Logger logger = LoggerFactory.getLogger(SoccerProviderService.class);


  WebClient.Builder builder = WebClient.builder();

    public TeamRecord getTeam(String name) {
        try {
            ApiTeamRes apiResponse = builder.build()
                    .get()
                    .uri("https://api-football-v1.p.rapidapi.com/v3/teams?name={name}", name)
                    .header("X-RapidAPI-Key", "7054b9d493mshccf6ff5e235d46fp1f9337jsn1334618229c3")
                    .header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response -> Mono.error(new RuntimeException("Failed to retrieve team info")))
                    .bodyToMono(ApiTeamRes.class)
                    .block();
            if(apiResponse != null && apiResponse.results() > 0){
                return apiResponse.response().get(0);
            }
        } catch (Exception e) {
            logger.error("Error retrieving team info: {}", e.getMessage());
            return null;
        } return null;
    }
}
