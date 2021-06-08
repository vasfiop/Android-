package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class CheckBoxActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton radioButton_1, radioButton_2, radioButton_3;
    private TextView txtview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_box);

        init();
        radioGroup.setOnCheckedChangeListener(listener);
    }

    RadioGroup.OnCheckedChangeListener listener = new RadioGroup.OnCheckedChangeListener() {
        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.CBA_radiob_first:
                    txtview.setText("您选择了: " + radioButton_1.getText());
                    break;
                case R.id.CBA_radiob_dec:
                    txtview.setText("您选择了: " + radioButton_2.getText());
                    break;
                case R.id.CBA_radiob_thir:
                    txtview.setText("您选择了: " + radioButton_3.getText());
                    break;
                default:
                    break;
            }
        }
    };

    private void init() {
        radioButton_1 = findViewById(R.id.CBA_radiob_first);
        radioButton_2 = findViewById(R.id.CBA_radiob_dec);
        radioButton_3 = findViewById(R.id.CBA_radiob_thir);

        radioGroup = findViewById(R.id.CBA_radiog);
        txtview = findViewById(R.id.CBA_txtview_out);
    }
}
