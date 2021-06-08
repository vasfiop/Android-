package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.Toast;

public class ExpandableListviewActivity extends AppCompatActivity {
    private ExpandableListAdapter expandableListAdapter = null;
    private ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_listview);

        expandableListAdapter = new ExpandableListview(ExpandableListviewActivity.this);
        expandableListView = findViewById(R.id.ELA_expandable);
        expandableListView.setAdapter(expandableListAdapter);

//        具体某一选项的监听
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                Toast.makeText(ExpandableListviewActivity.this, "你点击了:" + parent.getItemAtPosition(groupPosition), Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        expandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(ExpandableListviewActivity.this,"你点击了:"+expandableListAdapter.getChild(groupPosition,childPosition),Toast.LENGTH_SHORT).show();
                return false;
            }
        });
    }
}
