package com.jingna.xssapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.MemberEvaluateBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.page.CommentOrderActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/5/15.
 */

public class MyCommentAdapter extends RecyclerView.Adapter<MyCommentAdapter.ViewHolder> {

    private Context context;
    private List<MemberEvaluateBean.ObjBean> data;

    public MyCommentAdapter(List<MemberEvaluateBean.ObjBean> data) {
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
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(data.get(position).getRadio().equals("5")){
            holder.llYipingjia.setVisibility(View.VISIBLE);
            holder.llDaipingjia.setVisibility(View.GONE);
            holder.tvName.setText(data.get(position).getUsername());
            holder.tvTime.setText(data.get(position).getAddtime());
            holder.tvText.setText(data.get(position).getText());
            Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getImg()).into(holder.ivBottom);
            holder.tvBottom.setText(data.get(position).getServicename());
        }else {
            holder.llYipingjia.setVisibility(View.GONE);
            holder.llDaipingjia.setVisibility(View.VISIBLE);
            Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getImg()).into(holder.ivTop);
            holder.tvTop.setText(data.get(position).getServicename());
        }

        holder.tvCommentOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, CommentOrderActivity.class);
                intent.putExtra("id", data.get(position).getId());
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
        private LinearLayout llYipingjia;
        private LinearLayout llDaipingjia;
        private TextView tvName;
        private TextView tvTime;
        private TextView tvText;
        private ImageView ivBottom;
        private TextView tvBottom;
        private ImageView ivTop;
        private TextView tvTop;

        public ViewHolder(View itemView) {
            super(itemView);
            tvCommentOrder = itemView.findViewById(R.id.tv_comment_order);
            llYipingjia = itemView.findViewById(R.id.ll_yipingjia);
            llDaipingjia = itemView.findViewById(R.id.ll_daipingjia);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvText = itemView.findViewById(R.id.tv_text);
            ivBottom = itemView.findViewById(R.id.iv_bottom);
            tvBottom = itemView.findViewById(R.id.tv_bottom);
            ivTop = itemView.findViewById(R.id.iv_top);
            tvTop = itemView.findViewById(R.id.tv_top);
        }
    }

}
