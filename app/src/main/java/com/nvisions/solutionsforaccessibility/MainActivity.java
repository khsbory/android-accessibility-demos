package com.nvisions.solutionsforaccessibility;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.nvisions.solutionsforaccessibility.CollapseExpand.CollapseExpandActivity;
import com.nvisions.solutionsforaccessibility.ContentGrouping.ContentGroupingActivity;
import com.nvisions.solutionsforaccessibility.CustomControl.CustomControlActivity;
import com.nvisions.solutionsforaccessibility.DragAndDrop.DragAndDropActivity;
import com.nvisions.solutionsforaccessibility.FullscreenFragmentLayer.FullscreenFragmentLayerActivity;
import com.nvisions.solutionsforaccessibility.HorizontalScroll.HorizontalScrollActivity;
import com.nvisions.solutionsforaccessibility.KeyBoard.KeyBoardActivity;
import com.nvisions.solutionsforaccessibility.LayerFocus.LayerFocusActivity;
import com.nvisions.solutionsforaccessibility.LiveRegion.LiveRegionActivity;
import com.nvisions.solutionsforaccessibility.NestedScroll.NestedScrollActivity;
import com.nvisions.solutionsforaccessibility.NoScrollingRollingBanner.NoScrollingRollingBannerActivity;
import com.nvisions.solutionsforaccessibility.RatingBar.RatingBarActivity;
import com.nvisions.solutionsforaccessibility.ScrollFocus.ScrollFocusActivity;
import com.nvisions.solutionsforaccessibility.TabControl.TabControlActivity;
import com.nvisions.solutionsforaccessibility.Test.TestActivity;
import com.nvisions.solutionsforaccessibility.Video.VideoActivity;
import com.nvisions.solutionsforaccessibility.VoiceSearch.VoiceSearchActivity;
import com.nvisions.solutionsforaccessibility.WebView.WebViewActivity;

import com.nvisions.solutionsforaccessibility.customviewgroup.CustomViewGroupActivity;
import com.nvisions.solutionsforaccessibility.expandableList.ExpandableListSampleActivity;
import com.nvisions.solutionsforaccessibility.gmarketSample.GmarketSampleActivity;
import com.nvisions.solutionsforaccessibility.korailTalk.KorailTalkActivity;
import com.nvisions.solutionsforaccessibility.labelFor.LabelForActivity;
import com.nvisions.solutionsforaccessibility.music.MusicActivity;
import com.nvisions.solutionsforaccessibility.overlay.FocusActivity;
import com.nvisions.solutionsforaccessibility.progress.ProgressBarActivity;
import com.nvisions.solutionsforaccessibility.recyclerview.StayFocusActivity;
import com.nvisions.solutionsforaccessibility.refreshContents.RefreshContentsActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button liveRegion = (Button) findViewById(R.id.live_region);
        liveRegion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LiveRegionActivity.class);
                startActivity(intent);
            }
        });

        Button labelFor = (Button) findViewById(R.id.label_for);
        labelFor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LabelForActivity.class);
                startActivity(intent);
            }
        });

        Button contentGrouping = (Button) findViewById(R.id.contentGrouping);
        contentGrouping.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ContentGroupingActivity.class);
                startActivity(intent);
            }
        });

        Button fix_focus = (Button)findViewById(R.id.fix_focus);
        fix_focus.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Intent intent = new Intent(getApplicationContext(),    StayFocusActivity.class);
             startActivity(intent);
         }
        });

        Button progressBar = (Button)findViewById(R.id.progressBar);
        progressBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProgressBarActivity.class);
                startActivity(intent);
            }
        });

        Button timeLimit = (Button)findViewById(R.id.timeLimit);
        timeLimit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TimeLimitActivity.class);
                startActivity(intent);
            }
        });

        Button musicPlayer = (Button)findViewById(R.id.musicPlayer);
        musicPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MusicActivity.class);
                startActivity(intent);
            }
        });

        Button overlay = (Button)findViewById(R.id.overlay);
        overlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FocusActivity.class);
                startActivity(intent);
            }
        });

        Button ratingBar = (Button)findViewById(R.id.ratingBar);
        ratingBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RatingBarActivity.class);
                startActivity(intent);
            }
        });

        Button nestedScroll = (Button)findViewById(R.id.nestedScroll);
        nestedScroll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NestedScrollActivity.class);
                startActivity(intent);
            }
        });

                Button collapseExpand = (Button)findViewById(R.id.collapseExpand);
        collapseExpand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CollapseExpandActivity.class);
                startActivity(intent);
            }
        });

        Button webView = (Button)findViewById(R.id.webView);
        webView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
                startActivity(intent);
            }
        });

        Button korailTalk = (Button)findViewById(R.id.korailTalk);
        korailTalk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KorailTalkActivity.class);
                startActivity(intent);
            }
        });

        Button voiceSearch = (Button) findViewById(R.id.voiceSearch);
        voiceSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VoiceSearchActivity.class);
                startActivity(intent);
            }
        });

        Button refreshContents = (Button) findViewById(R.id.refreshContents);
        refreshContents.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RefreshContentsActivity.class);
                startActivity(intent);
            }
        });

        Button customControlButton = (Button)findViewById(R.id.customControlButton);
        customControlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CustomControlActivity.class);
                startActivity(intent);
            }
        });

        Button newLayerButton = (Button)findViewById(R.id.newLayerButton);
        newLayerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LayerFocusActivity.class);
                startActivity(intent);
            }
        });

        Button dragAndDropButton = (Button)findViewById(R.id.dragAndDropButton);
        dragAndDropButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DragAndDropActivity.class);
                startActivity(intent);
            }
        });

        Button keyBoardButton = (Button)findViewById(R.id.keyBoardButton);
        keyBoardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), KeyBoardActivity.class);
                startActivity(intent);
            }
        });

        Button gmarketButton = findViewById(R.id.gmarketButton);
        gmarketButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GmarketSampleActivity.class);
                startActivity(intent);
            }
        });

        Button scrollButton = findViewById(R.id.scrollButton);
        scrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ScrollFocusActivity.class);
                startActivity(intent);
            }
        });

        Button noScrollingRollingBanner = findViewById(R.id.noScrollingRollingBanner);
        noScrollingRollingBanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NoScrollingRollingBannerActivity.class);
                startActivity(intent);
            }
        });

        Button expandableListActivity = findViewById(R.id.expandableListActivity_bt);
        expandableListActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ExpandableListSampleActivity.class);
                startActivity(intent);
            }
        });

        Button btnFullscreenFragment = findViewById(R.id.GoExample_fullscreen_fragment_layer);
        btnFullscreenFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FullscreenFragmentLayerActivity.class);
                startActivity(intent);
            }
        });

        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), TestActivity.class);
                startActivity(intent);
            }
        });

        Button horizontalScrollButton = findViewById(R.id.horizontalScrollButton);
        horizontalScrollButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), HorizontalScrollActivity.class);
                startActivity(intent);
            }
        });

        Button videoPlayer = findViewById(R.id.videoPlayer);
        videoPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), VideoActivity.class);
                startActivity(intent);
            }
        });


        Button customViewGroup = findViewById(R.id.customViewGroup);
        customViewGroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CustomViewGroupActivity.class);
                startActivity(intent);
            }
        });

    }
}

