package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.net.URISyntaxException;

public class SecondActivity extends AppCompatActivity {

    TextView name, status, species;
    ImageView avatar;
    Button button1, button2 ;

    public String data_name, data_status, data_species, data_avatar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent i = getIntent();
        name = findViewById(R.id.name);
        status = findViewById(R.id.status);
        species = findViewById(R.id.species);
        avatar = findViewById(R.id.avatar);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);


        data_species = i.getStringExtra("species");
        data_name = i.getStringExtra("name");
        data_status = i.getStringExtra("status");
        data_avatar = i.getStringExtra("image");

        name.setText(data_name);
        species.setText(data_species);
        status.setText(data_status);
        Glide.with(this).load("http://i.imgur.com/DvpvklR.png").into(avatar);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent1 = new Intent(SecondActivity.this, Compare.class);
                intent1.putExtra("Character1", data_status);
                SecondActivity.this.startActivity(intent1);
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent2 = new Intent(SecondActivity.this, Compare.class);
                intent2.putExtra("Character2", data_status);
                SecondActivity.this.startActivity(intent2);
            }
        });
    }
}
