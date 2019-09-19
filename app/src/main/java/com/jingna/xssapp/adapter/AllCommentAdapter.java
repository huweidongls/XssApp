package com.jingna.xssapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
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

import java.util.ArrayList;
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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_service_details_comment, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if(data.get(position).getIs_anonymous().equals("1")){
            holder.tvName.setText("用户：匿名用户");
        }else {
            holder.tvName.setText("用户："+data.get(position).getUsername());
        }
        holder.tvText.setText(data.get(position).getText());
        if(data.get(position).getOn_satisfied().equals("0")){
            holder.tvManyi.setText("满意");
        }else if(data.get(position).getOn_satisfied().equals("1")){
            holder.tvManyi.setText("不满意");
        }

        String[] s = data.get(position).getImg().split(",");
        List<String> list = new ArrayList<>();
        for (int i = 0; i<s.length; i++){
            list.add(s[i]);
        }
        CommentPicAdapter picAdapter = new CommentPicAdapter(list);
        GridLayoutManager manager = new GridLayoutManager(context, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        holder.rv.setLayoutManager(manager);
        holder.rv.setAdapter(picAdapter);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rv;
        private TextView tvManyi;
        private TextView tvText;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            tvManyi = itemView.findViewById(R.id.tv_manyi);
            tvText = itemView.findViewById(R.id.tv_text);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

}
