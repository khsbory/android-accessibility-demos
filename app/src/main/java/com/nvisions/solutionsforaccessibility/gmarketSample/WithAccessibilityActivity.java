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
        ViewCompat.setAccessibilityDelegate(meat, new AccessibilityDelegateCompat() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setRoleDescription("tab");
                ColorStateList mList = meat.getTextColors();
                int color = mList.getDefaultColor();
                switch (color) {
                    case Color.GREEN:
                        info.setSelected(true);
                        break;
                    case Color.GRAY:
                        info.setSelected(false);
                        break;
                }
            }
        });

        ViewCompat.setAccessibilityDelegate(vegetable, new AccessibilityDelegateCompat() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setRoleDescription("tab");
                ColorStateList mList2 = vegetable.getTextColors();
                int color2 = mList2.getDefaultColor();
                switch (color2) {
                    case Color.GREEN:
                        info.setSelected(true);
                        break;
                    case Color.GRAY:
                        info.setSelected(false);
                        break;
                }
            }
        });

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
                vegetable.setTextColor(Color.GRAY);
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
                vegetable.setTextColor(Color.GREEN);
            }
        });
    }
}