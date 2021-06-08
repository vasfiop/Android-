package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class TestActivity extends AppCompatActivity {

    private EditText etText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test4_29);

        etText = findViewById(R.id.text_1);
        etText.addTextChangedListener(new TextWatcher() {

            private final String [] errorstr = {"TMD","CNMD","陈小龙"};
            private String oldText;
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                oldText = etText.getText().toString();
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String newText = etText.getText().toString();
                if(newText.equals(oldText))
                    return;
                for(String index:errorstr)
                    if(newText.indexOf(index)!=-1)
                        newText = newText.replaceAll(index,"***");
                 etText.setText(newText);
                 etText.invalidate();
                 etText.setSelection(newText.length());
            }
        });
    }
}
