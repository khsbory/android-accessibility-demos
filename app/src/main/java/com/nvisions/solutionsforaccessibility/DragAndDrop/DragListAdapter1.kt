package com.nvisions.solutionsforaccessibility.DragAndDrop

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.R
import java.util.*
import kotlin.collections.ArrayList

class DragListAdapter1 (val context: Context, val items: ArrayList<Int>) : RecyclerView.Adapter<DragListAdapter1.ViewHolder>() {
    interface OnItemDeleteListener{
        fun onItemDelete(holder:ViewHolder, position: Int)
    }

    interface OnItemMoveListener {
        fun onItemMoveUp(position:Int)
        fun onItemMoveDown(position:Int)
    }

    var itemDeleteListener :OnItemDeleteListener? = null
    var itemMoveListener: OnItemMoveListener? = null
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contentText: TextView
        var deleteButton: Button
        var dragButton: ImageView

        init {
            contentText = itemView.findViewById(R.id.contentText)
            deleteButton = itemView.findViewById(R.id.deleteButton)
            dragButton = itemView.findViewById(R.id.dragButton)
            deleteButton.setOnClickListener {
                itemDeleteListener?.onItemDelete(this, adapterPosition)
            }
            contentText.setOnClickListener {
                Toast.makeText(context, items[adapterPosition].toString() + " 클릭함", Toast.LENGTH_LONG).show()
            }
            contentText.accessibilityDelegate = object : View.AccessibilityDelegate() {


                override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
                    super.onInitializeAccessibilityNodeInfo(host, info)
                    info?.className = SeekBar::class.java.name
                    info?.tooltipText = "볼륨키로 순서 이동"
                }

                override fun performAccessibilityAction(host: View?, action: Int, args: Bundle?): Boolean {
                    if (action == AccessibilityNodeInfo.ACTION_SCROLL_BACKWARD) {
                        itemMoveListener?.onItemMoveDown(adapterPosition)
                    }
                    else if (action == AccessibilityNodeInfo.ACTION_SCROLL_FORWARD) {
                        itemMoveListener?.onItemMoveUp(adapterPosition)
                    }
                    return super.performAccessibilityAction(host, action, args)
                }
            }
            contentText.setOnKeyListener { v, keyCode, event ->
                if(keyCode == KeyEvent.KEYCODE_DPAD_DOWN && event.action == KeyEvent.ACTION_DOWN){
                    itemMoveListener?.onItemMoveDown(adapterPosition)
                    true
                }
                else if(keyCode == KeyEvent.KEYCODE_DPAD_UP && event.action == KeyEvent.ACTION_DOWN){
                    itemMoveListener?.onItemMoveUp(adapterPosition)
                    true
                }
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.drag_and_drop_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        if (holder is ViewHolder) {
            holder.contentText.text = items[position].toString()
            holder.deleteButton.contentDescription = holder.deleteButton.text.toString() + " " + items[position].toString()
        }
    }

    fun moveItem(fromPosition:Int, toPosition:Int) {
        if (fromPosition < toPosition) {
            for (i in fromPosition until toPosition) {
                Collections.swap(items, i, i + 1)
            }
        } else {
            for (i in fromPosition downTo toPosition + 1) {
                Collections.swap(items, i, i - 1)
            }
        }
        notifyItemMoved(fromPosition, toPosition)

    }
}

