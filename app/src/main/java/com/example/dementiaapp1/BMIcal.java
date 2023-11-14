package com.example.dementiaapp1;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class BMIcal extends AppCompatActivity {
    EditText heightText;
    EditText weightText;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmical);
        heightText = findViewById(R.id.heightEditText);
        weightText = findViewById(R.id.weightEditText);
    }

    public void calculateButtonClick(View view) {

        double height = 0;
        double weight = 0;

        if (!heightText.getText().toString().equals("") && Double.parseDouble(heightText.getText().toString()) != 0) {
            height = Double.parseDouble(heightText.getText().toString());
        }

        if (!weightText.getText().toString().equals("") && Double.parseDouble(weightText.getText().toString()) != 0) {
            weight = Double.parseDouble(weightText.getText().toString());
        }

        //Health health = new Health();
        BMIhelper bmIhelper=new BMIhelper();
       // double bmiResult = health.calculateBMI(height, weight);
        double bmiResult=bmIhelper.calculateBMI(height,weight);

        String resultText;

        if (bmiResult != -1) {
            String bmiCat = bmIhelper.determineCategory(bmiResult);
            resultText = "Your BMI index is " + String.format("%.2f", bmiResult) + "\nBMI category: " + bmiCat;


            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setTitle("BMI");
            alertDialogBuilder.setMessage(resultText);
            alertDialogBuilder.setPositiveButton("DONE", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    heightText.setText("");
                    weightText.setText("");

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        } else {
            Toast.makeText(this, bmIhelper.getErrorMsg(), Toast.LENGTH_SHORT).show();
        }
    }
}