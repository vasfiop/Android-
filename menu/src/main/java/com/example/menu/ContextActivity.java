package com.example.menu;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class ContextActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_context);

        textView = findViewById(R.id.CA_txt);
        this.registerForContextMenu(textView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.red:
                textView.setBackgroundColor(Color.RED);
                break;
            case R.id.blue:
                textView.setBackgroundColor(Color.BLUE);
                break;
            case R.id.green:
                textView.setBackgroundColor(Color.GREEN);
                break;
        }
        return super.onContextItemSelected(item);
    }
}
