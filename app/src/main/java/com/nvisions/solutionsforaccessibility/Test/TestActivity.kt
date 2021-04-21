package com.nvisions.solutionsforaccessibility.Test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.activity_test.*

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        textView13.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityEvent(host: View?, event: AccessibilityEvent?) {
                super.onInitializeAccessibilityEvent(host, event)
                when(event?.eventType) {
                    AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED -> Toast.makeText(applicationContext, "type view accessibility focused", Toast.LENGTH_LONG).show()
                    AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUS_CLEARED  -> Toast.makeText(applicationContext, "TYPE VIEW ACCESSIBILITY FOCUS CLEARED ", Toast.LENGTH_LONG).show()
                    AccessibilityEvent.TYPE_VIEW_HOVER_ENTER -> Toast.makeText(applicationContext, "TYPE VIEW HOVER ENTER", Toast.LENGTH_LONG).show()
                    AccessibilityEvent.TYPE_VIEW_HOVER_EXIT  -> Toast.makeText(applicationContext, "TYPE VIEW HOVER EXIT ", Toast.LENGTH_LONG).show()
                    AccessibilityEvent.TYPE_VIEW_FOCUSED -> Toast.makeText(applicationContext, "TYPE VIEW FOCUSED", Toast.LENGTH_LONG).show()
                    AccessibilityEvent.TYPE_VIEW_CLICKED -> Toast.makeText(applicationContext, "TYPE VIEW CLICKED", Toast.LENGTH_LONG).show()
                    AccessibilityEvent.TYPE_VIEW_TEXT_CHANGED -> Toast.makeText(applicationContext, "TYPE VIEW TEXT CHANGED", Toast.LENGTH_LONG).show()
                    AccessibilityEvent.TYPE_VIEW_TEXT_SELECTION_CHANGED -> Toast.makeText(applicationContext, "TYPE VIEW TEXT SELECTION CHANGED", Toast.LENGTH_LONG).show()
                }
            }

        }
        //포커스가될때 버튼으로 읽게 -> event.classname = button
        textView11.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityEvent(host: View?, event: AccessibilityEvent?) {
                super.onInitializeAccessibilityEvent(host, event)
                if(event?.eventType == AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED ) {
                    Log.d("mytag", event?.className.toString())
                    event?.className = Button::class.java.name
                }

            }

        }
    }
}