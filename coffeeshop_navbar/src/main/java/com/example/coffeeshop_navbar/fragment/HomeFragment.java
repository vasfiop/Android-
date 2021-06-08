package com.example.coffeeshop_navbar.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.coffeeshop_navbar.Activity.ShopActivity;
import com.example.coffeeshop_navbar.R;
import com.example.coffeeshop_navbar.org.taptwo.android.widget.CircleFlowIndicator;
import com.example.coffeeshop_navbar.org.taptwo.android.widget.ViewFlow;
import com.example.coffeeshop_navbar.viewflow.ImageAdapter;

public class HomeFragment extends Fragment {
    private View view;
    private ViewFlow viewFlow;
    private CircleFlowIndicator circleFlowIndicator;
    private LinearLayout linearLayout_1;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        Toast.makeText(getActivity(), "success", Toast.LENGTH_SHORT).show();

        init();
        set();

        return view;
    }

    private void init() {
        viewFlow = view.findViewById(R.id.viewFlow);
        circleFlowIndicator = view.findViewById(R.id.flowInficator);
        linearLayout_1 = view.findViewById(R.id.ll_module1);
    }

    private void set() {
        viewFlow.setAdapter(new ImageAdapter(getActivity()));
        viewFlow.setmSideBuffer(3);
        viewFlow.setTimeSpan(4000);
        viewFlow.setSelection(3 * 1000);
        viewFlow.startAutoFlowTimer();
        linearLayout_1.setOnClickListener(new ShopClickLinster());
    }

    class ShopClickLinster implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.ll_module1:
                    Intent intent = new Intent(getActivity(), ShopActivity.class);
                    startActivity(intent);
            }
        }
    }
}
