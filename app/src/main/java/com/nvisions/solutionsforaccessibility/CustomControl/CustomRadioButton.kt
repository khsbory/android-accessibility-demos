package com.nvisions.solutionsforaccessibility.CustomControl

import android.content.Context
import android.util.AttributeSet
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.LinearLayout
import android.widget.RadioButton
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.custom_control_radio_view.view.*

open class CustomRadioButton @JvmOverloads constructor(context:Context, attrs:AttributeSet?=null, defStyleAttr:Int = 0)
    :LinearLayout(context, attrs, defStyleAttr) {
    init {
        inflate(context, R.layout.custom_control_radio_view, this)
        button1.isSelected = true
        button2.isSelected = false
        button1.setOnClickListener {
            button1.isSelected = !button1.isSelected
            button2.isSelected = !button1.isSelected
        }
        button2.setOnClickListener {
            button2.isSelected = !button2.isSelected
            button1.isSelected = !button2.isSelected
        }
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

    override fun onInitializeAccessibilityNodeInfo(info: AccessibilityNodeInfo?) {
        super.onInitializeAccessibilityNodeInfo(info)
        info?.className = RadioButton::class.java.name
    }
}