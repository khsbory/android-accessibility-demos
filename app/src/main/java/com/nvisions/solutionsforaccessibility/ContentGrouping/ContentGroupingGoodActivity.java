package com.nvisions.solutionsforaccessibility.ContentGrouping;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.nvisions.solutionsforaccessibility.R;

public class ContentGroupingGoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_grouping_good);
        setTitle(getString(R.string.goodExample));
        if(getActionBar() != null) {
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }


        RelativeLayout song_data_container = (RelativeLayout)findViewById(R.id.song_data_container);
        song_data_container.setContentDescription(getString(R.string.label_song_name) + ": " + getString(R.string.value_song_name) + ", " + getString(R.string.label_song_artists) + ": " + getString(R.string.value_song_artists) + ", " + getString(R.string.label_song_cost) + ": " + getString(R.string.value_song_cost));

    }
}

