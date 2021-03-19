package com.nvisions.solutionsforaccessibility.ScrollFocus

import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityEvent
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat.AccessibilityActionCompat.ACTION_CLICK
import androidx.core.view.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.SCROLL_STATE_IDLE
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.activity_scroll_focus_good.*

class ScrollFocusGoodActivity : AppCompatActivity() {
    val frontList = arrayListOf(30, 60, 90)
    var backList = arrayListOf<Int>()
    private lateinit var frontAdapter: FrontScrollGoodListAdapter
    private lateinit var backAdapter: BackScrollGoodListAdapter

    inner class TopLinearSmoothScroller: LinearSmoothScroller(applicationContext) {
        @Override
        override fun getVerticalSnapPreference(): Int {
            return SNAP_TO_START
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_focus_good)
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
        setTitle("스크롤 포커스, 접근성 적용된 예시")

        val manager: LinearLayoutManager = object : LinearLayoutManager(this, RecyclerView.VERTICAL, false) {
            private var flg = false
            private var targetPosition = 0


            override fun smoothScrollToPosition(view: RecyclerView, state: RecyclerView.State, position: Int) {
                flg = true
                val scroller = TopLinearSmoothScroller()
                scroller.targetPosition = position
                targetPosition = position
                startSmoothScroll(scroller)
            }

            override fun onScrollStateChanged(state: Int) {
                super.onScrollStateChanged(state)
                if (state == SCROLL_STATE_IDLE && flg){
                    flg = false
                    val targetView = findViewByPosition(targetPosition + 1)
                    targetView?.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED)
                    targetView?.requestFocus()
                }
            }
        }

        for (i in 1..100) {
            backList.add(i)
        }
        frontAdapter = FrontScrollGoodListAdapter(frontList)
        backAdapter = BackScrollGoodListAdapter(backList)
        frontRView.layoutManager = LinearLayoutManager(
                applicationContext, RecyclerView.HORIZONTAL, false
        )
        backRView.layoutManager = manager
        frontRView.adapter = frontAdapter
        backRView.adapter = backAdapter

        frontAdapter.itemClickListener = object : FrontScrollGoodListAdapter.OnItemClickListener{
            override fun onItemClick(holder: FrontScrollGoodListAdapter.ViewHolder, view: View, data: Int) {
                backRView.smoothScrollToPosition(data - 2)
                for(i in 0 until frontAdapter.itemCount){
                    frontRView.get(i).findViewById<Button>(R.id.textView).isSelected = false
                }
                view.isSelected = true
            }
        }
    }
}

private class FrontScrollGoodListAdapter(val data: ArrayList<Int>) : RecyclerView.Adapter<FrontScrollGoodListAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(holder: FrontScrollGoodListAdapter.ViewHolder, view: View, data: Int)
    }

    var itemClickListener :OnItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: Button

        init {
            textView = itemView.findViewById(R.id.textView)
            textView.setOnClickListener {
                itemClickListener?.onItemClick(this, it, data[adapterPosition])
            }
            ViewCompat.replaceAccessibilityAction(textView, ACTION_CLICK, "스크롤", null)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.scroll_focus_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = data[position]!!
            holder.textView.text = item.toString()
        }
    }
}


private class BackScrollGoodListAdapter(val data: ArrayList<Int>) : RecyclerView.Adapter<BackScrollGoodListAdapter.ViewHolder>() {
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: TextView

        init {
            textView = itemView.findViewById(R.id.textView)
        }
    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.dialog_list_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            val item = data[position]!!
            holder.textView.text = item.toString()
        }
    }

}


