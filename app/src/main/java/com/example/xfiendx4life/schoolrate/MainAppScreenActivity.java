package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

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

    public ArrayList<School> readDataFromFile() throws IOException, FileNotFoundException, ClassNotFoundException {
        String filePath = getFilesDir().getPath().toString() + "/dataFile.dt";
        File file = new File(filePath);
        ArrayList<School> schools = null;
        if (file.exists()) {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            schools = (ArrayList<School>) ois.readObject();
        }
        return schools;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_app_screen);
        Intent intent = getIntent();
        try {
            messageFrom = readDataFromFile().get(4).schoolName;
        }
        catch (Exception e){
            e.printStackTrace();
            messageFrom = intent.getStringExtra(StartingScreen.EXTRA_NAME);
        }
        mTextName = (TextView) findViewById(R.id.name);
        mTextName.setText(messageFrom);
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


}