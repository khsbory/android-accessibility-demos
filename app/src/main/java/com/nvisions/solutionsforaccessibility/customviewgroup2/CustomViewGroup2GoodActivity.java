package com.nvisions.solutionsforaccessibility.customviewgroup2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nvisions.solutionsforaccessibility.R;

import java.util.ArrayList;

public class CustomViewGroup2GoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view_group2_good);
        initRecyclerView();
    }

    private void initRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        ;

        // 리사이클러뷰에 SimpleTextAdapter 객체 지정.
        ArrayList<String> datas = new ArrayList<>();
        datas.add("item1");

        RecyclerImageTextAdapter adapter = new RecyclerImageTextAdapter(datas);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this)) ;
    }


    public class RecyclerImageTextAdapter extends RecyclerView.Adapter<RecyclerImageTextAdapter.ViewHolder> {
        private ArrayList<String> mData = null;

        // 생성자에서 데이터 리스트 객체를 전달받음.
        RecyclerImageTextAdapter(ArrayList<String> list) {
            mData = list;
        }

        // onCreateViewHolder() - 아이템 뷰를 위한 뷰홀더 객체 생성하여 리턴.
        @Override
        public RecyclerImageTextAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            Context context = parent.getContext();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            View view = inflater.inflate(R.layout.recycler_item_customviewgroup_good, parent, false);
            RecyclerImageTextAdapter.ViewHolder vh = new RecyclerImageTextAdapter.ViewHolder(view);

            return vh;
        }

        // onBindViewHolder() - position에 해당하는 데이터를 뷰홀더의 아이템뷰에 표시.
        @Override
        public void onBindViewHolder(RecyclerImageTextAdapter.ViewHolder holder, int position) {

            String item = mData.get(position);

        }

        // getItemCount() - 전체 데이터 갯수 리턴.
        @Override
        public int getItemCount() {
            return mData.size();
        }

        // 아이템 뷰를 저장하는 뷰홀더 클래스.
        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView icon;
            TextView title;
            TextView desc;

            ViewHolder(View itemView) {
                super(itemView);

                // 뷰 객체에 대한 참조. (hold strong reference)
                icon = itemView.findViewById(R.id.image);
                title = itemView.findViewById(R.id.title);
                desc = itemView.findViewById(R.id.text);
            }
        }
    }
}