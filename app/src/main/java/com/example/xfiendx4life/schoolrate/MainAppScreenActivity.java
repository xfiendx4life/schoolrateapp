package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

public class MainAppScreenActivity extends AppCompatActivity {

    private TextView mTextName;
    String messageFrom;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextName.setText(messageFrom);
                    return true;
                case R.id.navigation_dashboard:
                    mTextName.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextName.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_screen);
        Intent intent = getIntent();
        messageFrom = intent.getStringExtra(StartingScreen.EXTRA_NAME);
        mTextName = (TextView) findViewById(R.id.name);
        mTextName.setText(messageFrom);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}