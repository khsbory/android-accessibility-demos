package com.nvisions.solutionsforaccessibility.AccessibilityUtil;

import android.content.Context;
import android.view.View;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.ToggleButton;

import androidx.core.view.AccessibilityDelegateCompat;
import androidx.core.view.ViewCompat;
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat;

public class AccessibilityUtil {

    public static void setAsButton(View view) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(Button.class.getName());
            }
        });
    }

    public static void setAsCheckBox(View view, boolean isChecked) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(CheckBox.class.getName());
                info.setCheckable(true);
                if (view.isSelected()) {
                    info.setChecked(true);
                    info.setSelected(false);
                } else if (isChecked) {
                    info.setChecked(true);
                } else {
                    info.setChecked(false);
                }
            }
        });
    }

    public static void setAsRadioButton(View view, boolean isChecked) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(RadioButton.class.getName());
                info.setCheckable(true);
                if (view.isSelected()) {
                    info.setChecked(true);
                    info.setSelected(false);
                } else if (isChecked) {
                    info.setChecked(true);
                } else {
                    info.setChecked(false);
                }
            }
        });
    }

    public static void setAsTab(View view, boolean isSelected) {
        ViewCompat.setAccessibilityDelegate(view, new AccessibilityDelegateCompat() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfoCompat info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setRoleDescription("tab");
                if (view.isSelected()) {
                    info.setClickable(false);
                    info.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                } else if (isSelected) {
                    info.setSelected(true);
                    info.setClickable(false);
                    info.removeAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK);
                } else {
                    info.setSelected(false);
                }
            }
        });
    }

    public static void setAsToggleButton(View view, boolean isChecked) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClassName(ToggleButton.class.getName());
                info.setCheckable(true);
                if (view.isSelected()) {
                    info.setChecked(true);
                    info.setSelected(false);
                } else if (isChecked) {
                    info.setChecked(true);
                } else {
                    info.setChecked(false);
                }
            }
        });
    }

    public static void removeClickHintMsg(View view) {
        view.setAccessibilityDelegate(new View.AccessibilityDelegate() {
            @Override
            public void onInitializeAccessibilityNodeInfo(View host, AccessibilityNodeInfo info) {
                super.onInitializeAccessibilityNodeInfo(host, info);
                info.setClickable(false);
                info.removeAction(AccessibilityNodeInfo.AccessibilityAction.ACTION_CLICK);
            }
        });
    }

    public static boolean isTalkBackOn(Context context) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) context.getSystemService(Context.ACCESSIBILITY_SERVICE);
        boolean isTalkBackOn = accessibilityManager.isTouchExplorationEnabled();
        return isTalkBackOn;
    }
}

