package com.jingna.xssapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.ServiceTypeAndServiceListBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.page.ServiceDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/5/10.
 */

public class FenleiRightRvAdapter extends RecyclerView.Adapter<FenleiRightRvAdapter.ViewHolder> {

    private Context context;
    private List<ServiceTypeAndServiceListBean.ObjBean.ServiceBean> data;

    public FenleiRightRvAdapter(List<ServiceTypeAndServiceListBean.ObjBean.ServiceBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fenlei_right_rv, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getImgurl()).into(holder.iv);
        holder.tvName.setText(data.get(position).getServicename());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ServiceDetailsActivity.class);
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

        private ImageView iv;
        private TextView tvName;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }

}
