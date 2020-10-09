package com.nvisions.solutionsforaccessibility.CustomControl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.custom_control_bad_activity.*

class CustomControlBadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_control_bad_activity)
        init()
    }

    fun init(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(getString(R.string.ratingBar_bad))
        initListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun initListener(){
        buttonDown.setOnClickListener {
            val num = Integer.parseInt(editText.text.toString()) - 1
            editText.setText(num.toString())
            if(num == 1){
                buttonDown.isEnabled = false
            }
        }
        buttonUp.setOnClickListener {
            val num = Integer.parseInt(editText.text.toString()) + 1
            editText.setText(num.toString())
            if(num == 2){
                buttonDown.isEnabled = true
            }
        }

        swipeButton.setOnStateChangeListener{
            val count = editText.text.toString()
            var type = ""
            when(radioGroup.checkedRadioButtonId){
                R.id.radioButton1->{
                    type = getString(R.string.customControl_radio_single)
                }
                R.id.radioButton2->{
                    type = getString(R.string.customControl_radio_set)
                }
            }
            val str = "햄버거 " + count + "개 " + type + " 주문 완료"
            Toast.makeText(applicationContext, str, Toast.LENGTH_LONG).show()
        }

        swipeButton.setOnClickListener {
            Toast.makeText(applicationContext, "clicked", Toast.LENGTH_LONG).show()

        }

//        swipeButton.accessibilityDelegate = object :View.AccessibilityDelegate(){
//            override fun addExtraDataToAccessibilityNodeInfo(host: View, info: AccessibilityNodeInfo, extraDataKey: String, arguments: Bundle?) {
//                super.addExtraDataToAccessibilityNodeInfo(host, info, extraDataKey, arguments)
//                info?.className = Button::class.java.name
//            }
//        }

    }

}