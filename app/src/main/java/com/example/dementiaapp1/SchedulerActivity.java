package com.example.dementiaapp1;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TableLayout;
import android.widget.TableRow;
import androidx.appcompat.app.AppCompatActivity;

public class SchedulerActivity extends AppCompatActivity {

    private TableLayout tableLayout;
    private EditText etNewSchedule;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scheduler);

        tableLayout = findViewById(R.id.tableLayout);
        etNewSchedule = findViewById(R.id.etNewSchedule);
        sharedPreferences = getSharedPreferences("MySchedulePrefs", MODE_PRIVATE);
        loadSchedules();
    }

    public void addSchedule(View view) {
        String newSchedule = etNewSchedule.getText().toString();

        if (!newSchedule.isEmpty()) {
            TableRow row = new TableRow(this);
            TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                    TableRow.LayoutParams.MATCH_PARENT,
                    TableRow.LayoutParams.WRAP_CONTENT
            );
            row.setLayoutParams(layoutParams);

            EditText editText = new EditText(this);
            editText.setText(newSchedule);
            editText.setEnabled(false);
            row.addView(editText);

            Button deleteButton = new Button(this);
            deleteButton.setText("Delete");
            deleteButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    tableLayout.removeView(row); // Remove row when delete button is clicked
                    deleteSchedule(editText.getText().toString());
                }
            });
            row.addView(deleteButton);

            tableLayout.addView(row);
            saveSchedule(newSchedule);
            etNewSchedule.setText("");
        }
    }

    public void deleteAllSchedules(View view) {
        tableLayout.removeAllViews(); // Remove all rows from the table
        deleteAllSchedulesFromSharedPreferences();
    }

    private void saveSchedule(String schedule) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int count = sharedPreferences.getInt("scheduleCount", 0);
        editor.putString("schedule_" + count, schedule);
        editor.putInt("scheduleCount", count + 1);
        editor.apply();
    }

    private void loadSchedules() {
        int count = sharedPreferences.getInt("scheduleCount", 0);
        for (int i = 0; i < count; i++) {
            String schedule = sharedPreferences.getString("schedule_" + i, "");
            if (!schedule.isEmpty()) {
                addScheduleRow(schedule);
            }
        }
    }

    private void addScheduleRow(String schedule) {
        TableRow row = new TableRow(this);
        TableRow.LayoutParams layoutParams = new TableRow.LayoutParams(
                TableRow.LayoutParams.MATCH_PARENT,
                TableRow.LayoutParams.WRAP_CONTENT
        );
        row.setLayoutParams(layoutParams);

        EditText editText = new EditText(this);
        editText.setText(schedule);
        row.addView(editText);

        Button deleteButton = new Button(this);
        deleteButton.setText("Delete");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tableLayout.removeView(row); // Remove row when delete button is clicked
                deleteSchedule(schedule);
            }
        });
        row.addView(deleteButton);

        tableLayout.addView(row);
    }

    private void deleteSchedule(String schedule) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int count = sharedPreferences.getInt("scheduleCount", 0);
        for (int i = 0; i < count; i++) {
            String savedSchedule = sharedPreferences.getString("schedule_" + i, "");
            if (savedSchedule.equals(schedule)) {
                editor.remove("schedule_" + i);
                editor.apply();
                break;
            }
        }
    }

    private void deleteAllSchedulesFromSharedPreferences() {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        int count = sharedPreferences.getInt("scheduleCount", 0);
        for (int i = 0; i < count; i++) {
            editor.remove("schedule_" + i);
        }
        editor.putInt("scheduleCount", 0);
        editor.apply();
    }
}
