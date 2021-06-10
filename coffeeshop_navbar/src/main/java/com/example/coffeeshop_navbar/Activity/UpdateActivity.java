package com.example.coffeeshop_navbar.Activity;

import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeshop_navbar.R;
import com.example.coffeeshop_navbar.db.DBUtil;
import com.example.coffeeshop_navbar.value.Shop;

import java.io.File;

public class UpdateActivity extends AppCompatActivity {
    private EditText editText_name, editText_address, editText_tel;
    private Button btn_ok, btn_cancel;
    private String id;
    private String img_name;

    private String DB_DIR = "databases";
    private String DB_NAME = "coffeeshop";

    private ApplicationInfo appInfo;
    private DBUtil dbUtil;
    private String databasePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        init();

        Bundle bundle = this.getIntent().getExtras();
        Shop shop = (Shop) bundle.getSerializable("shop");

        editText_name.setText(shop.getShop_name());
        editText_address.setText(shop.getShop_address());
        editText_tel.setText(shop.getTel());
        id = shop.getShop_id();
        img_name = shop.getImg_name();

        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        String packageName = this.getPackageName();

        try {
            appInfo = this.getPackageManager().getApplicationInfo(
                    packageName, PackageManager.GET_META_DATA);
            String dbDir = appInfo.dataDir + File.separator + DB_DIR;
            File file = new File(dbDir);
            if (!file.exists()) {
                file.mkdir();
            }
            databasePath = appInfo.dataDir + File.separator + DB_DIR
                    + File.separator + DB_NAME;

        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        dbUtil = DBUtil.getInstance(databasePath);
        if (dbUtil.openDB() == -1) {
            Toast.makeText(this, "数据库打开失败", Toast.LENGTH_SHORT).show();
            finish();
        }

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = String.valueOf(editText_name.getText());
                String address = String.valueOf(editText_address.getText());
                String tel = String.valueOf(editText_tel.getText());
                Shop update = new Shop();

                update.setShop_name(name);
                update.setShop_address(address);
                update.setTel(tel);
                update.setImg_name(img_name);

                dbUtil.updateOneData(id, update);
                Toast.makeText(UpdateActivity.this, "修改成功！", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void init() {
        editText_address = findViewById(R.id.UA_edit_address);
        editText_name = findViewById(R.id.UA_edit_name);
        editText_tel = findViewById(R.id.UA_edit_tel);
        btn_cancel = findViewById(R.id.UA_btn_cancel);
        btn_ok = findViewById(R.id.UA_btn_ok);
    }
}
