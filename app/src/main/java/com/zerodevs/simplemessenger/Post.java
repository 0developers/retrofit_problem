package com.zerodevs.simplemessenger;


public class Post {
    String username;
    String email;
    String pass;
    String response;

    public Post (String username , String email,String pass) {
        this.username = username;
        this.email = email;
        this.pass = pass;
    }


    public String getResponse() {
        return response;
    }
}
