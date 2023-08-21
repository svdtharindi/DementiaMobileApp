package com.example.dementiaapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

public class Dashboard extends AppCompatActivity {

    ImageButton goto_note,goto_voice,goto_record,goto_Addremindrs_alarm,gotoAlarmreminder,gotoBMIcal,gotoDementiaInfo;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        goto_note=findViewById(R.id.imageButton_note);
        goto_note.setOnClickListener((v)->startActivity(new Intent(Dashboard.this,MainActivity.class)));

        goto_voice=findViewById(R.id.imageButton_voice);
        goto_voice.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, VoiceAssistant.class)));

        goto_record=findViewById(R.id.imageButton_record);
        goto_record.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, Rec_and_play.class)));

        gotoAlarmreminder=findViewById(R.id.reminder);
        gotoAlarmreminder.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, MainActivityAlarm.class)));

        gotoBMIcal=findViewById(R.id.BMIcalpg);
        gotoBMIcal.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, BMIcal.class)));

        gotoDementiaInfo=findViewById(R.id.DementiaINFObtn);
        gotoDementiaInfo.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, weblink.class)));


        //goto_Addremindrs_alarm=findViewById(R.id.imageButton_reminder);
      //goto_Addremindrs_alarm.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, MainActivityAlarm.class)));
    }
}