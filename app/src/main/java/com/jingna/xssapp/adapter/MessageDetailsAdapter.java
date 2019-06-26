package com.jingna.xssapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.OrderContentBean;
import com.jingna.xssapp.net.NetUrl;

import java.util.List;

/**
 * Created by Administrator on 2019/5/13.
 */

public class MessageDetailsAdapter extends RecyclerView.Adapter<MessageDetailsAdapter.ViewHolder> {

    private Context context;
    private List<OrderContentBean.ObjBean.UserBean> data;

    public MessageDetailsAdapter(List<OrderContentBean.ObjBean.UserBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_message_details, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getHeadimg()).into(holder.ivAvatar);
        holder.tvName.setText(data.get(position).getName());
        holder.tvPhoneNum.setText(data.get(position).getUser());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivAvatar;
        private TextView tvName;
        private TextView tvPhoneNum;

        public ViewHolder(View itemView) {
            super(itemView);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhoneNum = itemView.findViewById(R.id.tv_phone_num);
        }
    }

}
