package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.xfiendx4life.schoolrate.htmlParser.FullSchoolList;

public class Alphabet extends AppCompatActivity {

    ArrayList<School> schools;
    public static final String SCHOOL_LINK = "com.example.xfiendx4life.schoolrate";

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
            fillAlphabet();
        }
    }
    public  void fillAlphabet() {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.alph_table);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.alphabet_table, tableLayout,false);
        tr.setId(0);
        TextView tv = tr.findViewById(R.id.alp_school_name);
        tv.setText("Название школы");
        tv = tr.findViewById(R.id.alp_price);
        tv.setText("Цена за 45 мин.");
        tv = tr.findViewById(R.id.alp_year);
        tv.setText("Год основания");
        tableLayout.addView(tr);
        for(int i = 0; i<schools.size(); i++){
            addRow(i, inflater, tableLayout);
        }
    }
    public void addRow(int num, LayoutInflater inflater, TableLayout parent) {

        TableRow tr = (TableRow) inflater.inflate(R.layout.alphabet_table, parent,false);
        tr.setId(num + 1);
        TextView tv = tr.findViewById(R.id.alp_school_name);
        tv.setText(schools.get(num).schoolName);
        tv =  tr.findViewById(R.id.alp_price);
        tv.setText(String.valueOf(schools.get(num).lessonPrice));
        tv =  tr.findViewById(R.id.alp_year);
        tv.setText(String.valueOf(schools.get(num).yearOfBirth));

        final int index = num ;
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Alphabet.this, SchoolCard.class);
                intent.putExtra(SCHOOL_LINK,schools.get(index).schoolCardLink);
                startActivity(intent);
            }
        });
        parent.addView(tr);
    }
}
