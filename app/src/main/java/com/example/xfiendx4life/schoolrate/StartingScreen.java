package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;
import java.util.ArrayList;

import static com.example.xfiendx4life.schoolrate.htmlParser.Rate;

public class StartingScreen extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.xfiendx4life.schoolrate";
    String checkString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        new GetData().execute();
    }
    public void startNewScreen(){
        Intent intent = new Intent(this, MainAppScreenActivity.class);
        intent.putExtra(EXTRA_MESSAGE,checkString);
        startActivity(intent);
    }

    private  class GetData extends AsyncTask <Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                ArrayList<School> schools = Rate();
                checkString = schools.get(1).schoolName;
            }
            catch (IOException e) {
                System.out.println(e);
            }
            return null;
        }

        @Override
        protected void onPostExecute (Void result){
            startNewScreen();
        }
    }





    }
