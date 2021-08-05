package com.nvisions.solutionsforaccessibility.customviewgroup2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.nvisions.solutionsforaccessibility.R;
import com.nvisions.solutionsforaccessibility.customviewgroup.CustomViewGroupActivity;
import com.nvisions.solutionsforaccessibility.customviewgroup.CustomViewGroupBadActivity;
import com.nvisions.solutionsforaccessibility.customviewgroup.CustomViewGroupGoodActivity;

public class CustomViewGroup2Activity extends AppCompatActivity {

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
                Intent intent = new Intent(CustomViewGroup2Activity.this, CustomViewGroup2GoodActivity.class);
                startActivity(intent);
            }
        });


        Button button2 = findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomViewGroup2Activity.this, CustomViewGroup2BadActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}