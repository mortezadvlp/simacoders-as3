package com.simacoders.morteza.persiancalendar1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mohamadamin.persianmaterialdatetimepicker.date.DatePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.time.RadialPickerLayout;
import com.mohamadamin.persianmaterialdatetimepicker.time.TimePickerDialog;
import com.mohamadamin.persianmaterialdatetimepicker.utils.PersianCalendar;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonDate = findViewById(R.id.buttonDate);
        Button buttonTime = findViewById(R.id.buttonTime);
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.editTextDate);
                String str = editText.getText().toString();
                if(str.equals("")){
                    showCalendar();
                }else {
                    showCalendar(str);
                }
            }
        });
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.editTextTime);
                String str = editText.getText().toString();
                if(str.equals("")){
                    showWatch();
                }else {
                    showWatch(str);
                }
            }
        });

    }

    private void showCalendar(){
        DatePickerDialog dialog = new DatePickerDialog();
        dialog.setOnDateSetListener(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                NumberFormat formatter = new DecimalFormat("00");
                String date = String.valueOf(year) + "/" +
                        formatter.format(monthOfYear + 1) + "/" +
                        formatter.format(dayOfMonth);
                TextView textView = findViewById(R.id.textViewDate);
                textView.setText(date);
            }
        });
        dialog.show(getFragmentManager(), "");
    }

    private void showCalendar(String date){
        int year = Integer.parseInt(date.substring(0, 4));
        int month = Integer.parseInt(date.substring(5, 7)) - 1;
        int day = Integer.parseInt(date.substring(8, 10));
        DatePickerDialog dialog = DatePickerDialog.newInstance(new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePickerDialog view, int year, int monthOfYear, int dayOfMonth) {
                NumberFormat formatter = new DecimalFormat("00");
                String date = String.valueOf(year) + "/" +
                        formatter.format(monthOfYear + 1) + "/" +
                        formatter.format(dayOfMonth);
                TextView textView = findViewById(R.id.textViewDate);
                textView.setText(date);
            }
        }, year, month, day);
        dialog.show(getFragmentManager(), "");
    }

    private void showWatch(String time){
        int hour = Integer.parseInt(time.substring(0, 2));
        int min = Integer.parseInt(time.substring(3, 5));
        TimePickerDialog dialog = TimePickerDialog.newInstance(new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(RadialPickerLayout view, int hourOfDay, int minute) {
                NumberFormat formatter = new DecimalFormat("00");
                String time = formatter.format(hourOfDay) + ":" +
                        formatter.format(minute);
                TextView textView = findViewById(R.id.textViewTime);
                textView.setText(time);
            }
        }, hour, min, true);
        dialog.show(getFragmentManager(), "");
    }

    private void showWatch(){
        SimpleDateFormat formater = new SimpleDateFormat("HH:mm", Locale.getDefault());
        String time = formater.format(new Date());
        showWatch(time);
    }

}
