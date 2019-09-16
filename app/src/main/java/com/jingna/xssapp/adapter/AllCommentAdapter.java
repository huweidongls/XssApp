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
import com.jingna.xssapp.bean.AllCommentBean;
import com.jingna.xssapp.bean.MemberEvaluateBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.page.CommentOrderActivity;
import com.jingna.xssapp.page.ServiceDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/5/15.
 */

public class AllCommentAdapter extends RecyclerView.Adapter<AllCommentAdapter.ViewHolder> {

    private Context context;
    private List<AllCommentBean.ObjBean> data;

    public AllCommentAdapter(List<AllCommentBean.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_all_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getHeadimg()).into(holder.ivHead);
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getImg()).into(holder.ivBottom);
        holder.tvName.setText(data.get(position).getUsername());
        holder.tvTime.setText(data.get(position).getAddtime());
        holder.tvText.setText(data.get(position).getText());
        holder.tvBottom.setText(data.get(position).getServicename());

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivHead;
        private TextView tvName;
        private TextView tvTime;
        private TextView tvText;
        private ImageView ivBottom;
        private TextView tvBottom;

        public ViewHolder(View itemView) {
            super(itemView);
            ivHead = itemView.findViewById(R.id.iv_head);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvText = itemView.findViewById(R.id.tv_text);
            ivBottom = itemView.findViewById(R.id.iv_bottom);
            tvBottom = itemView.findViewById(R.id.tv_bottom);
        }
    }

}
