package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartingScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
    }
    public void changeScreen (View view) {
        Intent intent = new Intent(this, MainAppScreenActivity.class);
        startActivity(intent);
    }
}
