package com.nvisions.solutionsforaccessibility.CustomControl

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.RadioButton
import com.nvisions.solutionsforaccessibility.AccessibilityUtil.AccessibilityKotlin
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.custom_control_radio_view.view.*

 class CustomRadioButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            :LinearLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.custom_control_radio_view, this)
        button1.isSelected = true
        button2.isSelected = false
        val radio1 = AccessibilityKotlin
                button1.setOnClickListener {
            button1.isSelected = true
            button2.isSelected = false
                    }

        button2.setOnClickListener {
            button2.isSelected = true
            button1.isSelected = false
        }
        radio1.setAsRadioButton(button1, false)
        radio1.setAsRadioButton(button2, false)
    }

    fun getStateSelected(): Int{
        if(button1.isSelected){
            return 1
        }
        else{
            return 2
        }
    }

    fun setStateSelected(num: Int){
        if(num == 1){
            button1.isSelected = true
            button2.isSelected = false
        }
        else{
            button1.isSelected = false
            button2.isSelected = true
        }
    }


}