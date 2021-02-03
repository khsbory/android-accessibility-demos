package com.nvisions.solutionsforaccessibility.KeyBoard

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.fragment_key_board_good.*

class KeyBoardGoodFragment : Fragment() {
    lateinit var keyAdapter: KeyBoardGoodAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_key_board_good, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        keyBoardView.layoutManager = GridLayoutManager(requireContext(), 3)
        keyAdapter = KeyBoardGoodAdapter()
        keyBoardView.adapter = keyAdapter
        keyBoardView.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO

        keyAdapter.itemClickListener = object: KeyBoardGoodAdapter.OnItemClickListener {
            override fun OnItemClick(view: View, input: String, position: Int) {
                if(input != "" && input != null){
                    //0-9 숫자 버튼
                    var num = editText.text.toString() + input
                    editText.setText(num)
                    editText.announceForAccessibility(editText.text.toString())
                }
                else{
                    // 지우기 버튼
                    if(position == 11){
                        val editLength = editText.text.toString().length
                        if (editLength >= 1){
                            var num = editText.text.toString().substring(0, editLength - 1)
                            editText.setText(num)
                            if(num.isEmpty()){
                                editText.announceForAccessibility("숫자 없음")
                            }
                            else{
                                editText.announceForAccessibility(editText.text.toString())
                            }
                        }
                    }
                }
            }

            override fun OnItemLongClick(view: View, input: String, position: Int) {
                val editLength = editText.text.toString().length
                if (editLength >= 1){
                    var num = ""
                    editText.setText(num)
                    if(num.isEmpty()){
                        editText.announceForAccessibility("숫자 없음")
                    }
                    else{
                        editText.announceForAccessibility(editText.text.toString())
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

class KeyBoardGoodAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    val VIEW_TYPE_NUM = 0
    val VIEW_TYPE_DELETE = 1

    interface OnItemClickListener{
        fun OnItemClick(view: View, input: String, position: Int)
        fun OnItemLongClick(view: View, input: String, position: Int)
    }

    var itemClickListener :OnItemClickListener? = null

    inner class NumViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var keyButton: Button
        init {
            keyButton = itemView.findViewById(R.id.content)
            keyButton.setOnClickListener {
                val input = keyButton.text.toString()
                itemClickListener?.OnItemClick(it, input, adapterPosition)
            }

            @RequiresApi(api = Build.VERSION_CODES.Q)
            keyButton.accessibilityDelegate = object : View.AccessibilityDelegate() {
                override fun onInitializeAccessibilityNodeInfo(host: View?, info: AccessibilityNodeInfo?) {
                    super.onInitializeAccessibilityNodeInfo(host, info)
                    info?.isTextEntryKey = true
                }
            }
        }
    }

    inner class DeleteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var deleteKey: ImageView
        init {
            deleteKey = itemView.findViewById(R.id.deleteKey)
            deleteKey.setOnClickListener {
                Log.d("mytag", "click")
                itemClickListener?.OnItemClick(it, "", adapterPosition)
            }
            deleteKey.setOnLongClickListener {
                Log.d("mytag", "longclick")
                itemClickListener?.OnItemLongClick(it, "", adapterPosition)
                false
            }
        }
    }

    override fun getItemCount(): Int {
        return 12
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

    override fun getItemViewType(position: Int): Int {
        if (position == 11)
            return VIEW_TYPE_DELETE
        else
            return VIEW_TYPE_NUM
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is KeyBoardGoodAdapter.NumViewHolder) {
            if(position < 9){
                holder.keyButton.text = (position + 1).toString()
            }
            else if (position == 10) {
                holder.keyButton.text = "0"
            }
            else if (position == 9){
                holder.keyButton.importantForAccessibility = View.IMPORTANT_FOR_ACCESSIBILITY_NO
                holder.keyButton.background = null
            }
        }

    }
}
