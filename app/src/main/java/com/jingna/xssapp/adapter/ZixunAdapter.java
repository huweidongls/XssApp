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
import com.jingna.xssapp.bean.ZixunListBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.page.ZixunDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/5/15.
 */

public class ZixunAdapter extends RecyclerView.Adapter<ZixunAdapter.ViewHolder> {

    private Context context;
    private List<ZixunListBean.ObjBean> data;

    public ZixunAdapter(List<ZixunListBean.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_zixun, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.tvTitle.setText(data.get(position).getTitle());
        holder.tvTime.setText(data.get(position).getAddtime());
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getImgurl()).into(holder.iv);

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ZixunDetailsActivity.class);
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

        private LinearLayout ll;
        private TextView tvTitle;
        private TextView tvTime;
        private ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTime = itemView.findViewById(R.id.tv_time);
            iv = itemView.findViewById(R.id.iv);
        }
    }

}
