package com.nvisions.solutionsforaccessibility.HorizontalScroll;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.nvisions.solutionsforaccessibility.MainActivity;
import com.nvisions.solutionsforaccessibility.R;
import com.nvisions.solutionsforaccessibility.TabControl.TabBadActivity;
import com.nvisions.solutionsforaccessibility.TabControl.TabGoodActivity;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class HorizontalScrollActivity extends AppCompatActivity{

    private LinearLayout viewMain, viewgroup1, viewgroup2, viewgroup3;

    private RecyclerView listview, listview2, listview3;
    private HorizontalAdapt adapter, adapter2;
    private VerticalAdapt adapter3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horizontal_scroll);
        setTitle(getString(R.string.horizontal_scroll_demo));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // 동적 page 추가. (viewgroup 생성)
        viewMain = findViewById(R.id.view_main);
        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.linearlayout_viewgroup_1, viewMain, true);

        LayoutInflater inflater2 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater2.inflate(R.layout.linearlayout_viewgroup_2, viewMain, true);

        LayoutInflater inflater3 = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater3.inflate(R.layout.linearlayout_viewgroup_3, viewMain, true);

        initHorizontalScroll_1();
        initHorizontalScroll_2();
        initVerticalScroll();

    }

    private void initHorizontalScroll_1() {

        listview = viewMain.findViewById(R.id.first_recycleview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview.setLayoutManager(layoutManager);

        ArrayList<String> itemList = new ArrayList<>();

        for(int i = 1 ; i<= 10 ; i++){
            itemList.add(i+"");
        }

        adapter = new HorizontalAdapt(this, itemList, onClickItem);
        listview.setAdapter(adapter);

        HorizontalDecoration decoration = new HorizontalDecoration();
        listview.addItemDecoration(decoration);
    }


    private View.OnClickListener onClickItem = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
            Toast.makeText(HorizontalScrollActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    };

    private void initHorizontalScroll_2() {

        listview2 = viewMain.findViewById(R.id.first_recycleview2);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        listview2.setLayoutManager(layoutManager);

        ArrayList<String> itemList = new ArrayList<>();
        itemList.add("a");
        itemList.add("b");
        itemList.add("c");
        itemList.add("d");
        itemList.add("e");

        adapter2 = new HorizontalAdapt(this, itemList, onClickItem2);
        listview2.setAdapter(adapter2);

        HorizontalDecoration decoration = new HorizontalDecoration();
        listview2.addItemDecoration(decoration);
    }

    private View.OnClickListener onClickItem2 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
            Toast.makeText(HorizontalScrollActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    };

    private void initVerticalScroll() {

        listview3 = viewMain.findViewById(R.id.first_recycleview3);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        listview3.setLayoutManager(layoutManager);

        ArrayList<String> itemList = new ArrayList<>();
        for(int i = 1 ; i<= 100 ; i++){
            itemList.add(i+"");
        }

        adapter3 = new VerticalAdapt(this, itemList, onClickItem3);
        listview3.setAdapter(adapter3);

//        HorizontalDecoration decoration = new HorizontalDecoration();
//        listview3.addItemDecoration(decoration);
    }

    private View.OnClickListener onClickItem3 = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String str = (String) v.getTag();
            Toast.makeText(HorizontalScrollActivity.this, str, Toast.LENGTH_SHORT).show();
        }
    };

}

