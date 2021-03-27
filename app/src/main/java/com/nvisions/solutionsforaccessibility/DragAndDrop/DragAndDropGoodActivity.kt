package com.nvisions.solutionsforaccessibility.DragAndDrop

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.MenuItem
import android.view.ViewGroup
import android.view.Window
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.activity_drag_and_drop_good.*

class DragAndDropGoodActivity : AppCompatActivity() {
    val tabLayoutTextArray = arrayOf("예시 1", "예시 2")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_drop_good)

        setTitle(getString(R.string.drag_good))
        init()
    }

    fun init(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        titleButton.isEnabled = false
        viewPager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager){ tab,position->
            tab.text = tabLayoutTextArray[position]
        }.attach()

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

class ViewPagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa){
    override fun createFragment(position: Int): Fragment {
        return when(position){
            0 -> DragAndDropFragment1()
            1 -> DragAndDropFragment2()
            else -> DragAndDropFragment1()
        }
    }
    override fun getItemCount():Int = 2
}

