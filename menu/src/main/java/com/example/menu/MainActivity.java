package com.example.menu;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.SubMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.lang.reflect.Method;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);

        MenuItem searchItem = menu.add(Menu.NONE, 1, 1, "搜索");
        searchItem.setIcon(R.drawable.test).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        MenuItem printItem = menu.add(Menu.NONE, 2, 2, "打印");
        printItem.setIcon(R.drawable.test).setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);
        MenuItem saveItem = menu.add(Menu.NONE, 3, 3, "保存");
        saveItem.setIcon(R.drawable.test);

        SubMenu subMenu = menu.addSubMenu(Menu.NONE, 4, 4, "设置");
        subMenu.setIcon(R.drawable.test);

        MenuItem aboutMenu = subMenu.add(Menu.NONE, 5, 5, "关于");
        aboutMenu.setIcon(R.drawable.test);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case 1:
                Toast.makeText(this, "跳转到首页", Toast.LENGTH_SHORT).show();
                break;
            case 2:
                this.finish();
                Toast.makeText(this, "退出", Toast.LENGTH_SHORT).show();
                break;
        }
        return true;

    }

    //解决菜单不能显示图片的问题的方法
    @Override
    public boolean onMenuOpened(int featureId, Menu menu) {
        if (menu != null) {
            if (menu.getClass().getSimpleName().equalsIgnoreCase("MenuBuilder")) {
                try {
                    Method method = menu.getClass().getDeclaredMethod("setOptionalIconsVisible", Boolean.TYPE);
                    method.setAccessible(true);
                    method.invoke(menu, true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return super.onMenuOpened(featureId, menu);
    }

}
