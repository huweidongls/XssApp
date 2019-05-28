package com.jingna.xssapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.ServiceContentBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/17.
 */

public class ServiceDetailsCommentAdapter extends RecyclerView.Adapter<ServiceDetailsCommentAdapter.ViewHolder> {

    private Context context;
    private List<ServiceContentBean.ObjBean.EvaluateBean> data;

    public ServiceDetailsCommentAdapter(List<ServiceContentBean.ObjBean.EvaluateBean> data) {
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
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvName.setText("用户："+data.get(position).getUsername());
        holder.tvText.setText(data.get(position).getText());
        if(data.get(position).getOn_satisfied().equals("0")){
            holder.tvManyi.setText("满意");
        }else if(data.get(position).getOn_satisfied().equals("1")){
            holder.tvManyi.setText("不满意");
        }

        List<String> list = data.get(position).getImg();
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
