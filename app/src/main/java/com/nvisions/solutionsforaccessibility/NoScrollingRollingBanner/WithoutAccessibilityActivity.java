package com.nvisions.solutionsforaccessibility.NoScrollingRollingBanner;

import android.os.Bundle;
import android.os.Handler;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.nvisions.solutionsforaccessibility.R;

public class WithoutAccessibilityActivity extends AppCompatActivity {
    boolean playing = true;
    boolean flag = true;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (flag) {
                ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper);
                flipper.showNext();

            }
            handler.postDelayed(this, 4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_scrolling_rolling_banner_without);
        setTitle(getString(R.string.badExample));
        handler.post(runnable);

        final ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper);
        ImageView image1 = (ImageView)findViewById(R.id.image1);
        ImageView image2 = (ImageView)findViewById(R.id.image2);
        ImageView image3 = (ImageView)findViewById(R.id.image3);

        Button btnNextCarousel = (Button) findViewById(R.id.carousel_next);
        Button btnPrevCarousel = (Button) findViewById(R.id.carousel_prev);

        Button.OnClickListener CarouselRemoteButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();
                switch (viewId) {
                    case R.id.carousel_next:
                        flipper.showNext();
                        break;
                    case R.id.carousel_prev:
                        flipper.showPrevious();
                        break;
                }
            }
        } ;

        btnNextCarousel.setOnClickListener(CarouselRemoteButtonClickListener);
        btnPrevCarousel.setOnClickListener(CarouselRemoteButtonClickListener);

        final TextView text1 = (TextView) findViewById(R.id.text1);
}
}

