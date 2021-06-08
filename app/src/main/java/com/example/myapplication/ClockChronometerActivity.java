package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;

import static com.example.myapplication.R.id.CCA_btn_stop;

public class ClockChronometerActivity extends AppCompatActivity {

    private Button btnstart, btnstop, btnreset;
    private Chronometer chronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock_chronometer);

        btnstart = findViewById(R.id.CCA_btn_start);
        btnstop = findViewById(CCA_btn_stop);
        btnreset = findViewById(R.id.CCA_btn_reset);
        chronometer = findViewById(R.id.CCA_chron);

        btnstart.setOnClickListener(listener);
        btnstop.setOnClickListener(listener);
        btnreset.setOnClickListener(listener);
    }

    View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.CCA_btn_start:
                    chronometer.start();
                    break;
                case R.id.CCA_btn_stop:
                    chronometer.stop();
                    break;
                case R.id.CCA_btn_reset:
                    chronometer.setBase(SystemClock.elapsedRealtime());
                    break;
                default:
                    break;
            }
        }
    };
}
