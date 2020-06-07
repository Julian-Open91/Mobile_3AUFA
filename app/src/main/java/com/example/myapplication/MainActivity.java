package com.example.myapplication;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ListAdapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private static final String BASE_URL = "https://raw.githubusercontent.com/Julian-Open91/Mobile_3AUFA/API_for_image/app/src/main/java/com/example/myapplication/";
    private SharedPreferences sharedPreferences ;
    private Gson gson;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences("application_esiea", Context.MODE_PRIVATE);
        gson = new GsonBuilder()
                .setLenient()
                .create();

        List<Character> characterList = getDataFromCache();

        if(characterList != null){
            showList(characterList);
        } else {
            makeApiCall();
        }
    }

    private List<Character> getDataFromCache() {

            String jsonRickandmorty = sharedPreferences.getString(Constants.KEY_RICKANDMORTY_LIST, null);

            if(jsonRickandmorty == null){
                return null;
            } else {
                Type listType = new TypeToken<List<Character>>(){}.getType();
                return gson.fromJson(jsonRickandmorty, listType);

            }

    }

    private void showList(List<Character> characterList) {
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        final SwipeRefreshLayout swiperefresh = findViewById(R.id.swiperefresh);
        swiperefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                swiperefresh.setRefreshing(false);
            }
        });
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        mAdapter = new ListAdapter(characterList, MainActivity.this);
        recyclerView.setAdapter(mAdapter);
    }


    private void makeApiCall () {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        RickApi rickAPI = retrofit.create(RickApi.class);

        Call<RestRickandmortyResponse> call = rickAPI.GetRickandmortyResponse();
        call.enqueue(new Callback<RestRickandmortyResponse>() {
            @Override
            public void onResponse(Call<RestRickandmortyResponse> call, Response<RestRickandmortyResponse> response) {
                if(response.isSuccessful() && response.body() != null){
                    List<Character> characterList = response.body().getResults();
                    Toast.makeText(getApplicationContext(), "API Success", Toast.LENGTH_SHORT).show();
                    saveList(characterList);
                    showList(characterList);
                }
                else {
                    showError();
                }
            }

            @Override
            public void onFailure(Call<RestRickandmortyResponse> call, Throwable t) {
                showError();
            }
        });
    }

    private void saveList(List<Character> characterList) {
        String jsonString = gson.toJson(characterList);
        sharedPreferences
                .edit()
                .putString(Constants.KEY_RICKANDMORTY_LIST, jsonString)
                .apply();
        Toast.makeText(getApplicationContext(), "List saved", Toast.LENGTH_SHORT).show();
    }

    private void showError() {
        Toast.makeText(getApplicationContext(), "API Error", Toast.LENGTH_SHORT).show();
    }
}