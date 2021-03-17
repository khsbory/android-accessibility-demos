package com.nvisions.solutionsforaccessibility.KeyBoard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.activity_key_board_good_and_bad.*

class KeyBoardGoodAndBadActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_key_board_good_and_bad)
        init()
    }

    private fun init(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle("키보드 접근성")
        buttonGood.isSelected = true
        ViewCompat.setAccessibilityDelegate(buttonGood, object :AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfoCompat?) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info?.roleDescription = "tab"
            }
        })
        buttonGood.setOnClickListener {
            buttonGood.isSelected = true
            buttonBad.isSelected = false
            viewPager.currentItem = 0
        }
        ViewCompat.setAccessibilityDelegate(buttonBad, object :AccessibilityDelegateCompat() {
            override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfoCompat?) {
                super.onInitializeAccessibilityNodeInfo(host, info)
                info?.roleDescription = "tab"
            }
        })
        buttonBad.setOnClickListener {
            buttonBad.isSelected = true
            buttonGood.isSelected = false
            viewPager.currentItem = 1
        }
        viewPager.adapter = PagerAdapter(this)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}

class PagerAdapter(fa: FragmentActivity): FragmentStateAdapter(fa) {

    override fun createFragment(position: Int): Fragment {
        when(position){
            0 -> {
                return KeyBoardBadFragment()
            }
            1 -> {
                return KeyBoardGoodFragment()
            }
        }
        return KeyBoardBadFragment()
    }

    override fun getItemCount(): Int {
        return 2
    }
}