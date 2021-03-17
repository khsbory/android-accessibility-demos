package com.nvisions.solutionsforaccessibility.ScrollFocus

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSmoothScroller
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.activity_scroll_focus_bad.*


class ScrollFocusBadActivity : AppCompatActivity() {
    val frontList = arrayListOf(30, 60, 90)
    var backList = arrayListOf<Int>()
    private lateinit var frontAdapter: FrontScrollListAdapter
    private lateinit var backAdapter: BackScrollListAdapter

    inner class TopLinearSmoothScroller: LinearSmoothScroller(applicationContext) {
        @Override
        override fun getVerticalSnapPreference(): Int {
            return SNAP_TO_START
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scroll_focus_bad)
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
        setTitle("스크롤 포커스, 접근성 적용 안된 예시")

        val manager: LinearLayoutManager = object : LinearLayoutManager(this, RecyclerView.VERTICAL, false) {
            override fun smoothScrollToPosition(view: RecyclerView, state: RecyclerView.State, position: Int) {
                val scroller = TopLinearSmoothScroller()
                scroller.targetPosition = position
                startSmoothScroll(scroller)
            }
        }

        for (i in 1..100) {
            backList.add(i)
        }
        frontAdapter = FrontScrollListAdapter(frontList)
        backAdapter = BackScrollListAdapter(backList)
        frontRView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                applicationContext, RecyclerView.HORIZONTAL, false
        )
        backRView.layoutManager = manager
        frontRView.adapter = frontAdapter
        backRView.adapter = backAdapter
        frontAdapter.itemClickListener = object : FrontScrollListAdapter.OnItemClickListener{
            override fun onItemClick(holder: FrontScrollListAdapter.ViewHolder, view: View, data: Int) {
                Log.d("mytag", (data - 1).toString())
                backRView.smoothScrollToPosition(data - 2)
            }
        }
    }
}

private class FrontScrollListAdapter(val data: ArrayList<Int>) : RecyclerView.Adapter<FrontScrollListAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun onItemClick(holder: FrontScrollListAdapter.ViewHolder, view: View, data: Int)
    }

    var itemClickListener :OnItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textView: Button

        init {
            textView = itemView.findViewById(R.id.textView)
            textView.setOnClickListener {
                itemClickListener?.onItemClick(this, it, data[adapterPosition])
            }
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


private class BackScrollListAdapter(val data: ArrayList<Int>) : RecyclerView.Adapter<BackScrollListAdapter.ViewHolder>() {
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


