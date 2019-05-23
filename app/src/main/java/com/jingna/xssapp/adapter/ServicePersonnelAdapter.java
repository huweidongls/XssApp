package com.jingna.xssapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.WokerListBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.page.ServicePersonnelDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/22.
 */

public class ServicePersonnelAdapter extends RecyclerView.Adapter<ServicePersonnelAdapter.ViewHolder> {

    private Context context;
    private List<WokerListBean.ObjBean> data;

    public ServicePersonnelAdapter(List<WokerListBean.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_servicepersonnel, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getHeadimg()).into(holder.ivAvatar);
        holder.tvName.setText(data.get(position).getName());
        holder.tvServiceNum.setText("服务次数："+data.get(position).getServicenum()+"次");
        holder.tvAge.setText(data.get(position).getAge()+"岁");

        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        ServicePersonnelItemAdapter itemAdapter = new ServicePersonnelItemAdapter(list);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerView.setLayoutManager(manager);
        holder.recyclerView.setAdapter(itemAdapter);

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ServicePersonnelDetailsActivity.class);
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

        private RecyclerView recyclerView;
        private LinearLayout ll;
        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvAge;
        private TextView tvServiceNum;

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv);
            ll = itemView.findViewById(R.id.ll);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvAge = itemView.findViewById(R.id.tv_age);
            tvServiceNum = itemView.findViewById(R.id.tv_service_num);
        }
    }

}
