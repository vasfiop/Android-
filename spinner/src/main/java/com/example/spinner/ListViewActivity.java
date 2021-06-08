package com.example.spinner;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListViewActivity extends AppCompatActivity {
    final private String[] names = {"马云", "王健林", "李彦宏" };
    final private int[] imgid = {R.drawable.mayun, R.drawable.wangjianlin, R.drawable.liyanhong};
    final private String[] texts = {"123-4567-890", "888-9999-000", "111-2222-333" };

    private SimpleAdapter simpleAdapter;
    private ListView listView;
    private ArrayList<Map<String, Object>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listView = findViewById(R.id.LVA_listview_show);
        list = new ArrayList<Map<String, Object>>();

        simpleAdapter = new SimpleAdapter(this, SetData(), R.layout.listviewitems, new String[]{"name", "pic", "txt" }, new int[]{R.id.LVA_txtview_names, R.id.LVA_img, R.id.LVA_txtview_texts});
        listView.setAdapter(simpleAdapter);
    }

    private List<Map<String, Object>> SetData() {
        for (int i = 0; i < names.length; i++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("pic", imgid[i]);
            map.put("name", names[i]);
            map.put("txt", texts[i]);
            list.add(map);
        }
        return list;
    }
}
