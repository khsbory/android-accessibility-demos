package com.nvisions.solutionsforaccessibility.CustomControl

import android.app.Dialog
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.view.accessibility.AccessibilityManager
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.custom_control_good_activity.*

class CustomControlGoodActivity : AppCompatActivity() {
    private var count:String = "1"
    private var type:String = "단품"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_control_good_activity)
        init()
    }

    fun init(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(getString(R.string.customControl_good))
        initAccessibility()
        initListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initAccessibility(){
        editText.accessibilityLiveRegion = View.ACCESSIBILITY_LIVE_REGION_NONE

        editText.accessibilityDelegate = object : View.AccessibilityDelegate() {
            override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info?.isEnabled = false
            }
        }
        swipeButton.accessibilityDelegate = object : View.AccessibilityDelegate(){
            override fun addExtraDataToAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo, extraDataKey: String, arguments: Bundle?) {
                super.addExtraDataToAccessibilityNodeInfo(host, info, extraDataKey, arguments)
                info?.className = Button::class.java.name
            }
        }

        val accessibilityManager = getSystemService(ACCESSIBILITY_SERVICE) as AccessibilityManager
        swipeButton.setOnClickListener {
            if (accessibilityManager.isEnabled) {
                Toast.makeText(applicationContext, "clicked", Toast.LENGTH_LONG).show()
                completeOrder()
            }
        }

    }

    private fun initListener() {
        buttonDown.setOnClickListener {
            val num = Integer.parseInt(editText.text.toString()) - 1
            editText.setText(num.toString())
            if (num == 1) {
                buttonDown.isEnabled = false
            }
            editText.announceForAccessibility( "수량 " + num.toString())
        }
        buttonUp.setOnClickListener {
            val num = Integer.parseInt(editText.text.toString()) + 1
            editText.setText(num.toString())
            if (num == 2) {
                buttonDown.isEnabled = true
            }
            editText.announceForAccessibility( "수량 " + num.toString())
        }

        swipeButton.setOnStateChangeListener {
            completeOrder()
        }
    }

    private fun completeOrder(){
        count = editText.text.toString()
        type = ""
        when(radioButton.getStateSelected()){
            1->{//단품
                type = getString(R.string.customControl_radio_single)
            }
            2->{//세트
                type = getString(R.string.customControl_radio_set)
            }
        }

        val str = "햄버거 " + count + "개 " + type + " 주문 완료"
        Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show()
        val builder = AlertDialog.Builder(this)
        builder.setMessage(str)
        builder.setPositiveButton(R.string.confirm) {_, _->
            count = "1"
            type = getString(R.string.customControl_radio_single)
            editText.setText("1")
            radioButton.setStateSelected(1)
            buttonDown.isEnabled = false
        }
        builder.create().show()
    }

}