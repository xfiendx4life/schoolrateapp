package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
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
    public static final String SCHOOL_LINK = "com.example.xfiendx4life.schoolrate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_main);
        loadRating();
        setNavigationDrawer();

    }
    private void loadRating(){
        try {
            readDataFromFile();
            fillTable();
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    private void setNavigationDrawer() {
        final DrawerLayout dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                int itemId = menuItem.getItemId();
                if(itemId == R.id.rating){
                    loadRating();
                }
                else {
                    if (itemId == R.id.alphabet) {
                        Intent intent = new Intent(MainActivity.this, Alphabet.class);
                        startActivity(intent);
                    }
                }
                dLayout.closeDrawers();
                return true;
            }
        });
        }
    public void fillTable () {
        TableLayout tableLayout = (TableLayout) findViewById(R.id.table);
        LayoutInflater inflater = LayoutInflater.from(this);
        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, tableLayout,false);
        tr.setId(0);
        TextView tv = tr.findViewById(R.id.school_name);
        tv.setText("Название школы");
        tv.setTextSize(15);
        tv.setTypeface(null, Typeface.BOLD);
        tv =  tr.findViewById(R.id.rating);
        tv.setText("Рейтинг");
        tv.setTextSize(15);
        tv.setTypeface(null, Typeface.BOLD);
        tableLayout.addView(tr);
        for(int i = 0; i<schools.size(); i++){
           addRow(i, inflater, tableLayout);
        }

    }



    public void addRow(int num, LayoutInflater inflater, TableLayout parent) {

        TableRow tr = (TableRow) inflater.inflate(R.layout.table_row, parent,false);
        tr.setId(num + 1);
        TextView tv = tr.findViewById(R.id.number);
        tv.setText(String.valueOf(num+1));
        tv =  tr.findViewById(R.id.school_name);
        tv.setText(schools.get(num).schoolName);
        tv =  tr.findViewById(R.id.rating);
        tv.setText(String.valueOf(schools.get(num).score));

        final int index = num ;
        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, SchoolCard.class);
                intent.putExtra(SCHOOL_LINK,schools.get(index).schoolCardLink);
                startActivity(intent);
            }
        });
       parent.addView(tr);
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
