package com.nvisions.solutionsforaccessibility.CustomControl

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.RadioButton
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.custom_control_radio_view.view.*

 class CustomRadioButton @JvmOverloads constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            :LinearLayout(context, attrs, defStyleAttr) {

    init {
        inflate(context, R.layout.custom_control_radio_view, this)
        button1.isSelected = true
        button2.isSelected = false
        button1.setOnClickListener {
            button1.isSelected = true
            button2.isSelected = false
                    }

        button2.setOnClickListener {
            button2.isSelected = true
            button1.isSelected = false
        }
        //이미지뷰의 접근성 정보 수정을 위해 AccessibilityDelegate 객체 만들기
        button1.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info?.className = RadioButton::class.java.name
                info?.isCheckable = true // 체크, 체크 해제에 대한 음성 안내
                info?.isChecked = button1.isSelected // 조건문에 따라 checked true false 적용
                info?.isSelected = false // 선택된 요소에 selected 적용한 경우 체크됨과 중복되므로 해제

            }
        }

        button2.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info?.className = RadioButton::class.java.name
                info?.isCheckable = true
                info?.isChecked = button2.isSelected
                info?.isSelected = false
            }
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


}