package com.example.myapplication;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.MultiAutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import org.w3c.dom.Text;

public class AutoCompleteTextView extends AppCompatActivity {

    private android.widget.AutoCompleteTextView actv;
    private MultiAutoCompleteTextView mactv;
    private SeekBar seekBar;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto_complete_text_view);

        actv = findViewById(R.id.autoctv);
        mactv = findViewById(R.id.multactv);

        String[] items = {"Dalian","Dali","Beijing","Qiqihaer","Qinhuangdao"};

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                AutoCompleteTextView.this,
                android.R.layout.simple_dropdown_item_1line,
                items
        );

        actv.setThreshold(1);
        actv.setAdapter(arrayAdapter);

        mactv.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());
        mactv.setAdapter(arrayAdapter);


        seekBar = findViewById(R.id.seekbar);
        textView = findViewById(R.id.textview);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                textView.setText("音量大小: "+seekBar.getProgress());
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
