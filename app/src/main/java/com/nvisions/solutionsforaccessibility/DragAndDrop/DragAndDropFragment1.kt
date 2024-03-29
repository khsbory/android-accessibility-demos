package com.nvisions.solutionsforaccessibility.DragAndDrop

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.TextView
import androidx.core.view.get
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.nvisions.solutionsforaccessibility.AccessibilityUtil.AccessibilityKotlin
import com.nvisions.solutionsforaccessibility.R
import kotlinx.android.synthetic.main.fragment_drag_and_drop1.*
import kotlinx.android.synthetic.main.view_holder_item.view.*

class DragAndDropFragment1 : Fragment() {

    lateinit var rViewAdapter: DragListAdapter1
    lateinit var touchHelper: ItemTouchHelper
    var itemArr = arrayListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_drag_and_drop1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(
            requireContext(), RecyclerView.VERTICAL, false
        )
        rViewAdapter = DragListAdapter1(requireContext(), itemArr)
        val callback = DragItemTouchHelperCallback(rViewAdapter)
        touchHelper = ItemTouchHelper(callback)
        touchHelper.attachToRecyclerView(rView)
        rView.adapter = rViewAdapter
        rViewAdapter.itemDeleteListener = object : DragListAdapter1.OnItemDeleteListener {
            override fun onItemDelete(holder: DragListAdapter1.ViewHolder, position: Int) {
                itemArr.removeAt(position)
                rViewAdapter.notifyItemRemoved(position)
                rViewAdapter.notifyItemRangeChanged(position, itemArr.size)
                val a11y = AccessibilityKotlin
                if (rViewAdapter.itemCount == 0) {
                    a11y.sendFocusThisView(addButton)
                } else {
                    if (position == rViewAdapter.itemCount) {
                        a11y.sendFocusThisView((rView.get(position - 1) as ViewGroup).getChildAt(2))
                    } else {
                        a11y.sendFocusThisView((rView.get(position) as ViewGroup).getChildAt(2))
                    }
                }
            }
        }

        rViewAdapter.itemMoveListener = object : DragListAdapter1.OnItemMoveListener {
            override fun onItemMoveUp(position: Int) {
                if (position - 1 >= 0) {
                    rViewAdapter.moveItem(position, position - 1)
                    Handler().postDelayed({
                        (rView.get(position - 1) as ViewGroup).getChildAt(2)
                            .performAccessibilityAction(
                                AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS,
                                null
                            )
                        (rView.get(position - 1) as ViewGroup).getChildAt(2).requestFocus()
                        (rView.get(position - 1) as ViewGroup).getChildAt(2)
                            .announceForAccessibility(
                                ((rView.get(position) as ViewGroup).getChildAt(2) as TextView).text.toString() + "위로 이동됨"
                            )
                    }, 200)
                } else {
                    (rView.get(position) as ViewGroup).getChildAt(2)
                        .announceForAccessibility("위로 이동할 수 없음")
                }
            }

            override fun onItemMoveDown(position: Int) {
                if (position + 1 < rViewAdapter.itemCount) {
                    rViewAdapter.moveItem(position, position + 1)
                    Handler().postDelayed({
                        (rView.get(position + 1) as ViewGroup).getChildAt(2)
                            .performAccessibilityAction(
                                AccessibilityNodeInfo.ACTION_ACCESSIBILITY_FOCUS,
                                null
                            )
                        (rView.get(position + 1) as ViewGroup).getChildAt(2).requestFocus()
                        (rView.get(position + 1) as ViewGroup).getChildAt(2)
                            .announceForAccessibility(
                                ((rView.get(position) as ViewGroup).getChildAt(2) as TextView).text.toString() + "아래로 이동됨"
                            )
                    }, 200)
                } else {
                    (rView.get(position) as ViewGroup).getChildAt(2)
                        .announceForAccessibility("아래로 이동할 수 없음")
                }
            }
        }


        addButton.setOnClickListener {
            var max = 0
            for (i in itemArr) {
                if (i > max) {
                    max = i
                }
            }
            itemArr.add(max + 1)
            rViewAdapter.notifyItemInserted(itemArr.size - 1)
            it.announceForAccessibility((max + 1).toString() + " 추가됨")
        }
    }


}


