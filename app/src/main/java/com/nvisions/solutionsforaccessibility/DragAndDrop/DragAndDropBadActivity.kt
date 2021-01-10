package com.nvisions.solutionsforaccessibility.DragAndDrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.MenuItem
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.activity_drag_and_drop_bad.*
import kotlinx.android.synthetic.main.activity_drag_and_drop_bad.addButton
import kotlinx.android.synthetic.main.activity_drag_and_drop_bad.rView
import kotlinx.android.synthetic.main.activity_drag_and_drop_bad.toolbar
import kotlinx.android.synthetic.main.activity_drag_and_drop_good.*

class DragAndDropBadActivity : AppCompatActivity() {
    lateinit var rViewAdapter: DragListNotAccessibleAdapter
    lateinit var touchHelper: ItemTouchHelper
    var itemArr = arrayListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_and_drop_bad)
        init()
    }

    fun init(){
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        rView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
                this, RecyclerView.VERTICAL, false
        )
        rViewAdapter = DragListNotAccessibleAdapter(this, itemArr)
        val callback = DragItemTouchHelperNotAccessibleCallback(rViewAdapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(rView)
        rView.adapter = rViewAdapter

        rViewAdapter.itemDeleteListener = object : DragListNotAccessibleAdapter.OnItemDeleteListener{
            override fun onItemDelete(holder: DragListNotAccessibleAdapter.ViewHolder, position: Int) {
                itemArr.removeAt(position)
                rViewAdapter.notifyItemRemoved(position)
                rViewAdapter.notifyItemRangeChanged(position, itemArr.size)
            }
        }

        rViewAdapter.itemMoveListener = object : DragListNotAccessibleAdapter.OnItemMoveListener{
            override fun onItemMoveUp(position: Int) {
                if(position - 1 >= 0){
                    rViewAdapter.moveItem(position, position - 1)
                                    }
                else{
                    (rView.get(position) as ViewGroup).getChildAt(2).announceForAccessibility("위로 이동할 수 없음")
                }
            }

            override fun onItemMoveDown(position: Int) {
                if(position + 1 < rViewAdapter.itemCount){
                    rViewAdapter.moveItem(position, position + 1)
                }
                else{
                    (rView.get(position) as ViewGroup).getChildAt(2).announceForAccessibility("아래로 이동할 수 없음")
                }
            }
        }

        addButton.setOnClickListener {
            var max = 0
            for(i in itemArr){
                if(i > max){
                    max = i
                }
            }
            itemArr.add(max + 1)
            rViewAdapter.notifyItemInserted(itemArr.size - 1)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }


}