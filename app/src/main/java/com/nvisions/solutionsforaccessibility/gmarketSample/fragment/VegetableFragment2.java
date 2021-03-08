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


public class VegetableFragment2 extends Fragment {
    private RecyclerView vegetableRecyclerView;
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
        View view = inflater.inflate(R.layout.fragment_vegetable, container, false);
        itemList = new ArrayList<>();
        itemList.add(new Item("https://gdimg.gmarket.co.kr/1751664898/still/280?ver=1614768771",
                "콩나물", "35,000원", "40,000원", "4.3", "13", "20% 쿠폰", "카드 20%"));

        itemList.add(new Item("https://gdimg.gmarket.co.kr/2039317794/still/280?ver=1613715348",
                "시금치", "35,000원", "40,000원", "4.3", "13", "20% 쿠폰", "카드 20%"));

        itemList.add(new Item("https://gdimg.gmarket.co.kr/1998372892/still/280?ver=1609740374",
                "김치", "35,000원", "40,000원", "4.3", "13", "20% 쿠폰", "카드 20%"));

        vegetableRecyclerView = view.findViewById(R.id.vegetable_list_view);
        CustomAdapter2 customAdapter = new CustomAdapter2(itemList, context);
        vegetableRecyclerView.setAdapter(customAdapter);
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        vegetableRecyclerView.setLayoutManager(linearLayoutManager);
        return view;
    }
}