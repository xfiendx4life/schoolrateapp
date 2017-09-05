package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.net.URL;
import java.util.Enumeration;

import static com.example.xfiendx4life.schoolrate.MainActivity.SCHOOL_LINK;
import static com.example.xfiendx4life.schoolrate.htmlParser.cardDataGetter;

public class SchoolCard extends AppCompatActivity {
    TextView bioTextView;
    String link;
    SchoolCardData schoolData = new SchoolCardData();
    Bitmap bmp;
    ImageView schPic;
    String name;
    //final android.app.ActionBar ab = getActionBar();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_card);
        bioTextView = (TextView) findViewById(R.id.school_bio_text);
        Intent intent = getIntent();
        //получим данные в фоне
        link = intent.getStringExtra(SCHOOL_LINK);
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
            TextView pricesHeader = (TextView) findViewById(R.id.prices_header);
            pricesHeader.setText(R.string.prices_for_card_header);
            FillPricesTable();
           /*try {

            }
            catch (Exception e){
                bioTextView.setText("Unexpected Error");
            }
            /*/


        }
    }

    public void FillPricesTable() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.prices_table);
        LayoutInflater inflater = LayoutInflater.from(this);
        Enumeration e = schoolData.prices.keys();
        int id = 0;
        while(e.hasMoreElements()){
            addRow((String) e.nextElement(), id, inflater, tableLayout);
            id++;
        }
    }
    public void addRow(String key, int id, LayoutInflater inflater, TableLayout parent) {

        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, parent,false);
        tr.setId(id);
        TextView tv = tr.findViewById(R.id.school_name);
        tv.setText(key);
        tv =  tr.findViewById(R.id.rating);
        tv.setText(String.format("%.0f",schoolData.prices.get(key)));

        parent.addView(tr);
    }

}
