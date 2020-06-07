package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RickApi {

    @GET("RickandMorty.json")
    Call<RestRickandmortyResponse> GetRickandmortyResponse();
}
