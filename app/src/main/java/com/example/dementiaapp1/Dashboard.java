package com.example.dementiaapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Dashboard extends AppCompatActivity {

    ImageView goto_diary,goto_voice,goto_record,gotoAlarmreminder,gotoBMIcal,gotoDementiaInfo,gotoSettings,
            gotodayshedule,gotoActivity;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //connect to diary
        goto_diary=findViewById(R.id.imageButton_note);
        goto_diary.setOnClickListener((v)->startActivity(new Intent(Dashboard.this,MainActivity.class)));

        //voice assistance
        goto_voice=findViewById(R.id.imageButton_voice);
        goto_voice.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, VoiceAssistant.class)));

        //record voice note
        goto_record=findViewById(R.id.imageButton_record);
        goto_record.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, MainActivityRecord.class)));

        //activity
       gotoActivity=findViewById(R.id.Activitybtn);
        gotoActivity.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, MoCAActivity.class )));

        //reminder
        gotoAlarmreminder=findViewById(R.id.reminder);
        gotoAlarmreminder.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, MainActivityAlarm.class)));

        //your health BMI cal
        gotoBMIcal=findViewById(R.id.BMIcalpg);
        gotoBMIcal.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, BMIcal.class)));

        //dementia info page
        gotoDementiaInfo=findViewById(R.id.DementiaINFObtn);
        gotoDementiaInfo.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, weblink.class)));

        //day schedule
        gotodayshedule=findViewById(R.id.imageButtonSchedule);
        gotodayshedule.setOnClickListener((v)->startActivity(new Intent(Dashboard.this, SchedulerActivity.class)));






    }
}