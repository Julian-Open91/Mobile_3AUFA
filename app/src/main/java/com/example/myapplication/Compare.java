package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class Compare extends AppCompatActivity {

    TextView name1, name2, result;
    String data_status1, data_status2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);

        name1 = findViewById(R.id.name1);
        name2 = findViewById(R.id.name2);
        result = findViewById(R.id.result);

        Intent intent = getIntent();
        Globals g = (Globals)getApplication();
        data_status1 = intent.getStringExtra("status");
        data_status2= intent.getStringExtra("status");

        g.setStatus1(data_status1);
        g.setStatus2(data_status2);

        name1.setText(data_status1);
        name2.setText(data_status2);


    }

}
