package com.nvisions.solutionsforaccessibility.KeyBoard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
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
        setTitle(getString(R.string.newLayer_bad))

        buttonBad.setOnClickListener { viewPager.currentItem = 0 }
        buttonGood.setOnClickListener { viewPager.currentItem = 1 }
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