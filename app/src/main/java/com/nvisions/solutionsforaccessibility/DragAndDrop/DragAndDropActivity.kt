package com.nvisions.solutionsforaccessibility.DragAndDrop

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.nvisions.solutionsforaccessibility.R
import com.nvisions.solutionsforaccessibility.RatingBar.RatingBarBadActivity
import com.nvisions.solutionsforaccessibility.RatingBar.RatingBarGoodActivity
import kotlinx.android.synthetic.main.rating_bar_activity.*

class DragAndDropActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_drop)
        init()
    }

    fun init(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(getString(R.string.dragAndDropName))

        //접근성 적용되지 않은 예시
        button1.setOnClickListener {
            val intent = Intent(applicationContext, DragAndDropBadActivity::class.java)
            startActivity(intent)
        }

        //접근성 적용된 예시
        button2.setOnClickListener {
            val intent = Intent(applicationContext, DragAndDropGoodActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}