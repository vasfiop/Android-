package com.example.sharedpreference;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editText_username, editText_ps;
    private Button button_login;
    private String file_name = "first_text";
    private final static int MODE = MODE_PRIVATE;
    private String username;
    private String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText_username = findViewById(R.id.MA_edittxt_username);
        editText_ps = findViewById(R.id.MA_edittxt_password);
        button_login = findViewById(R.id.MA_btn_login);

        getStr();

        button_login.setOnClickListener(new MyClickListener());
    }

    public void setStr() {
        SharedPreferences sharedPreferences = getSharedPreferences(file_name, MODE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("username", editText_username.getText().toString());
        editor.putString("passworld", editText_ps.getText().toString());
        editor.apply();
    }

    public void getStr() {
        SharedPreferences sharedPreferences = getSharedPreferences(file_name, MODE);

        username = sharedPreferences.getString("username", "null");
        password = sharedPreferences.getString("passworld", "null");

        editText_username.setText(username);
        editText_ps.setText(password);
    }

    private class MyClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            setStr();
            Toast.makeText(MainActivity.this, "以保存用户信息", Toast.LENGTH_SHORT).show();
        }
    }

}
