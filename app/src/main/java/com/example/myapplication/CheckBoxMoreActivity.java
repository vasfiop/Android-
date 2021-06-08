package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CheckBoxMoreActivity extends AppCompatActivity {
    private CheckBox checkBox1, checkBox2, checkBox3;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box_more);

        init();
        checkBox1.setOnCheckedChangeListener(listener);
        checkBox2.setOnCheckedChangeListener(listener);
        checkBox3.setOnCheckedChangeListener(listener);
    }

    CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            switch (buttonView.getId()) {
                case R.id.CBMA_checkbox_first:
                    textView.setText("您选择的是: " + checkBox1.getText());
                    break;
                case R.id.CBMA_checkbox_dec:
                    textView.setText("您选择的是：" + checkBox2.getText());
                    break;
                case R.id.CBMA_checkbox_three:
                    textView.setText("您选择的是：" + checkBox3.getText());
                    break;
                default:
                    break;
            }
        }
    };

    private void init() {
        checkBox1 = findViewById(R.id.CBMA_checkbox_first);
        checkBox2 = findViewById(R.id.CBMA_checkbox_dec);
        checkBox3 = findViewById(R.id.CBMA_checkbox_three);
        textView = findViewById(R.id.CBMA_txtview_out);
    }
}
