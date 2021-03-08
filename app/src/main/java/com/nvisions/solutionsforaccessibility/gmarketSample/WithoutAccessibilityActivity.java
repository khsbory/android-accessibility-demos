package com.nvisions.solutionsforaccessibility.gmarketSample;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.nvisions.solutionsforaccessibility.R;
import com.nvisions.solutionsforaccessibility.gmarketSample.fragment.MeatFragment;
import com.nvisions.solutionsforaccessibility.gmarketSample.fragment.VegetableFragment;

public class WithoutAccessibilityActivity extends AppCompatActivity {

    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notaccessibility);
        setTitle("접근성 미적용");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final TextView meat = findViewById(R.id.meat_txt);
        final TextView vegetable = findViewById(R.id.vegetable_txt);
        meat.setTextColor(Color.GREEN);

        Fragment meatFragment = new MeatFragment();
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, meatFragment);
        fragmentTransaction.commit();

        meat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment fragment = new MeatFragment();
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
                Fragment fragment = new VegetableFragment();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                fragmentTransaction.replace(R.id.frame_layout, fragment);
                fragmentTransaction.commit();
                meat.setTextColor(Color.GRAY);
                vegetable.setTextColor(Color.GREEN);
            }
        });
    }
}