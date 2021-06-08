package com.example.gridview;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    final int[] img = {
            R.drawable.a, R.drawable.b, R.drawable.c, R.drawable.d, R.drawable.e, R.drawable.f, R.drawable.g, R.drawable.h, R.drawable.i
    };
    final String[] title = {
            "通讯录", "电话", "QQ", "短信", "微信", "电子邮件", "时钟", "录音机", "扫一扫", "下载管理"
    };

    ArrayList<Map<String, Object>> list;
    private GridView gridView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initview();
    }

    private void initview() {
        list = new ArrayList<>();
        gridView = findViewById(R.id.CA_gridview);

        SimpleAdapter adapter = new SimpleAdapter(this, setlist(), R.layout.item, new String[]{"img", "title"}, new int[]{R.id.CA_img, R.id.CA_txtview_title});
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        Intent intent = new Intent();
                        intent.setClass(MainActivity.this, ContactAcitivity.class);
                        startActivity(intent);
                        break;
                    case 1:
                        Intent intenta = new Intent();
                        intenta.setAction(Intent.ACTION_DIAL);
                        startActivity(intenta);
                        break;
                    case 2:
                        Intent intentc = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + "10086"));
                        startActivity(intentc);
                        break;
                }
            }
        });
    }

    private List<Map<String, Object>> setlist() {
        for (int i = 0; i < img.length; i++) {
            Map<String, Object> map = new HashMap<>();
            map.put("img", img[i]);
            map.put("title", title[i]);
            list.add(map);
        }
        return list;
    }
}
