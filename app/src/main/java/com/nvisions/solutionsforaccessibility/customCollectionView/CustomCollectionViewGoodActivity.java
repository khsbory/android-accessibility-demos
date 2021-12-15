package com.nvisions.solutionsforaccessibility.customCollectionView;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityNodeInfo;

import com.nvisions.solutionsforaccessibility.R;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

public class CustomCollectionViewGoodActivity extends AppCompatActivity {

    Context mContext;

    int count = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_collection_view_good);

        ConstraintLayout view1 = findViewById(R.id.view1);
        ConstraintLayout view2 = findViewById(R.id.view2);

        mContext = getApplicationContext();

        ViewCompat.setAccessibilityDelegate(view1, new AccessibilityDelegateCompat() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                count = info.getChildCount();
                info.getCollectionInfo();
                Log.d("test111",info.getCollectionInfo() + ", childCount : " + count);
            }
        });
        ViewCompat.setAccessibilityDelegate(view2, new AccessibilityDelegateCompat() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                count = info.getChildCount();
                info.getCollectionInfo();
                Log.d("test111","2 : " + info.getCollectionInfo() + ", childCount : " + count);
            }
        });
    }

}
