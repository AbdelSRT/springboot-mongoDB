package com.stage.competietabel.service;
import com.google.gson.Gson;
import com.stage.competietabel.controller.dto.res.ApiTeamRes;
import com.stage.competietabel.controller.dto.res.TeamResponse;
import com.stage.competietabel.repository.model.Team;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.HttpStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import okhttp3.*;

import com.stage.competietabel.repository.model.TeamApi;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class SoccerProviderService {


/*
  WebClient.Builder builder = WebClient.builder();

    public ApiTeamRes apiReq(String name) {
        try {
            return builder.build()
                    .get()
                    .uri("https://api-football-v1.p.rapidapi.com/v3/teams?name={name}", name)
                    .header("X-RapidAPI-Key", "7054b9d493mshccf6ff5e235d46fp1f9337jsn1334618229c3")
                    .header("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
                    .retrieve()
                    .onStatus(HttpStatusCode::isError, response -> Mono.error(new RuntimeException("Failed to retrieve team info")))
                    .bodyToMono(ApiTeamRes.class)
                    .block();
        } catch (Exception e) {
            logger.error("Error retrieving team info: {}", e.getMessage());
            return null;
        }
    }



    public TeamResponse getTeam(String name){
        ApiTeamRes request = apiReq(name);
        return (TeamResponse) request.response().get(0);
    }

*/
    OkHttpClient client = new OkHttpClient();


    public Team apiReq(String teamName) throws IOException {
        Request request = new Request.Builder()
                .url("https://api-football-v1.p.rapidapi.com/v3/teams?name=" + teamName)
                .get()
                .addHeader("X-RapidAPI-Key", "7054b9d493mshccf6ff5e235d46fp1f9337jsn1334618229c3")
                .addHeader("X-RapidAPI-Host", "api-football-v1.p.rapidapi.com")
                .build();
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) throw new IOException("Unexpected code " + response);

            String responseBody = response.body().string();
            ApiTeamRes teamResponse = new Gson().fromJson(responseBody, ApiTeamRes.class);

            if (teamResponse.results() > 0) {
                return teamResponse.response().get(0).team();
            } else {
                return null;
            }
        }
    }
}
