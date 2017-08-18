package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private TextView mTextName;
    String messageFrom;
    ArrayList<School> schools = null;
    public static final String SCHOOL_NAME = "com.example.xfiendx4life.schoolrate";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_main);
        //mTextName = (TextView) findViewById(R.id.name);
        try {
            readDataFromFile();
            messageFrom = schools.get(4).schoolName;
            fillTable();
        }
        catch (Exception e){
            e.printStackTrace();
            //messageFrom = intent.getStringExtra(StartingScreen.EXTRA_NAME);
        }

    }
    public void fillTable () {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);
        tr.setId(0);
        TextView tv = tr.findViewById(R.id.number);
        tv.setText("");
        tv =  tr.findViewById(R.id.school_name);
        tv.setText("Название школы");
        tv =  tr.findViewById(R.id.rating);
        tv.setText("Рейтинг");
        tv =  tr.findViewById(R.id.price);
        tv.setText("Цена");
        tableLayout.addView(tr);
        for(int i = 0; i<schools.size(); i++){
            addRow(i);
        }

    }

    public void addRow(int num) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);
        tr.setId(num + 1);
        TextView tv = tr.findViewById(R.id.number);
        tv.setText(String.valueOf(num+1));
        tv =  tr.findViewById(R.id.school_name);
        tv.setText(schools.get(num).schoolName);
        tv =  tr.findViewById(R.id.rating);
        tv.setText(String.valueOf(schools.get(num).score));
        tv =  tr.findViewById(R.id.price);
        tv.setText(String.valueOf(schools.get(num).lessonPrice));
        final int index = num ;
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SchoolCard.class);
                intent.putExtra(SCHOOL_NAME,schools.get(index).schoolCardLink);
                startActivity(intent);
            }
        });
        tableLayout.addView(tr);
    }


    public void readDataFromFile() throws IOException, ClassNotFoundException {
        String filePath = getFilesDir().getPath().toString() + "/dataFile.dt";
        File file = new File(filePath);

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            schools = (ArrayList<School>) ois.readObject();
        }
    }
}
