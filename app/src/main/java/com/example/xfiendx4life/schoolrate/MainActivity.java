package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    //private TextView mTextName;
    String messageFrom;
    ArrayList<School> schools = null;


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
        //
        //mTextName.setText(messageFrom);
    }
    public void fillTable () {
        for(int i = 0; i<schools.size(); i++){
            addRow(i);
        }

    }

    public void addRow(int num) {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, null);
        TextView tv = tr.findViewById(R.id.number);
        tv.setText(Integer.toString(num+1));
        tv =  tr.findViewById(R.id.name);
        tv.setText(schools.get(num).schoolName);
        tv =  tr.findViewById(R.id.rating);
        tv.setText(Integer.toString(schools.get(num).score));
        tv =  tr.findViewById(R.id.price);
        tv.setText(Integer.toString(schools.get(num).lessonPrice));
        tableLayout.addView(tr);
    }

    public void readDataFromFile() throws IOException, FileNotFoundException, ClassNotFoundException {
        String filePath = getFilesDir().getPath().toString() + "/dataFile.dt";
        File file = new File(filePath);

        if (file.exists()) {
            FileInputStream fis = new FileInputStream(filePath);
            ObjectInputStream ois = new ObjectInputStream(fis);
            schools = (ArrayList<School>) ois.readObject();
        }
    }
}
