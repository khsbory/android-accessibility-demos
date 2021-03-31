package com.nvisions.solutionsforaccessibility.expandableList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.nvisions.solutionsforaccessibility.R;
import java.util.ArrayList;


public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<String> arrayList;
    private LayoutInflater layoutInflater;
    public CustomAdapter(Context context, ArrayList<String> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
        this.layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.expandable_main_item, null);
        TextView textView = view.findViewById(R.id.main_item);
        textView.setText(arrayList.get(position));
        view.setEnabled(false);
        return view;
    }
}
