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
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.fragment_key_board_bad.*
import kotlinx.android.synthetic.main.fragment_key_board_bad.confrimButton
import kotlinx.android.synthetic.main.fragment_key_board_bad.editText
import kotlinx.android.synthetic.main.fragment_key_board_bad.keyBoardView
import kotlinx.android.synthetic.main.fragment_key_board_good.*
import java.security.Key

class KeyBoardBadFragment : Fragment() {
    lateinit var keyAdapter: KeyBoardBadAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_key_board_bad, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        editText.isEnabled = false
        confrimButton.isEnabled = false
        keyBoardView.layoutManager = GridLayoutManager(requireContext(), 3)
        keyAdapter = KeyBoardBadAdapter()
        keyBoardView.adapter = keyAdapter
        keyAdapter.itemTouchListener = object :KeyBoardBadAdapter.OnItemTouchListener {
            override fun OnItemTouch() {
                val editLength = editText.text.toString().length
                if (editLength >= 1){
                    var num = editText.text.toString().substring(0, editLength - 1)
                    confrimButton.isEnabled = false
                    editText.setText(num)
                }

            }
        }

        keyAdapter.itemClickListener = object :KeyBoardBadAdapter.OnItemClickListener {
            override fun OnItemClick(input: String, position: Int) {
                if(input != "" && input != null){
                    var num = editText.text.toString() + input
                    confrimButton.isEnabled = true
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

            override fun OnItemLongClick(view: View, input: String, position: Int) {
                val editLength = editText.text.toString().length
                if (editLength >= 1){
                    var num = ""
                    confrimButton.isEnabled = false
                    editText.setText(num)

                }
            }
        }
        confrimButton.setOnClickListener {
            val builder = AlertDialog.Builder(requireContext())
            builder.setMessage("잔액이 부족합니다")
            builder.setPositiveButton(R.string.confirm ) {_, _-> editText.text.clear() }
            builder.create().show()
            confrimButton.isEnabled = false
        }

    }
}

class KeyBoardBadAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val VIEW_TYPE_NUM = 0
    val VIEW_TYPE_DELETE = 1

    interface OnItemClickListener{
        fun OnItemClick(input: String, position: Int)
        fun OnItemLongClick(view: View, input: String, position: Int)
    }

    interface OnItemTouchListener{
        fun OnItemTouch()
    }

    var itemClickListener :OnItemClickListener? = null
    var itemTouchListener :OnItemTouchListener? = null


    inner class NumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var keyButton: Button
        init {
            keyButton = itemView.findViewById(R.id.content)
            keyButton.setOnClickListener {
                val input = keyButton.text.toString()
                itemClickListener?.OnItemClick(input, adapterPosition)
            }
        }
    }

    inner class DeleteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var deleteKey: ImageButton
        init {
            deleteKey = itemView.findViewById(R.id.deleteKey)
            deleteKey.setOnTouchListener { v, event ->
                if (event.actionMasked == MotionEvent.ACTION_UP) {
                    itemTouchListener?.OnItemTouch()
                    Log.d("mytag", "up")
                }
                false
            }

            deleteKey.setOnLongClickListener {
                itemClickListener?.OnItemLongClick(it, "", adapterPosition)
                false
            }
        }
    }



    override fun getItemCount(): Int {
        return 12
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 11)
            return VIEW_TYPE_DELETE
        else
            return VIEW_TYPE_NUM
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_NUM){
            val v = LayoutInflater.from(parent.context).inflate(R.layout.keyboard_item, parent, false)
            return NumViewHolder(v)
        }
        else{
            val v = LayoutInflater.from(parent.context).inflate(R.layout.keyboard_delete_item, parent, false)
            return DeleteViewHolder(v)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is KeyBoardBadAdapter.NumViewHolder) {
            if(position < 9){
                holder.keyButton.text = (position + 1).toString()
            }
            else if (position == 9) {
                holder.keyButton.background = null
            }
            else if (position == 10) {
                holder.keyButton.text = "0"
            }
        }
    }
}

