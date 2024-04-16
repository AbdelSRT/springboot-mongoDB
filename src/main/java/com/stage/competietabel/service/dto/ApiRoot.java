package com.stage.competietabel.service.dto;

import java.util.ArrayList;

public record ApiRoot(float results, ArrayList<SecondApiResponse> response) {
}
