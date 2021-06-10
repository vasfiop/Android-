package com.example.coffeeshop_navbar.Activity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.coffeeshop_navbar.R;
import com.example.coffeeshop_navbar.value.Shop;

public class ShopItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_item);

        TextView detail = findViewById(R.id.shop_detail);
        ImageView img = findViewById(R.id.iv_img_detail);

        Bundle bundle = this.getIntent().getExtras();
        Shop shop = (Shop) bundle.getSerializable("shop");

        detail.setText("店铺名称:" + shop.getShop_name() + "\n" +
                "店铺地址:" + shop.getShop_address() + "\n" +
                "店铺电话:" + shop.getTel()
        );

        int pic_id = getResources().getIdentifier(shop.getImg_name(), "drawable", this.getPackageName());
        img.setImageResource(pic_id);
    }
}
