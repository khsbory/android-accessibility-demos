package com.nvisions.solutionsforaccessibility.expandableList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.nvisions.solutionsforaccessibility.R;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomExpandableListViewAdapter2 extends BaseExpandableListAdapter {

    private Context mContext;
    private ArrayList<String> mParentList;
    private HashMap<String, ArrayList<String>> mChildHashMap;
    private int mSelectPosition = -1;
    private int mSelectGroup = 0;
    private int lastExpandedPosition = -1;
    private boolean firstTime = true;
    private ArrayList<String> mainList;
    private CustomAdapter mainAdapter;

    // CustomExpandableListViewAdapter 생성자
    public CustomExpandableListViewAdapter2(Context context, ArrayList<String> parentList,
                                           HashMap<String, ArrayList<String>> childHashMap,
                                           ArrayList<String> mainList, CustomAdapter mainAdapter) {
        this.mContext = context;
        this.mParentList = parentList;
        this.mChildHashMap = childHashMap;
        this.mainList = mainList;
        this.mainAdapter = mainAdapter;
    }

    /* ParentListView에 대한 method */
    @Override
    public String getGroup(int groupPosition) { // ParentList의 position을 받아 해당 TextView에 반영될 String을 반환
        return mParentList.get(groupPosition);
    }

    @Override
    public int getGroupCount() { // ParentList의 원소 개수를 반환
        return mParentList.size();
    }

    @Override
    public long getGroupId(int groupPosition) { // ParentList의 position을 받아 long값으로 반환
        return groupPosition;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater groupInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            // ParentList의 layout 연결. root로 argument 중 parent를 받으며 root로 고정하지는 않음
            convertView = groupInfla.inflate(R.layout.expandable_list_group, parent, false);
        }
        final ExpandableListView expandableListView = (ExpandableListView) parent;
        RadioButton radioButton = convertView.findViewById(R.id.radio_button);

        radioButton.setChecked(isExpanded);


        if (groupPosition == 0) {
            if (firstTime) {
                radioButton.setChecked(true);
                firstTime = false;
            }
        }
        expandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (lastExpandedPosition != -1
                        && groupPosition != lastExpandedPosition) {
                    expandableListView.collapseGroup(lastExpandedPosition);
                }
                lastExpandedPosition = groupPosition;
                mSelectPosition = -1;
                mainAdapter.notifyDataSetChanged();
            }
        });

        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                mainList.clear();
                if (groupPosition == 0 && !parent.isGroupExpanded(groupPosition)) { //전체 선택시
                    mainList.add("바지");
                    mainList.add("구두");
                    mainList.add("수박");
                    mainList.add("귤");
                } else if (groupPosition == 1 && !parent.isGroupExpanded(groupPosition)) { // 의류 선택시
                    mainList.add("바지");
                    mainList.add("구두");
                } else if (groupPosition == 2 && !parent.isGroupExpanded(groupPosition)) { // 식품 선택시
                    mainList.add("수박");
                    mainList.add("귤");
                }
                parent.setVisibility(View.GONE);
                parent.setVisibility(View.VISIBLE);
                mainAdapter.notifyDataSetChanged();
                return false;
            }
        });

        TextView lblListHeader = convertView
                .findViewById(R.id.lblListHeader);
        lblListHeader.setText(getGroup(groupPosition));
        return convertView;
    }

    /* 여기서부터 ChildListView에 대한 method */
    @Override
    public String getChild(int groupPosition, int childPosition) { // groupPostion과 childPosition을 통해 childList의 원소를 얻어옴
        return this.mChildHashMap.get(this.mParentList.get(groupPosition)).get(childPosition);

    }

    @Override
    public int getChildrenCount(int groupPosition) { // ChildList의 크기를 int 형으로 반환
        return this.mChildHashMap.get(this.mParentList.get(groupPosition)).size();

    }

    @Override
    public long getChildId(int groupPosition, int childPosition) { // ChildList의 ID로 long 형 값을 반환
        return childPosition;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, final ViewGroup parent) {
        // ChildList의 View. 위 ParentList의 View를 얻을 때와 비슷하게 Layout 연결 후, layout 내 TextView, ImageView를 연결
        String childData = getChild(groupPosition, childPosition);
        if (convertView == null) {
            LayoutInflater childInfla = (LayoutInflater) this.mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = childInfla.inflate(R.layout.expandable_list_item, null);
        }
        final RadioButton radioButton = convertView.findViewById(R.id.radio_button);
        TextView txtListChild = convertView.findViewById(R.id.lblListItem);

        radioButton.setChecked(childPosition == mSelectPosition && groupPosition == mSelectGroup);


        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainList.clear();
                if (groupPosition == 1 && childPosition == 0) {//일반 의류 선택시
                    mainList.add("바지");
                } else if (groupPosition == 1 && childPosition == 1) { //신발 선택시
                    mainList.add("구두");
                } else if (groupPosition == 2 && childPosition == 0) { // 여름과일 선택시
                    mainList.add("수박");
                } else if (groupPosition == 2 && childPosition == 1) { // 겨울과일 선택시
                    mainList.add("귤");
                }
                mSelectGroup = groupPosition;
                mSelectPosition = childPosition;
                parent.setVisibility(View.GONE);
                parent.setVisibility(View.VISIBLE);
                mainAdapter.notifyDataSetChanged();
                notifyDataSetChanged();
            }
        });


        txtListChild.setText(childData);
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    } // stable ID인지 boolean 값으로 반환

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    } // 선택여부를 boolean 값으로 반환

}
