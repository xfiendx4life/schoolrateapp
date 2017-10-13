package com.example.xfiendx4life.schoolrate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class application extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application);
        Button sendButton = (Button) findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //readDataFromEdits();
                sendEmail();
            }
        });
    }

    protected void readDataFromEdits() {
        //читаем данные из editов
    }

    protected void sendEmail () {
        EditText name = (EditText) findViewById(R.id.user_name_edit);
        EditText email = (EditText) findViewById(R.id.email_edit);
        EditText phone = (EditText) findViewById(R.id.phone_edit);
        EditText extra = (EditText) findViewById(R.id.extra);
        Log.i("Send email","");
        String[] TO = {"xfiendx4life@schoolrate.ru"};
        String[] CC = {""};

        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
        emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Подбор школы");
        String emailText = new String();
        emailText = String.format("Имя: %s \n Телефон: %s \n Электронный адрес: %s \n Пожелания по обучению: \n %s ",
                name.getText(),email.getText(),phone.getText(),extra.getText());
        emailIntent.putExtra(Intent.EXTRA_TEXT, emailText);
        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
            finish();
            Log.i("Finished sending email", "");
        }
        catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(application.this, "There is no email client installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
