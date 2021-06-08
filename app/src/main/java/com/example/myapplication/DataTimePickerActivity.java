package com.example.myapplication;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;
import java.util.Locale;

public class DataTimePickerActivity extends AppCompatActivity {

    private int my_year, my_month, my_day, my_hour, my_min, my_second;

    private Button btndate, btntime;
    private TextView txtshow;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        calendar = Calendar.getInstance(Locale.CHINA);
        my_year = calendar.get(Calendar.YEAR);
        my_month = calendar.get(Calendar.MONTH);
        my_day = calendar.get(Calendar.DAY_OF_MONTH);
        my_hour = calendar.get(Calendar.HOUR_OF_DAY);
        my_min = calendar.get(Calendar.MINUTE);
        my_second = calendar.get(Calendar.SECOND);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_time_picker);

        btndate = findViewById(R.id.DTP_btn_datedialog);
        btntime = findViewById(R.id.DTP_btn_timedialog);
        txtshow = findViewById(R.id.DTP_txtview_showtime);

        showDateTime();
        btndate.setOnClickListener(listener);
        btntime.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.DTP_btn_datedialog:
                    datePickerDialog = new DatePickerDialog(DataTimePickerActivity.this,
                            dateSetListener, my_year, my_month, my_day);
                    datePickerDialog.show();
                    break;
                case R.id.DTP_btn_timedialog:
                    timePickerDialog = new TimePickerDialog(DataTimePickerActivity.this,
                            timeSetListener, my_hour, my_min, true);
                    timePickerDialog.show();
                    break;
                default:
                    break;
            }
        }
    };

    DatePickerDialog.OnDateSetListener dateSetListener = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            my_year = year;
            my_month = month;
            my_day = dayOfMonth;
            showDateTime();
        }
    };

    TimePickerDialog.OnTimeSetListener timeSetListener = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            my_hour = hourOfDay;
            my_min = minute;
            showDateTime();
        }
    };

    private void showDateTime() {
        txtshow.setText(new StringBuffer()
                .append(my_year).append("/")
                .append(formatString(my_month + 1)).append("/")
                .append(formatString(my_day)).append(" ")
                .append(formatString(my_hour)).append(":")
                .append(formatString(my_min)).append(":")
                .append(formatString(my_second)));
    }

    public String formatString(int x) {
        String str = Integer.toString(x);
        if (str.length() == 1)
            str = "0" + str;
        return str;
    }
}
