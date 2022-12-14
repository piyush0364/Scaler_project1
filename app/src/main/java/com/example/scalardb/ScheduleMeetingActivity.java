package com.example.scalardb;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.scalardb.data.MyDbHandler;
import com.example.scalardb.model.Contact;

import java.util.Calendar;

public class ScheduleMeetingActivity extends AppCompatActivity {
    Button SetDate;
    TimePickerDialog picker;
    TextView SelectedDate;
    Button SetTime;
    TextView SelectTime;
    Button Save;
    EditText Title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule_meeting);
        SetDate = findViewById(R.id.SetDate);
        SelectedDate = findViewById(R.id.set_date);
        SelectedDate.setVisibility(View.GONE);
        SetTime = findViewById(R.id.SetTime);
        SelectTime = findViewById(R.id.set_time);
        SelectTime.setVisibility(View.GONE);
        Save = findViewById(R.id.save);
        Title = findViewById(R.id.et_name);

        final Intent intent = getIntent();

        SetDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar d = Calendar.getInstance();
                final int mHour = d.get(Calendar.HOUR_OF_DAY);
                final int mMinute = d.get(Calendar.MINUTE);

                picker = new TimePickerDialog(ScheduleMeetingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                SetDate.setVisibility(View.GONE);
                                SelectedDate.setVisibility(View.VISIBLE);
                                SelectedDate.setText("" + hourOfDay + ":" + minute);
                            }
                        }, mHour, mMinute, true);
                picker.show();
//                final Calendar cldr = Calendar.getInstance();
//                int day = cldr.get(Calendar.DAY_OF_MONTH);
//                int month = cldr.get(Calendar.MONTH);
//                int year = cldr.get(Calendar.YEAR);
//                picker = new DatePickerDialog(ScheduleMeetingActivity.this,
//                        new DatePickerDialog.OnDateSetListener() {
//                            @Override
//                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                SetDate.setVisibility(View.GONE);
//                                SelectedDate.setVisibility(View.VISIBLE);
//                                SelectedDate.setText("DATE: "+ dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
//                            }
//                        }, year, month, day);
//                picker.show();
            }


        });

        SetTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final Calendar c = Calendar.getInstance();
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(ScheduleMeetingActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                SetTime.setVisibility(View.GONE);
                                SelectTime.setText("" + hourOfDay + ":" + minute);
                                SelectTime.setVisibility(View.VISIBLE);
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();
            }
        });

        Save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SelectedDate.getText().equals(null) || SelectedDate.getText().equals("")) {
                    Toast.makeText(ScheduleMeetingActivity.this, "Add Date", Toast.LENGTH_SHORT).show();
                } else if (SelectTime.getText().equals(null) || SelectTime.getText().equals("")) {
                    Toast.makeText(ScheduleMeetingActivity.this, "Add Time", Toast.LENGTH_SHORT).show();
                } else if (Title.getText().toString().equals(null) || Title.getText().toString().equals("")) {
                    Title.setError("Add Title");
                } else {
                    MyDbHandler db = new MyDbHandler(ScheduleMeetingActivity.this);
                    Contact aqsa = new Contact();
                    aqsa.setName(Title.getText().toString());
                    aqsa.setStart_time(SelectedDate.getText().toString());
                    aqsa.setEnd_time(SelectTime.getText().toString());

                    db.addContact(aqsa);
                    Intent intent = new Intent(ScheduleMeetingActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}