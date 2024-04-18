package com.stage.competietabel.authentication;

public record RegisterRequest(String firstname, String lastname, String email, String password) {
}
