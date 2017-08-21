package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import java.net.URL;

import static com.example.xfiendx4life.schoolrate.htmlParser.cardDataGetter;

public class SchoolCard extends AppCompatActivity {
    TextView bioTextView;
    String link;
    SchoolCardData schoolData = new SchoolCardData();
    Bitmap bmp;
    ImageView schPic;
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
        Exception e = null;
        protected void onPreExecute() {
            bioTextView.setText("Wait for data...");
        }
        @Override
        protected Void doInBackground(Void... voids) {

            try {

                schoolData = cardDataGetter("http://www.schoolrate.ru"+link);
                URL url = new URL("http://www.schoolrate.ru" + schoolData.picLink);
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            }
            catch (Exception ee) {
                ee.printStackTrace();
                //e = ee;
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            bioTextView.setText(schoolData.bio);
            schPic = (ImageView) findViewById(R.id.school_pic);
            schPic.setImageBitmap(bmp);
           /* try {
                getActionBar().setTitle(schoolData.name);
            }
            catch (Exception e){
                getActionBar().setTitle(R.string.school_page);
            }
            //bioTextView.setText("Unexpected Error");
            */

        }
    }
}
