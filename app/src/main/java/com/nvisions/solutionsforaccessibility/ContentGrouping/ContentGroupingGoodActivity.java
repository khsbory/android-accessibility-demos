package com.nvisions.solutionsforaccessibility.ContentGrouping;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageButton;
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

        TextView songName = (TextView) findViewById(R.id.value_song_name);
        TextView songLabel = (TextView) findViewById(R.id.label_song_name);
        songName.setContentDescription(songLabel.getText() + ": " + songName.getText());
    }
}

