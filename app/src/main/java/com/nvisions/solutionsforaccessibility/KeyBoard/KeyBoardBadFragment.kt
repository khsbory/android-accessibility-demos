package com.nvisions.solutionsforaccessibility.KeyBoard

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.fragment_key_board_bad.*

class KeyBoardBadFragment : Fragment() {
    lateinit var keyAdapter: KeyBoardBadAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_key_board_bad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        keyBoardView.layoutManager = GridLayoutManager(requireContext(), 3)
        keyAdapter = KeyBoardBadAdapter()
        keyBoardView.adapter = keyAdapter
//        keyAdapter.itemTouchListener = object :KeyBoardBadAdapter.OnItemTouchListener{
//            override fun OnItemTouch(input: String, position: Int) {
//                if(input != "" && input != null){
//                    var num = editText.text.toString() + input
//                    editText.setText(num)
//                }
//                else{
//                    if(position == 11){
//                        val editLength = editText.text.toString().length
//                        if (editLength >= 1){
//                            var num = editText.text.toString().substring(0, editLength - 1)
//                            editText.setText(num)
//                        }
//                    }
//                }
//            }
//        }
        keyAdapter.itemClickListener = object :KeyBoardBadAdapter.OnItemClickListener {
            override fun OnItemClick(input: String, position: Int) {
                if(input != "" && input != null){
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

class KeyBoardBadAdapter : RecyclerView.Adapter<KeyBoardBadAdapter.ViewHolder>() {
    interface OnItemClickListener{
        fun OnItemClick(input: String, position: Int)
    }

//    interface OnItemTouchListener{
//        fun OnItemTouch(input: String, position: Int)
//    }

    var itemClickListener :OnItemClickListener? = null
//    var itemTouchListener :OnItemTouchListener? = null

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var keyButton: Button
        init {
            keyButton = itemView.findViewById(R.id.content)
            keyButton.setOnClickListener {
                val input = keyButton.text.toString()
                itemClickListener?.OnItemClick(input, adapterPosition)
            }
//            keyButton.setOnTouchListener { v, event ->
//                val input = keyButton.text.toString()
//                itemTouchListener?.OnItemTouch(input, adapterPosition)
//                true
//            }
        }
    }

    override fun getItemCount(): Int {
        return 12
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): KeyBoardBadAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.keyboard_item, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: KeyBoardBadAdapter.ViewHolder, position: Int) {
        if (holder is KeyBoardBadAdapter.ViewHolder) {
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

