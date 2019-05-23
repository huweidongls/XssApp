package com.jingna.xssapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.WokerContentBean;
import com.jingna.xssapp.net.NetUrl;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */

public class ServicePersonnelDetailsImgAdapter extends RecyclerView.Adapter<ServicePersonnelDetailsImgAdapter.ViewHolder> {

    private Context context;
    private List<WokerContentBean.ObjBean.ImgBean> data;

    public ServicePersonnelDetailsImgAdapter(List<WokerContentBean.ObjBean.ImgBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_service_personnel_details_img, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getImgurl()).into(holder.iv);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
        }
    }

}
