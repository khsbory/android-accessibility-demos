package com.nvisions.solutionsforaccessibility.Video;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerControlView;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.upstream.RawResourceDataSource;
import com.google.android.exoplayer2.util.Util;
import com.nvisions.solutionsforaccessibility.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

import static android.view.accessibility.AccessibilityEvent.CONTENT_CHANGE_TYPE_CONTENT_DESCRIPTION;
import static android.view.accessibility.AccessibilityEvent.CONTENT_CHANGE_TYPE_TEXT;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED;
import static android.view.accessibility.AccessibilityEvent.TYPE_VIEW_SELECTED;

public class VideoGoodActivity extends AppCompatActivity {

    SimpleExoPlayer player;
    PlayerView playerView;
    ImageView fullscreenButton, playButton, stopButton;
    ImageButton previousTrack, nextTrack, forward, backward;
        Button closeBtn;
    TextView timePosition, totaltime;
    boolean fullscreen = false;
    private AccessibilityManager A11yManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_good);
        setTitle(getString(R.string.accessibilityExample));
        player = ExoPlayerFactory.newSimpleInstance(getApplicationContext());
        totaltime = findViewById(R.id.exo_duration);
        nextTrack = findViewById(R.id.exo_next);
        nextTrack.setContentDescription(getString(R.string.nextTrack));
        ViewCompat.setAccessibilityDelegate(nextTrack, new AccessibilityDelegateCompat() {
            @Override
            public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onInitializeAccessibilityEvent(host, event);
                if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            timePosition.announceForAccessibility(timePosition.getText());
                        }
                    }, 500);
                };
                            };
        });

        timePosition = findViewById(R.id.exo_position);
        timePosition.setImportantForAccessibility(View.IMPORTANT_FOR_ACCESSIBILITY_NO);
        ViewCompat.setAccessibilityDelegate(timePosition, new AccessibilityDelegateCompat() {
            @Override
            public void sendAccessibilityEvent(View host, int eventType) {
                if (eventType == TYPE_VIEW_ACCESSIBILITY_FOCUSED) {
                  return;
                };
                super.sendAccessibilityEvent(host, eventType);
            }
        });

        playerView = findViewById(R.id.player);
        ViewCompat.replaceAccessibilityAction(playerView, AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK, "", null);
        ViewCompat.setAccessibilityDelegate(playerView, new AccessibilityDelegateCompat() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
            }
        });

        previousTrack = findViewById(R.id.exo_prev);
        previousTrack.setContentDescription(getString(R.string.previousTrack));
        ViewCompat.setAccessibilityDelegate(previousTrack, new AccessibilityDelegateCompat() {
            @Override
            public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onInitializeAccessibilityEvent(host, event);
                if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            timePosition.announceForAccessibility(timePosition.getText());
                        }
                    }, 500);
                };
            };
        });

        forward = findViewById(R.id.exo_ffwd);
        ViewCompat.setAccessibilityDelegate(forward, new AccessibilityDelegateCompat() {
            @Override
            public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onInitializeAccessibilityEvent(host, event);
                if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            timePosition.announceForAccessibility(timePosition.getText());
                        }
                    }, 500);
                };
            };
        });

        backward = findViewById(R.id.exo_rew);
        ViewCompat.setAccessibilityDelegate(backward, new AccessibilityDelegateCompat() {
            @Override
            public void onInitializeAccessibilityEvent(View host, AccessibilityEvent event) {
                super.onInitializeAccessibilityEvent(host, event);
                if (event.getEventType() == AccessibilityEvent.TYPE_VIEW_CLICKED) {
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            timePosition.announceForAccessibility(timePosition.getText());
                        }
                    }, 500);
                };
            };
        });

        closeBtn = playerView.findViewById(R.id.btn_close);
        closeBtn.setAccessibilityTraversalBefore(R.id.player);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fullscreenButton = playerView.findViewById(R.id.exo_fullscreen_icon);
        fullscreenButton.setContentDescription(getString(R.string.startFullScreen));
        fullscreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fullscreen) {
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(VideoGoodActivity.this, R.drawable.ic_fullscreen_open));
                    fullscreenButton.setContentDescription(getString(R.string.startFullScreen));
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_VISIBLE);

                    if(getSupportActionBar() != null){
                        getSupportActionBar().show();
                    }

                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = (int) ( 200 * getApplicationContext().getResources().getDisplayMetrics().density);
                    playerView.setLayoutParams(params);

                    fullscreen = false;
                }else{
                    fullscreenButton.setImageDrawable(ContextCompat.getDrawable(VideoGoodActivity.this, R.drawable.ic_fullscreen_close));
                    fullscreenButton.setContentDescription(getString(R.string.endFullScreen));
                    getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_FULLSCREEN
                            |View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                            |View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);

                    if(getSupportActionBar() != null){
                        getSupportActionBar().hide();
                    }
                    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
                    RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) playerView.getLayoutParams();
                    params.width = params.MATCH_PARENT;
                    params.height = params.MATCH_PARENT;
                    playerView.setLayoutParams(params);
                    fullscreen = true;
                }
            }
        });

        playButton = playerView.findViewById(R.id.exo_play);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setPlayWhenReady(true);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        stopButton.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
                        Log.d("test", "play focus");
                    }
                },1000);
            }
        });

        stopButton = playerView.findViewById(R.id.exo_pause);
        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                player.setPlayWhenReady(false);
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        playButton.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
                        Log.d("test", "stop focus");
                    }
                },1000);
            }
        });

        playerView.setPlayer(player);
        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FIXED_HEIGHT);

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(getApplicationContext(), Util.getUserAgent(getApplicationContext(),getApplicationContext().getString(R.string.app_name)));

//        MediaSource videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory)
//                .createMediaSource(Uri.parse("http://html5videoformatconverter.com/data/images/happyfit2.mp4"));
        MediaSource videoSource  = new ExtractorMediaSource.Factory(dataSourceFactory).createMediaSource(RawResourceDataSource.buildRawResourceUri(R.raw.samplevideo));

        player.prepare(videoSource);


        A11yManager = (AccessibilityManager) this.getSystemService(ACCESSIBILITY_SERVICE);
        A11yManager.addTouchExplorationStateChangeListener(new AccessibilityManager.TouchExplorationStateChangeListener(){
            @Override
            public void onTouchExplorationStateChanged(boolean enabled){
                if(enabled){
                    playerView.setControllerShowTimeoutMs(0);
                    playerView.showController();
                } else {
                    playerView.setControllerShowTimeoutMs(1000*3);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if(A11yManager.isTouchExplorationEnabled()){
            playerView.setControllerShowTimeoutMs(0);
        } else {
            playerView.setControllerShowTimeoutMs(1000*3);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        player.setPlayWhenReady(false);
    }

    @Override
    public void onDestroy() {
        player.release();
        super.onDestroy();
    }

}
