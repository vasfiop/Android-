package com.example.sql;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private Button btn_add, btn_showall, btn_cleanshow, btn_alldelete;
    private Button btn_iddelete, btn_idselect, btn_idupdata;
    private EditText editText_username, editText_password, editText_height;
    private EditText editText_id;
    private TextView txt_answer;
    private DBUtil dbUtil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btninit();
        dbUtil = new DBUtil(this);
        dbUtil.open();

        btn_add.setOnClickListener(new MyClick());
//        显示全部的事件
        btn_showall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                People[] peoples = dbUtil.showall();
                if (peoples == null) {
                    txt_answer.setText("error!数据库未查询到数据!");
                    Toast.makeText(MainActivity.this, "error!数据库未查询到数据!", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(MainActivity.this, "success!", Toast.LENGTH_SHORT).show();
                String msg = "";
                for (int i = 0; i < peoples.length; i++)
                    msg += peoples[i].toString() + "\n";
                txt_answer.setText("查询到数据如下: \n" + msg);
            }
        });

        btn_alldelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbUtil.deleteall();
                txt_answer.setText("已删除全部数据!");
                Toast.makeText(MainActivity.this, "success!delete all!", Toast.LENGTH_SHORT).show();
            }
        });

        btn_iddelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long user_id = Integer.parseInt(editText_id.getText().toString());
                if (dbUtil.deleteid(user_id) > 0) {
                    txt_answer.setText("已删除" + user_id + "的数据!");
                    Toast.makeText(MainActivity.this, "success!delete by " + user_id, Toast.LENGTH_SHORT).show();
                } else {
                    txt_answer.setText("删除失败！");
                    Toast.makeText(MainActivity.this, "error!delete fail", Toast.LENGTH_SHORT).show();
                }

            }
        });

        btn_cleanshow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_username.setText(null);
                editText_password.setText(null);
                editText_height.setText(null);
                editText_id.setText(null);
                txt_answer.setText(null);
            }
        });

        btn_idselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long user_id = Integer.parseInt(editText_id.getText().toString());
                People[] peoples = dbUtil.selectid(user_id);
                if (peoples == null) {
                    txt_answer.setText("未查到" + user_id);
                    Toast.makeText(MainActivity.this, "Error!Not found " + user_id, Toast.LENGTH_SHORT).show();
                } else {
                    String name = peoples[0].getName();
                    String password = peoples[0].getPassword();
                    float height = peoples[0].getHeight();
                    editText_username.setText(name);
                    editText_password.setText(password);
                    editText_height.setText(String.valueOf(height));
                    txt_answer.setText("查询到的数据为:" + peoples[0].toString());
                    Toast.makeText(MainActivity.this, "success!find " + user_id, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_idupdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                People people = new People();
                people.setName(editText_username.getText().toString());
                people.setPassword(editText_password.getText().toString());
                people.setHeight(Float.parseFloat(editText_height.getText().toString()));
                long user_id = Integer.parseInt(editText_id.getText().toString());

                long position = dbUtil.updata(user_id, people);
                if (position == -1) {
                    txt_answer.setText("修改错误！");
                    Toast.makeText(MainActivity.this, "error!", Toast.LENGTH_SHORT).show();
                } else {
                    txt_answer.setText(people.toString());
                    Toast.makeText(MainActivity.this, "sccess!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @SuppressLint("WrongViewCast")
    private void btninit() {
        btn_add = findViewById(R.id.MA_btn_add);
        btn_showall = findViewById(R.id.MA_btn_showall);
        btn_cleanshow = findViewById(R.id.MA_btn_cleanshow);
        btn_alldelete = findViewById(R.id.MA_btn_deleteall);
        btn_iddelete = findViewById(R.id.MA_btn_iddelete);
        btn_idselect = findViewById(R.id.MA_btn_idselect);
        btn_idupdata = findViewById(R.id.MA_btn_idupdata);

        editText_username = findViewById(R.id.MA_edittxt_username);
        editText_password = findViewById(R.id.MA_edittxt_password);
        editText_height = findViewById(R.id.MA_edittxt_height);
        editText_id = findViewById(R.id.MA_edittxt_id);
        txt_answer = findViewById(R.id.MA_txt_showanswer);
    }

    private class MyClick implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            People people = new People();
            people.setName(editText_username.getText().toString());
            people.setPassword(editText_password.getText().toString());
            people.setHeight(Float.parseFloat(editText_height.getText().toString()));

            long position = dbUtil.insert(people);
            if (position == -1) {
                txt_answer.setText("添加错误！");
                Toast.makeText(MainActivity.this, "error!", Toast.LENGTH_SHORT).show();
            } else {
                txt_answer.setText("数据添加成功ID: " + position);
                Toast.makeText(MainActivity.this, "sccess!", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
