package com.nvisions.solutionsforaccessibility.ScrollFocus

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.activity_scroll_focus.*

class ScrollFocusActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_focus)
        init()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

    fun init(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle("스크롤 포커스")

        buttonGood.setOnClickListener {
            val intent = Intent(applicationContext, ScrollFocusGoodActivity::class.java)
            startActivity(intent)
        }

        buttonBad.setOnClickListener {
            val intent = Intent(applicationContext, ScrollFocusBadActivity::class.java)
            startActivity(intent)
        }
    }

}