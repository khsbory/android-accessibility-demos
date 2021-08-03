package com.nvisions.solutionsforaccessibility.customviewgroup;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.nvisions.solutionsforaccessibility.R;

public class CustomViewGroupBadActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group_bad);
    }

    public void clickView(View view) {
        Toast.makeText(this, "테스트 입니다.", Toast.LENGTH_SHORT).show();
    }
}