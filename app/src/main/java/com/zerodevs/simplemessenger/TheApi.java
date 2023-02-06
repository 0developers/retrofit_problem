package com.zerodevs.simplemessenger;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface TheApi {
        @GET("s/sh0s8iddz56ipcs/link.json?dl=0")
        Call<MyLink> GetLink();
        @POST("oreo_register")
        Call<Post>createPost(@Body Post post);
        @POST("oreo_login")
        Call<Post>createPost(@Body Login login);

    }



