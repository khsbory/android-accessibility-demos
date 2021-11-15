package com.nvisions.solutionsforaccessibility.NoScrollingRollingBanner;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;

import com.nvisions.solutionsforaccessibility.R;

public class WithAccessibilityActivity extends AppCompatActivity {
    boolean playing = true;
     boolean flag = true;
    private Handler handler = new Handler();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            if (flag) { //flag가 true일때 즉 접근성 포커스가 가지 않았을때만 이미지가 변경됩니다.
                ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper);
                flipper.showNext();
            }
            handler.postDelayed(this, 4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_scrolling_rolling_banner_accessibility);
        setTitle(getString(R.string.goodExample));
            final ViewFlipper flipper = (ViewFlipper) findViewById(R.id.flipper);
            flipper.setAccessibilityDelegate(new View.AccessibilityDelegate() {
                @Override
                public boolean onRequestSendAccessibilityEvent(ViewGroup host, View child, AccessibilityEvent event) {
                    if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                    return super.onRequestSendAccessibilityEvent(host, child, event);
                }
            });
        ImageView image1 = (ImageView) findViewById(R.id.image1);
        ImageView image2 = (ImageView) findViewById(R.id.image2);
        ImageView image3 = (ImageView) findViewById(R.id.image3);
        TextView text1 = (TextView) findViewById(R.id.text1);
        Button btnNextCarousel = (Button) findViewById(R.id.carousel_next);
        Button btnPrevCarousel = (Button) findViewById(R.id.carousel_prev);

        Button.OnClickListener CarouselRemoteButtonClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int viewId = v.getId();
                View currentView = flipper.getCurrentView();
                CharSequence currentText = currentView.getContentDescription();

                switch (viewId) {
                    case R.id.carousel_next:
                        flipper.showNext();
                        currentView.announceForAccessibility(currentText);
                        break;
                    case R.id.carousel_prev:
                        flipper.showPrevious();
                        currentView.announceForAccessibility(currentText);
                        break;
                }
            }
        } ;

        btnNextCarousel.setOnClickListener(CarouselRemoteButtonClickListener);
        btnPrevCarousel.setOnClickListener(CarouselRemoteButtonClickListener);

        handler.post(runnable);
    }


    @Override
    protected void onPause() {
        super.onPause();
        flag = false;
    }
}

