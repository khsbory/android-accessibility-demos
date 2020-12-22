package com.nvisions.solutionsforaccessibility.LayerFocus

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityManager
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.activity_layer_focus_good.*

class LayerFocusGoodActivity : AppCompatActivity() {
    val titleArray = arrayOf("과일", "스포츠", "알파벳", "음식")
    val contentArray = arrayOf(arrayOf("사과", "귤", "자몽", "포도"), arrayOf("축구", "농구", "배구", "수영"), arrayOf("A", "B", "C", "D"), arrayOf("잡채", "호떡", "갈비", "전"))
    lateinit var baseRViewAdapter: LayerFocusBaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_layer_focus_good)
        init()

    }

    private fun isTalkBackEnabled(): Boolean {
        val accessibilityManager = getSystemService(ACCESSIBILITY_SERVICE) as AccessibilityManager
        val isAccessibilityEnabled = accessibilityManager.isEnabled
        val isExploreByTouchEnabled = accessibilityManager.isTouchExplorationEnabled
        return isAccessibilityEnabled && isExploreByTouchEnabled
    }

    private fun init(){
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        setTitle(getString(R.string.newLayer_good))
        baseRView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                this, RecyclerView.VERTICAL, false
        )
        baseRViewAdapter = LayerFocusBaseAdapter(this, titleArray)
        baseRView.adapter = baseRViewAdapter
        baseRViewAdapter.itemClickListener = object : LayerFocusBaseAdapter.OnItemClickListener {
            override fun OnItemClick(holder: LayerFocusBaseAdapter.ViewHolder, view: View, position: Int) {
                newLayout.visibility = View.VISIBLE
                rView.adapter = LayerFocusHorizontalAdapter(this@LayerFocusGoodActivity, contentArray[position])
                layerTitle.text = titleArray[position]
                if (isTalkBackEnabled()){
                    baseRView.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
                    newLayout.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES

                    newLayout.isFocusable = true
                    newLayout.isFocusableInTouchMode = true
                    newLayout.requestFocus()
                    newLayout.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_ACCESSIBILITY_FOCUSED)
                    newLayout.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
                    Log.d("mytag", currentFocus.toString())
                }


            }
        }

        rView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                this, RecyclerView.HORIZONTAL, false
        )

        button.setOnClickListener {
            newLayout.visibility = View.INVISIBLE
            newLayout.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO_HIDE_DESCENDANTS
            baseRView.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_YES
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}