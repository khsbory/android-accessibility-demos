package com.nvisions.solutionsforaccessibility.gmarketSample.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nvisions.solutionsforaccessibility.R;
import com.nvisions.solutionsforaccessibility.gmarketSample.CustomAdapter;
import com.nvisions.solutionsforaccessibility.gmarketSample.CustomAdapter2;
import com.nvisions.solutionsforaccessibility.gmarketSample.Item;

import java.util.ArrayList;

public class MeatFragment2 extends Fragment {
    private RecyclerView meatRecyclerView;
    private ArrayList<Item> itemList;
    private Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_meat, container, false);
        itemList = new ArrayList<>();
        itemList.add(new Item("http://gdimg.gmarket.co.kr/1882676224/still/280?ver=1599532432",
                "소고기", "35,000원", "40,000원", "4.3", "13", "20% 쿠폰", "카드 20%"));

        itemList.add(new Item("https://gdimg.gmarket.co.kr/2001045282/still/280?ver=1609992566",
                "돼지고기", "35,000원", "40,000원", "4.3", "13", "20% 쿠폰", "카드 20%"));

        itemList.add(new Item("https://gdimg.gmarket.co.kr/818873908/still/280?ver=1610008562",
                "닭고기", "35,000원", "40,000원", "4.3", "13", "20% 쿠폰", "카드 20%"));

        meatRecyclerView = view.findViewById(R.id.meat_list_view);
        CustomAdapter2 customAdapter = new CustomAdapter2(itemList, context);
        meatRecyclerView.setAdapter(customAdapter);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        meatRecyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }
}