package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import static com.example.xfiendx4life.schoolrate.SchoolCard.SCHOOL_BIO;

public class SchoolBio extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_bio);
        Intent intent = getIntent();
        String bioString = intent.getStringExtra(SCHOOL_BIO);
        TextView bio = (TextView) findViewById(R.id.bio);
        bio.setText(bioString);
    }
    public void close (View view) {
        super.finish();
    }
}
