package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class SchoolCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_card);
        TextView nameTextView = (TextView) findViewById(R.id.school_name_text);
        Intent intent = getIntent();
        String link = intent.getStringExtra(MainActivity.SCHOOL_NAME); //link to card actually
        nameTextView.setText(link);
    }
}
