package com.example.coffeeshop_navbar.Activity;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeshop_navbar.R;
import com.example.coffeeshop_navbar.db.DBUtil;
import com.example.coffeeshop_navbar.value.Shop;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ShopActivity extends AppCompatActivity {
    private ArrayList<HashMap<String, Object>> data;
    private ListView listView;
    private final String DB_DIR = "databases";
    private final String DB_NAME = "coffeeshop";
    private String databasePath;
    private ApplicationInfo appInfo;
    private DBUtil dbUtil;
    private Shop[] shops;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        init(this);

        set();
    }

    private void init(Context context) {
        listView = findViewById(R.id.SA_listview);

        String packageName = context.getPackageName();

        try {
            appInfo = context.getPackageManager().getApplicationInfo(
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
        dbUtil.openDB();
    }

    private void set() {
        shops = dbUtil.queryAllShops();
        data = new ArrayList<>();
        for (int i = 0; i < shops.length; i++) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("shop_name", shops[i].getShop_name());
            map.put("shop_address", shops[i].getShop_address());
            map.put("shop_tel", shops[i].getTel());
            String img_name = shops[i].getImg_name();
            int img_id = getResources().getIdentifier(img_name, "drawable", getPackageName());
            map.put("img_id", img_id);

            data.add(map);
        }

        MyAdapter adapter = new MyAdapter(this, data, R.layout.item_listview_shop, new String[]{"shop_name", "shop_address", "shop_tel", "img_id"}, new int[]{R.id.shop_name, R.id.shop_address, R.id.shop_tel, R.id.iv_img});
        listView.setAdapter(adapter);
    }

    class MyAdapter extends SimpleAdapter {

        public MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }
    }
}
