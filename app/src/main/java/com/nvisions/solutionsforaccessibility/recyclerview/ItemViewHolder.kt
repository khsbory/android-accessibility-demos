package com.nvisions.solutionsforaccessibility.recyclerview

import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.view_holder_item.view.item_delete_button
import kotlinx.android.synthetic.main.view_holder_item.view.item_name

class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setItem(item: Item, deleteMode: Boolean, itemDeleted: (Item) -> Unit) {
        itemView.item_name.text = item.name
itemView.item_delete_button.contentDescription = itemView.context.getString(R.string.content_description_delete_item) + ", " + item.name
        itemView.item_delete_button.visibility = if (deleteMode) View.VISIBLE else View.GONE
        itemView.item_delete_button.setOnClickListener {
            setDeletedState(item)
            itemDeleted.invoke(item)
        }

        if (deleteMode && item.deleted) {
            setDeletedState(item)
        }
    }

    private fun setDeletedState(item: Item) {
        itemView.item_delete_button.visibility = View.GONE
        itemView.item_name.text = itemView.context.getString(R.string.item_deleted_x, item.name)
        itemView.item_name.setTextColor(Color.parseColor("#ff0000"));
    }
}
