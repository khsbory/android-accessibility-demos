package com.nvisions.solutionsforaccessibility.gmarketSample;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityEvent;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.nvisions.solutionsforaccessibility.R;

import java.util.ArrayList;

public class CustomAdapter2 extends RecyclerView.Adapter<CustomAdapter2.ViewHolder> {
    private ArrayList<Item> itemList;
    private Context context;

    public CustomAdapter2(ArrayList<Item> itemList, Context context) {
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
        holder.price.setContentDescription("정가: " + item.getPrice());
        holder.salePrice.setContentDescription("할인가: " + item.getSalePrice());
        holder.price.setPaintFlags(holder.price.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.salePrice.setText(item.getSalePrice());
        holder.gradeAndReview.setText(item.getGrade() + " (" + item.getReview() + ")");
        holder.gradeAndReview.setContentDescription("평점: " + item.getGrade() + " (후기 개수: " + item.getReview() + ")");
        holder.itemView.setContentDescription(holder.title.getText() + ", " + holder.price.getContentDescription() + ", " + holder.salePrice.getContentDescription() + ", " +
                holder.gradeAndReview.getContentDescription() + "," +item.getCouponSale() + ", " + item.getCardSale());

        holder.couponSale.setText(item.getCouponSale());
        //holder.couponSale.setContentDescription("쿠폰 할인율: " + item.getCouponSale());
        holder.cardSale.setText(item.getCardSale());
        //holder.cardSale.setContentDescription("카드 할인율" + item.getCardSale());

        holder.seeReview.setText("초점 보내기:" + item.getTitle());

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
        private TextView couponSale;
        private TextView cardSale;

        public ViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.imageView);
            title = v.findViewById(R.id.title_txt);
            salePrice = v.findViewById(R.id.sale_price_txt);
            price = v.findViewById(R.id.price_txt);
            gradeAndReview = v.findViewById(R.id.grade_txt);
            seeReview = v.findViewById(R.id.see_review_txt);
            couponSale = v.findViewById(R.id.coupon_sale);
            cardSale = v.findViewById(R.id.card_sale);
            seeReview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    itemView.sendAccessibilityEvent(AccessibilityEvent.TYPE_VIEW_FOCUSED);
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

