package com.nvisions.solutionsforaccessibility.KeyBoard

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.LayerFocus.LayerFocusBaseAdapter
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.fragment_key_board_bad.*

class KeyBoardBadFragment : Fragment() {
    lateinit var keyAdapter: KeyBoardAdapter

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
        keyAdapter = KeyBoardAdapter()
        keyBoardView.adapter = keyAdapter
        keyAdapter.itemClickListener = object :KeyBoardAdapter.OnItemClickListener {
            override fun OnItemClick(input: String, position: Int) {
                if(input != "" && input != null){
                    Log.d("mytag", "input : " + input)
                    var num = editText.text.toString() + input
                    editText.setText(num)
                }
                else{
                    if(position == 11){
                        val editLength = editText.text.toString().length
                        if (editLength >= 1){
                            var num = editText.text.toString().substring(0, editLength - 1)
                            editText.setText(num)
                        }
                    }
                }

            }
        }
        confrimButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("잔액이 부족합니다")
            builder.setPositiveButton(R.string.confirm ) {_, _-> editText.text.clear() }
            builder.create().show()
        }

    }
}

class KeyBoardAdapter : RecyclerView.Adapter<KeyBoardAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(input: String, position: Int)
    }
    var itemClickListener :OnItemClickListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var keyButton: Button
        init {
            keyButton = itemView.findViewById(R.id.content)
            keyButton.setOnClickListener {
                val input = keyButton.text.toString()
                itemClickListener?.OnItemClick(input, adapterPosition)
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
            if(position < 9){
                holder.keyButton.text = (position + 1).toString()
            }
            else if (position == 10) {
                holder.keyButton.text = "0"
            }
            else if (position == 11) {
                holder.keyButton.setBackgroundResource(R.drawable.back)
            }

        }
    }
}
