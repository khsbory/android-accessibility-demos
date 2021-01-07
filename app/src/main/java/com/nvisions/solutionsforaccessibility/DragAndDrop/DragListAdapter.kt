package com.nvisions.solutionsforaccessibility.DragAndDrop

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.R
import java.util.*
import kotlin.collections.ArrayList

class DragListAdapter (val context: Context, val items: ArrayList<Int>) : RecyclerView.Adapter<DragListAdapter.ViewHolder>() {
    interface OnItemDeleteListener{
        fun onItemDelete(holder:ViewHolder, position: Int)
    }

    var itemDeleteListener :OnItemDeleteListener? = null
    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contentText: TextView
        var deleteButton: Button
        var dragButton: ImageView

        init {
            contentText = itemView.findViewById(R.id.contentText)
            deleteButton = itemView.findViewById(R.id.deleteButton)
            dragButton = itemView.findViewById(R.id.dragButton)
            deleteButton.setOnClickListener {
//                items.removeAt(adapterPosition)
//                notifyItemRemoved(adapterPosition)
//                notifyItemRangeChanged(adapterPosition, items.size)
                itemDeleteListener?.onItemDelete(this, adapterPosition)
            }
            contentText.setOnClickListener {
                Toast.makeText(context, items[adapterPosition].toString() + " 클릭함", Toast.LENGTH_LONG).show()
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