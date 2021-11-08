package com.nvisions.solutionsforaccessibility.gmarketSample;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nvisions.solutionsforaccessibility.AccessibilityUtil.AccessibilityUtil;
import com.nvisions.solutionsforaccessibility.R;
import com.nvisions.solutionsforaccessibility.gmarketSample.fragment.MeatFragment;
import com.nvisions.solutionsforaccessibility.gmarketSample.fragment.MeatFragment2;
import com.nvisions.solutionsforaccessibility.gmarketSample.fragment.VegetableFragment;
import com.nvisions.solutionsforaccessibility.gmarketSample.fragment.VegetableFragment2;

public class WithAccessibilityActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmarket_sample_accessible);
        setTitle("접근성 적용");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final TextView meat = findViewById(R.id.meat_txt);
        final TextView vegetable = findViewById(R.id.vegetable_txt);
        meat.setTextColor(Color.GREEN);
        AccessibilityUtil.setAsTab(meat, true);
        AccessibilityUtil.setAsTab(vegetable, false);
        Fragment meatFragment = new MeatFragment2();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, meatFragment);
        fragmentTransaction.commit();

        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new MeatFragment2();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                fragmentTransaction.commit();
                meat.setTextColor(Color.GREEN);
                AccessibilityUtil.setAsTab(meat, true);
                vegetable.setTextColor(Color.GRAY);
                AccessibilityUtil.setAsTab(vegetable, false);
            }
        });
        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new VegetableFragment2();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                fragmentTransaction.commit();
                meat.setTextColor(Color.GRAY);
                AccessibilityUtil.setAsTab(meat, false);
                vegetable.setTextColor(Color.GREEN);
                AccessibilityUtil.setAsTab(vegetable, true);
            }
        });
    }
}