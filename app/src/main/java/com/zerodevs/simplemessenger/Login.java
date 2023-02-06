package com.zerodevs.simplemessenger;

public class Login {
    String email;
    String pass;
    String response;

    public Login(String email, String pass) {
        this.email = email;
        this.pass = pass;
    }

    public String getResponse() {
        return response;
    }
}
