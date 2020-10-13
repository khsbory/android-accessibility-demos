package com.nvisions.solutionsforaccessibility.CustomControl

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.custom_control_bad_activity.*
import kotlinx.android.synthetic.main.custom_control_bad_activity.buttonDown
import kotlinx.android.synthetic.main.custom_control_bad_activity.buttonUp
import kotlinx.android.synthetic.main.custom_control_bad_activity.editText
import kotlinx.android.synthetic.main.custom_control_bad_activity.radioButton
import kotlinx.android.synthetic.main.custom_control_bad_activity.swipeButton
import kotlinx.android.synthetic.main.custom_control_good_activity.*

class CustomControlBadActivity : AppCompatActivity() {
    private var count:String = "1"
    private var type:String = "단품"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.custom_control_bad_activity)
        init()
    }

    fun init(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(getString(R.string.customControl_bad))
        initListener()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun initListener(){
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

}