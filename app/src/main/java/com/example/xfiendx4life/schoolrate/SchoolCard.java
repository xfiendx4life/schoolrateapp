package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import static com.example.xfiendx4life.schoolrate.htmlParser.cardDataGetter;

public class SchoolCard extends AppCompatActivity {
    TextView bioTextView;
    String link;
    SchoolCardData schoolData = new SchoolCardData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_card);
        bioTextView = (TextView) findViewById(R.id.school_bio_text);
        Intent intent = getIntent();
        //получим данные в фоне
        link = intent.getStringExtra(MainActivity.SCHOOL_NAME); //link to card actually
        new GetData().execute();
        //nameTextView.setText(link);
    }

    private  class GetData extends AsyncTask<Void, Void, Void> {
        protected void onPreExecute() {
            bioTextView.setText("Wait for data...");
        }
        @Override
        protected Void doInBackground(Void... voids) {

            try {

                schoolData = cardDataGetter("http://www.schoolrate.ru"+link);
            }
            catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            bioTextView.setText(schoolData.bio);
        }
    }
}
