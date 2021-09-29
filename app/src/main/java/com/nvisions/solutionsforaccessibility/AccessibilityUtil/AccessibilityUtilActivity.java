package com.nvisions.solutionsforaccessibility.AccessibilityUtil;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.nvisions.solutionsforaccessibility.ContentGrouping.ContentGroupingBadActivity;
import com.nvisions.solutionsforaccessibility.ContentGrouping.ContentGroupingGoodActivity;
import com.nvisions.solutionsforaccessibility.R;

public class AccessibilityUtilActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accessibility_util);

        if(getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }

        initViews();

    }

    private void initViews() {

        ImageView radioButton1 = findViewById(R.id.radioButton1);
        radioButton1.setOnClickListener(v -> v.setSelected(!v.isSelected()));

        ImageView radioButton2 = findViewById(R.id.radioButton2);
        radioButton2.setOnClickListener(v -> v.setSelected(!v.isSelected()));

        AccessibilityUtil.setAsRadioButton(radioButton1);
        AccessibilityUtil.setAsRadioButton(radioButton2);
    }


}
