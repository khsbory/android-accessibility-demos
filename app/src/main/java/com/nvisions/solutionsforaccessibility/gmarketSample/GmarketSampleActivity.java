package com.nvisions.solutionsforaccessibility.gmarketSample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nvisions.solutionsforaccessibility.R;

public class GmarketSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gmarket_sample);
setTitle("복잡한 레이아웃에서의 대체 텍스트");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Button accessibilityButton = findViewById(R.id.withAccessibility);
        Button withoutAccessibilityButton = findViewById(R.id.withoutAccessibility);
        accessibilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WithAccessibilityActivity.class);
                startActivity(intent);
            }
        });
        withoutAccessibilityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WithoutAccessibilityActivity.class);
                startActivity(intent);
            }
        });
    }
}

