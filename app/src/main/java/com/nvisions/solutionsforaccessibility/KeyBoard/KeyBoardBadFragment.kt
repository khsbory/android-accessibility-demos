package com.nvisions.solutionsforaccessibility.KeyBoard

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.LayerFocus.LayerFocusBaseAdapter
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.fragment_key_board_bad.*

class KeyBoardBadFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_key_board_bad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        keyBoardView.layoutManager = GridLayoutManager(requireContext(), 3)
        keyBoardView.adapter = KeyBoardAdapter()
    }
}

class KeyBoardAdapter () : RecyclerView.Adapter<KeyBoardAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(holder: KeyBoardAdapter.ViewHolder, view:View, position: Int)
    }
    var itemClickListener :OnItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var contentText: TextView
        init {
            contentText = itemView.findViewById(R.id.content)
            itemView.setOnClickListener {
                itemClickListener?.OnItemClick(this, it, adapterPosition)
            }
        }
    }

    override fun getItemCount(): Int {
        return 12
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyBoardAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.keyboard_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: KeyBoardAdapter.ViewHolder, position: Int) {
        if (holder is KeyBoardAdapter.ViewHolder) {
            if(position < 9 && position == 10)
                holder.contentText.text = (position + 1).toString()
            else if (position == 11) //지우기 버튼
                holder.contentText.setBackgroundResource(R.drawable.back)

        }
    }
}
