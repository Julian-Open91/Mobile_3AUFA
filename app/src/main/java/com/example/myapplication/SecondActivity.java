package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    TextView name, status, species;
    public String data_name, data_status, data_species;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();
        name = findViewById(R.id.name);
        status = findViewById(R.id.status);
        species = findViewById(R.id.species);

        data_species = i.getStringExtra("species");
        data_name = i.getStringExtra("name");
        data_status = i.getStringExtra("status");

        name.setText(data_name);
        species.setText(data_species);
        status.setText(data_status);
    }
}
