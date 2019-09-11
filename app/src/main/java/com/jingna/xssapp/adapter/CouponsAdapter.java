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
import com.jingna.xssapp.bean.CouponListBean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/15.
 */

public class CouponsAdapter extends RecyclerView.Adapter<CouponsAdapter.ViewHolder> {

    private Context context;
    private List<CouponListBean.ObjBean> data;
    private ClickListener listener;
    public CouponsAdapter(List<CouponListBean.ObjBean> data,ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_coupons, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvPrice.setText(data.get(position).getMoney());
        holder.tvType.setText(data.get(position).getDecript());
        holder.tvName.setText(data.get(position).getDiscountname());
        holder.tvTime.setText("有效期至："+data.get(position).getTermofvalidity());
        holder.tvText.setText(data.get(position).getText());
        holder.ivRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.tvText.getVisibility() == View.GONE){
                    holder.tvText.setVisibility(View.VISIBLE);
                    Glide.with(context).load(R.mipmap.round_bottom).into(holder.ivRight);
                }else if(holder.tvText.getVisibility() == View.VISIBLE){
                    holder.tvText.setVisibility(View.GONE);
                    Glide.with(context).load(R.mipmap.round_right).into(holder.ivRight);
                }
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPayOrder(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvPrice;
        private TextView tvType;
        private TextView tvName;
        private TextView tvTime;
        private ImageView ivRight;
        private TextView tvText;

        public ViewHolder(View itemView) {
            super(itemView);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvType = itemView.findViewById(R.id.tv_type);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            ivRight = itemView.findViewById(R.id.iv_right);
            tvText = itemView.findViewById(R.id.tv_text);
        }
    }
    public interface ClickListener{
        void onPayOrder(int pos);
    }
}
