package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import static com.example.xfiendx4life.schoolrate.htmlParser.Rate;

public class StartingScreen extends AppCompatActivity {
    public static final String EXTRA_NAME = "com.example.xfiendx4life.schoolrate";
    String checkString;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_screen);
        new GetData().execute();
    }
    public void startNewScreen(){
        Intent intent = new Intent(this, MainAppScreenActivity.class);
        intent.putExtra(EXTRA_NAME,checkString);
        startActivity(intent);
    }

    public void writeToFile (ArrayList<School> schools) throws IOException {
        String filename = "dataFile.dt";
        String filePath = getFilesDir().getPath().toString() + "/"+filename;
        File file = new File(filePath);
        FileOutputStream outputStream = new FileOutputStream(filePath);

        ObjectOutputStream oos = new ObjectOutputStream(outputStream);
        oos.writeObject(schools);
        oos.close();
    }
    private  class GetData extends AsyncTask <Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            String filename = "data";
            FileOutputStream outputStream;

            try {

                ArrayList<School> schools = Rate();
                checkString = schools.get(4).schoolName;//пусть пока будет
                writeToFile(schools);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute (Void result){
            startNewScreen();
        }
    }





    }
