package com.jingna.xssapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.page.CommentOrderActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/5/15.
 */

public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public MyCommentAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_my_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvCommentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, CommentOrderActivity.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvCommentOrder;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCommentOrder = itemView.findViewById(R.id.tv_comment_order);
        }
    }

}
