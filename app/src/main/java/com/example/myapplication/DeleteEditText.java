package com.example.myapplication;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;

public class DeleteEditText extends LinearLayout {

    private EditText editText;
    private ImageButton imageButton;

    public DeleteEditText(Context context, AttributeSet attrs) {
        super(context, attrs);

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.activity_diyctrl, null);
        editText = view.findViewById(R.id.edittxt);
        imageButton = view.findViewById(R.id.imgbutton);
        addView(view);
        init();
    }

    private void init() {
        editText.addTextChangedListener(watcher);
        imageButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                hidenImageButton();
                editText.setText("");
            }
        });
    }

    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (s.length() == 0)
                hidenImageButton();
            else
                showImageButton();
        }


        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void hidenImageButton() {
        if (imageButton.isShown())
            imageButton.setVisibility(View.GONE);
    }

    private void showImageButton() {
        if (!imageButton.isShown())
            imageButton.setVisibility(View.VISIBLE);
    }
}
