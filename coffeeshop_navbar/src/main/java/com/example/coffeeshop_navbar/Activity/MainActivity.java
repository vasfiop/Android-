package com.example.coffeeshop_navbar.Activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.coffeeshop_navbar.R;
import com.example.coffeeshop_navbar.fragment.CartFragment;
import com.example.coffeeshop_navbar.fragment.HomeFragment;
import com.example.coffeeshop_navbar.fragment.MeFragment;
import com.example.coffeeshop_navbar.fragment.SearchFragment;
import com.example.coffeeshop_navbar.fragment.SortFragment;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {
    private RadioButton rbhome, rbcart, rbsort, rbsearch, rbme;
    private RadioGroup rgnav;
    private ViewPager viewPager;
    private ArrayList<Fragment> list;

    private Boolean isExited = false;

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            isExited = false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navbar);

        initviews();
    }

    class viewPagerListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            int current = viewPager.getCurrentItem();
            switch (current) {
                case 0:
                    rgnav.check(R.id.MA_radiobtn_home);
                    break;
                case 1:
                    rgnav.check(R.id.MA_radiobtn_cart);
                    break;
                case 2:
                    rgnav.check(R.id.MA_radiobtn_sort);
                    break;
                case 3:
                    rgnav.check(R.id.MA_radiobtn_search);
                    break;
                case 4:
                    rgnav.check(R.id.MA_radiobtn_me);
                    break;
                default:
                    break;
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    class PageAdapter extends FragmentPagerAdapter {

        ArrayList<Fragment> fragments;

        PageAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
            super(fm);
            this.fragments = fragments;
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }
    }

    private void initviews() {
        rbhome = findViewById(R.id.MA_radiobtn_home);
        rbcart = findViewById(R.id.MA_radiobtn_cart);
        rbsearch = findViewById(R.id.MA_radiobtn_search);
        rbsort = findViewById(R.id.MA_radiobtn_sort);
        rbme = findViewById(R.id.MA_radiobtn_me);

        rgnav = findViewById(R.id.MA_radiogroup);
        rgnav.setOnCheckedChangeListener(new CheckedChangeLinster());

        viewPager = findViewById(R.id.MA_viewpager_fragment);

        list = new ArrayList<Fragment>();

        HomeFragment homeFragment = new HomeFragment();
        CartFragment cartFragment = new CartFragment();
        MeFragment meFragment = new MeFragment();
        SearchFragment searchFragment = new SearchFragment();
        SortFragment sortFragment = new SortFragment();

        list.add(homeFragment);
        list.add(cartFragment);
        list.add(sortFragment);
        list.add((searchFragment));
        list.add(meFragment);

        viewPager.setAdapter(new PageAdapter(getSupportFragmentManager(), list));
        viewPager.setCurrentItem(0);

        viewPager.setOnPageChangeListener(new viewPagerListener());


    }

    class CheckedChangeLinster implements RadioGroup.OnCheckedChangeListener {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            int current = 0;
            switch (checkedId) {
                case R.id.MA_radiobtn_home:
                    current = 0;
                    break;
                case R.id.MA_radiobtn_cart:
                    current = 1;
                    break;
                case R.id.MA_radiobtn_sort:
                    current = 2;
                    break;
                case R.id.MA_radiobtn_search:
                    current = 3;
                    break;
                case R.id.MA_radiobtn_me:
                    current = 4;
                    break;
                default:
                    break;
            }
            if (viewPager.getCurrentItem() != current)
                viewPager.setCurrentItem(current);
        }
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            exit();
            return false;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    private void exit() {
        if (!isExited) {
            isExited = true;
            Toast.makeText(MainActivity.this, "再按一次退出程序", Toast.LENGTH_SHORT).show();
            handler.sendEmptyMessageDelayed(0, 2000);
        } else {
            System.exit(0);
        }
    }
}
