package com.nvisions.solutionsforaccessibility.FullscreenFragmentLayer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import com.nvisions.solutionsforaccessibility.R;

public class FullscreenFragmentLayerActivity extends AppCompatActivity implements View.OnClickListener{
    private Button GoodExample;
    private FragmentManager FM;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen_fragment_layer);
        setTitle(R.string.activity_fullscreen_fragment_layer_title);
        GoodExample = (Button) findViewById(R.id.button1);

        GoodExample.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        int viewId = v.getId();
        switch (viewId){
            case R.id.button1:
                startActivity(new Intent(getApplicationContext(), FullscreenFragmentLayerExampleActivity.class));
                break;
        }
    }
}