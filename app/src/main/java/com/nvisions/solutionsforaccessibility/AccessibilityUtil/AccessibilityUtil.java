package com.nvisions.solutionsforaccessibility.AccessibilityUtil;

import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.RadioButton;

public class AccessibilityUtil {
    public static void setAsRadioButton(View view) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(RadioButton.class.getName());
                if (view.isSelected()) {
                    info.setChecked(true);
                } else {
                    info.setChecked(false);
                }
            }
        });
    }
}
