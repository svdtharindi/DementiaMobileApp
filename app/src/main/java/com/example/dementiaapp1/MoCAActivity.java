package com.example.dementiaapp1;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MoCAActivity extends AppCompatActivity {

    private Button submitButton;
    private EditText[] taskInputs;
    private TextView resultText;

    private int[] taskScores = new int[6];

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mo_caactivity);

        submitButton = findViewById(R.id.submitButton);
        taskInputs = new EditText[] {
                findViewById(R.id.task1Input),
                findViewById(R.id.task2Input),
                findViewById(R.id.task3Input),
                findViewById(R.id.task4Input),
                findViewById(R.id.task5Input),
                findViewById(R.id.task6Input)
        };
        resultText = findViewById(R.id.resultText);

        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAndDisplayResult();
            }
        });
    }

    private void calculateAndDisplayResult() {
        for (int i = 0; i < taskInputs.length; i++) {
            String taskScoreStr = taskInputs[i].getText().toString().trim();
            if (!taskScoreStr.isEmpty()) {
                taskScores[i] = Integer.parseInt(taskScoreStr);
            }
        }

        int totalScore = calculateTotalScore(taskScores);
        int cognitiveLevel = predictCognitiveLevel(totalScore);

        String assessmentResult = getAssessmentResult(cognitiveLevel);
        resultText.setText(assessmentResult);
    }

    private int calculateTotalScore(int[] scores) {
        int total = 0;
        for (int score : scores) {
            total += score;
        }
        return total;
    }

    private int predictCognitiveLevel(int totalScore) {
        if (totalScore >= 26 && totalScore <= 30) {
            return 1; // Normal Cognitive Function
        } else if (totalScore >= 19 && totalScore <= 25) {
            return 2; // Mild Cognitive Impairment (MCI)
        } else if (totalScore < 19 && totalScore >= 0){
            return 3; // Possible Cognitive Impairment
        } else return 4;
    }

    private String getAssessmentResult(int level) {
        switch (level) {
            case 1:
                return "Patient's cognitive function is within the normal range.";
            case 2:
                return "Patient may be experiencing mild cognitive impairment (MCI). Further assessment is recommended.";
            case 3:
                return "Patient's score suggests possible cognitive impairment. Further assessment is strongly recommended.";
            case 4:
                return "Please give a valid score.";
            default:
                return "Invalid assessment level.";
        }
    }
}
