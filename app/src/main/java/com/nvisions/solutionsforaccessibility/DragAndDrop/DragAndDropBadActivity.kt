package com.nvisions.solutionsforaccessibility.DragAndDrop

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.activity_drag_and_drop_bad.*

class DragAndDropBadActivity : AppCompatActivity() {
    lateinit var rViewAdapter: DragListAdapter
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
        rViewAdapter = DragListAdapter(this, itemArr)
        val callback = DragItemTouchHelperCallback(rViewAdapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(rView)
        rView.adapter = rViewAdapter

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