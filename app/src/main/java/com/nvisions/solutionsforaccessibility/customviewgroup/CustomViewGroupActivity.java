package com.nvisions.solutionsforaccessibility.customviewgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.nvisions.solutionsforaccessibility.R;

public class CustomViewGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group);
        init();
    }

    private void init() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(R.string.custom_viewgroup_title);

        Button button1 = findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomViewGroupActivity.this, CustomViewGroupGoodActivity.class);
                startActivity(intent);
            }
        });


        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomViewGroupActivity.this, CustomViewGroupBadActivity.class);
                startActivity(intent);
            }
        });

    }
}