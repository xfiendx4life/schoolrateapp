package com.example.xfiendx4life.schoolrate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.xfiendx4life.schoolrate.htmlParser.FullSchoolList;

public class Alphabet extends AppCompatActivity {

    ArrayList<School> schools;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alphabet);
        new GetData().execute();
    }
    private  class GetData extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                schools = FullSchoolList("http://www.schoolrate.ru/school_list.html");
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        return null;
        }
        @Override
        protected void onPostExecute (Void result) {
            TextView test = (TextView) findViewById(R.id.test);
            test.setText(schools.get(5).schoolName);
        }
    }
    public  void fillAlphabet() {

    }
}
