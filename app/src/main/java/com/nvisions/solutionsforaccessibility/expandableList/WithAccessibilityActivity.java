package com.nvisions.solutionsforaccessibility.expandableList;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.nvisions.solutionsforaccessibility.R;

import java.util.ArrayList;
import java.util.HashMap;

public class WithAccessibilityActivity extends AppCompatActivity {
    private ExpandableListView expandableListView;
    public ArrayList<String> parentList; // ExpandableListView의 Parent 항목이 될 List 변수 선언
    public ArrayList<String> clothList;
    public ArrayList<String> foodList;
    public ArrayList<String> emptyList;
    public HashMap<String, ArrayList<String>> childList; // 위 ParentList와 ChildList를 연결할 HashMap 변수 선언

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expandable_activity_main);
        setTitle("접근성 적용");
        final DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        expandableListView = findViewById(R.id.list_slidermenu);
        final ListView mainListView = findViewById(R.id.list_view);
        final Button filterButton = findViewById(R.id.filter_setting);
        Button filterClose = findViewById(R.id.filter_close);

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {

            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                Handler handler = new Handler(Looper.getMainLooper());
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        filterButton.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
                    }
                },500);

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        filterClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.closeDrawer(Gravity.RIGHT);
            }
        });
        filterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawerLayout.openDrawer(Gravity.RIGHT);
            }
        });
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("바지");
        arrayList.add("구두");
        arrayList.add("수박");
        arrayList.add("귤");

        CustomAdapter adapter = new CustomAdapter(this, arrayList);
        mainListView.setAdapter(adapter);

        parentList = new ArrayList<>();
        parentList.add("전체");
        parentList.add("의류");
        parentList.add("식품");
        emptyList = new ArrayList<>();
        clothList = new ArrayList<>(); // 의류
        clothList.add("일반 의류");
        clothList.add("신발");
        foodList = new ArrayList<>(); //식품
        foodList.add("여름 과일");
        foodList.add("겨울 과일");

        childList = new HashMap<>();

        childList.put(parentList.get(0), emptyList);
        childList.put(parentList.get(1), clothList);
        childList.put(parentList.get(2), foodList);
        CustomExpandableListViewAdapter expAdapter = new CustomExpandableListViewAdapter(this, parentList, childList, arrayList, adapter);

        expandableListView.setAdapter(expAdapter);
    }
}
