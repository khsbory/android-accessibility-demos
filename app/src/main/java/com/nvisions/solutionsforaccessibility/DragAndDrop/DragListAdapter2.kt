package com.nvisions.solutionsforaccessibility.DragAndDrop

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.*
import androidx.core.view.AccessibilityDelegateCompat
import androidx.core.view.ViewCompat
import androidx.core.view.accessibility.AccessibilityNodeInfoCompat
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.R
import java.util.*
import kotlin.collections.ArrayList

class DragListAdapter2 (val context: Context, val items: ArrayList<Int>) : RecyclerView.Adapter<DragListAdapter2.ViewHolder>() {
    interface OnItemDeleteListener{
        fun onItemDelete(position: Int)
    }

    interface OnItemMoveListener {
        fun onItemMoveUp(position:Int)
        fun onItemMoveDown(position:Int)
        fun onItemMoveTo(fromPosition: Int, toPosition:Int)
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
                itemDeleteListener?.onItemDelete(adapterPosition)
            }
            deleteButton.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
            contentText.setOnClickListener {
                Toast.makeText(context, items[adapterPosition].toString() + " 클릭함", Toast.LENGTH_LONG).show()
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

            ViewCompat.setAccessibilityDelegate(contentText, object :AccessibilityDelegateCompat(){
                override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfoCompat?) {
                    super.onInitializeAccessibilityNodeInfo(host, info)
                    info?.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_delete, "삭제"))
                    info?.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_move_up, "위로 이동"))
                    info?.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_move_down, "아래로 이동"))
                    info?.addAction(AccessibilityNodeInfoCompat.AccessibilityActionCompat(R.id.action_move, "이동"))
                }

                override fun performAccessibilityAction(host: View?, action: Int, args: Bundle?): Boolean {
                    when(action){
                        R.id.action_delete -> {
                            Log.d("mytag", "delete")
                            itemDeleteListener?.onItemDelete(adapterPosition)
                        }
                        R.id.action_move_up -> {
                            Log.d("mytag", "up")
                            itemMoveListener?.onItemMoveUp(adapterPosition)
                        }
                        R.id.action_move_down -> {
                            Log.d("mytag", "down")
                            itemMoveListener?.onItemMoveDown(adapterPosition)
                        }
                        R.id.action_move -> {
                            var list = ArrayList<String>()
                            for (i in 0 until itemCount){
                                if (i < adapterPosition){
                                    list.add(items[i].toString() + " 위로 이동")
                                }
                                else if (i > adapterPosition){
                                    list.add(items[i].toString() + " 아래로 이동")
                                }
                            }
                            var dialog = ListDialog.Builder(context, list).create()

                            dialog.itemClickListener = object : ListDialog.OnItemClickListener{
                                override fun onItemClick(position: Int) {
                                    if (position >= adapterPosition){
                                        itemMoveListener?.onItemMoveTo(adapterPosition, position + 1)
                                    }
                                    else{
                                        itemMoveListener?.onItemMoveTo(adapterPosition, position)
                                    }
                                    dialog.dismissDialog()
                                }
                            }
                            dialog.show()
                        }
                        else -> {
                            return super.performAccessibilityAction(host, action, args)
                        }
                    }
                    return true
                }
            })
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

