package com.example.myapplication;

import retrofit2.Call;
import retrofit2.http.GET;

public interface PokeApi {

    @GET("/api/v2/pokemon")
    Call<RestPokemonResponse> GetPokemonResponse();

    @GET("/api/v2/ability")
    Call<RestPokemonResponse> GetAbility();
}