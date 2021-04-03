package com.nvisions.solutionsforaccessibility.FullscreenFragmentLayer;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.nvisions.solutionsforaccessibility.R;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class FullscreenFragmentLayerExampleActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnOpenGoodFragment;
    private Button btnOpenBadFragment;
    private FrameLayout LayerFrame;
    private FragmentManager fm;
    private FragmentTransaction tr;
    private FullscreenLayerGoodFragment fragGood;
    private FullscreenLayerBadFragment fragBad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fm = getSupportFragmentManager();
        tr = fm.beginTransaction();
        fragGood = new FullscreenLayerGoodFragment();
        fragBad = new FullscreenLayerBadFragment();
        setContentView(R.layout.activity_fullscreen_fragment_layer_example);
        setTitle(getString(R.string.accessibilityExample));
                btnOpenGoodFragment = (Button) findViewById(R.id.btn_OpenFullscreenLayerFragmentGood);
        btnOpenBadFragment = (Button) findViewById(R.id.btn_OpenFullscreenLayerFragmentBad);
        btnOpenGoodFragment.setOnClickListener(this);
        btnOpenBadFragment.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();
        if(viewId == R.id.btn_OpenFullscreenLayerFragmentGood){
            tr = fm.beginTransaction();
            tr.add(R.id.FragmentLayerExample_Layer,fragGood).addToBackStack(null)
                    .commitAllowingStateLoss();
        }
        if(viewId == R.id.btn_OpenFullscreenLayerFragmentBad){
            tr = fm.beginTransaction();
            tr.add(R.id.FragmentLayerExample_Layer,fragBad).addToBackStack(null).commitAllowingStateLoss();
        }
    }
}