package com.stage.competietabel.controller.dto.res;

import org.apache.tomcat.util.http.Parameters;

import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.Objects;

public record ApiTeamRes(String get, Parameters ParametersObject, ArrayList<Object> errors, float results, Objects PagingObject, ArrayList<TeamData> response) {

    }
