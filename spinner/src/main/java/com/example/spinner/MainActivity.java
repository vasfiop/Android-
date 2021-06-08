package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private TextView textView;
    private Spinner spinner;
    private ArrayList<Map<String, Object>> list;
    private String[] names = {"马云", "王健林", "李彦宏"};
    private int[] imgid = {R.drawable.mayun, R.drawable.wangjianlin, R.drawable.liyanhong};
    private SimpleAdapter simpleAdapter;

    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        init();
        SetData();
        simpleAdapter = new SimpleAdapter(this, list, R.layout.items, new String[]{"pic", "name"}, new int[]{R.id.MA_img, R.id.MA_txtview_names});
        spinner.setDropDownVerticalOffset(200);
        spinner.setAdapter(simpleAdapter);
        spinner.setOnItemSelectedListener(listener);

    }

    AdapterView.OnItemSelectedListener listener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            HashMap<String, Object> map = (HashMap<String, Object>) simpleAdapter.getItem(position);
            textView.setText("您选择了: " + map.get("name"));
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    };

    private void init() {
        list = new ArrayList<Map<String, Object>>();
        textView = findViewById(R.id.MA_txtview_show);
        spinner = findViewById(R.id.MA_spinner);
    }

    private List<Map<String, Object>> SetData() {

        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", imgid[i]);
            map.put("name", names[i]);
            list.add(map);
        }
        return list;
    }
}
