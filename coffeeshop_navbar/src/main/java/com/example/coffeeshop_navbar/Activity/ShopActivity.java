package com.example.coffeeshop_navbar.Activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
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

        listView = findViewById(R.id.SA_listview);

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
        dbUtil.openDB();

        shops = dbUtil.queryAllShops();
        data = new ArrayList<>();

        for (Shop shop : shops) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("shop_name", shop.getShop_name());
            map.put("shop_address", shop.getShop_address());
            map.put("shop_tel", shop.getTel());
            String img_name = shop.getImg_name();
            int img_id = getResources().getIdentifier(img_name, "drawable", getPackageName());
            map.put("img_id", img_id);

            data.add(map);
        }

        MyAdapter adapter = new MyAdapter(this, data, R.layout.item_listview_shop,
                new String[]{"shop_name", "shop_address", "shop_tel", "img_id"}, new int[]{R.id.shop_name, R.id.shop_address, R.id.shop_tel, R.id.iv_img});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(ShopActivity.this, ShopItemActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("shop", shops[position]);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        registerForContextMenu(listView);
    }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.shopchange_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int index = (int) menuInfo.id;
        Bundle bundle = new Bundle();
        switch (item.getItemId()) {
            case R.id.add:
                Intent add = new Intent(ShopActivity.this, AddActivity.class);
                startActivity(add);
                break;
            case R.id.update:
                Intent update = new Intent(ShopActivity.this, UpdateActivity.class);
                bundle.putSerializable("shop", shops[index]);
                update.putExtras(bundle);
                startActivity(update);
                break;
            case R.id.delete:
                new AlertDialog.Builder(ShopActivity.this)
                        .setMessage("?????????????????????")
                        .setPositiveButton("???", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish();
                            }
                        })
                        .setNegativeButton("???", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dbUtil.deleteOneData(shops[index].getShop_id());
                                Toast.makeText(ShopActivity.this, "???????????????", Toast.LENGTH_SHORT).show();
                            }
                        }).show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    class MyAdapter extends SimpleAdapter {

        MyAdapter(Context context, List<? extends Map<String, ?>> data, int resource, String[] from, int[] to) {
            super(context, data, resource, from, to);

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            return super.getView(position, convertView, parent);
        }
    }
}
