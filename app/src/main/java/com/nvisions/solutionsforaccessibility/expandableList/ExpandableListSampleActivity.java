package com.nvisions.solutionsforaccessibility.expandableList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.nvisions.solutionsforaccessibility.R;

public class ExpandableListSampleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expandable_sample);
        setTitle("이중 리스트에서의 대체 텍스트");
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

