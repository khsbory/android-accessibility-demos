package com.nvisions.solutionsforaccessibility.gmarketSample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nvisions.solutionsforaccessibility.R;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder> {
    private ArrayList<Item> itemList;
    private Context context;

    public CustomAdapter(ArrayList<Item> itemList, Context context) {
        this.itemList = itemList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = itemList.get(position);
        Glide.with(context)
                .load(item.getImage())
                .into(holder.imageView);

        holder.title.setText(item.getTitle());
        holder.price.setText(item.getPrice());
        holder.price.setPaintFlags(holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.salePrice.setText(item.getSalePrice());
        holder.gradeAndReview.setText(item.getGrade() + " (" + item.getReview() + ")");

        holder.seeReview.setText("상품평 보기:" + item.getTitle());
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;
        private TextView title;
        private TextView salePrice;
        private TextView price;
        private TextView gradeAndReview;
        private TextView seeReview;

        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView);
            title = v.findViewById(R.id.title_txt);
            salePrice = v.findViewById(R.id.sale_price_txt);
            price = v.findViewById(R.id.price_txt);
            gradeAndReview = v.findViewById(R.id.grade_txt);
            seeReview = v.findViewById(R.id.see_review_txt);
            seeReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAlertDialog();
                }
            });
        }

        private void showAlertDialog() {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("지금은 준비중 입니다.");
            builder.setPositiveButton("확인",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            //nothing
                        }
                    });
            builder.show();
        }
    }
}

