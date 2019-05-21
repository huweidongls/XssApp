package com.jingna.xssapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.TransactionDetailsBean;
import com.jingna.xssapp.page.ConsumptionDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/5/13.
 */

public class ConsumptionRecordsAdapter extends RecyclerView.Adapter<ConsumptionRecordsAdapter.ViewHolder> {

    private Context context;
    private List<TransactionDetailsBean.ObjBean> data;

    public ConsumptionRecordsAdapter(List<TransactionDetailsBean.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_consumption_records, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvType.setText(data.get(position).getTypename());
        holder.tvTime.setText(data.get(position).getAddtime());
        holder.tvPrice.setText(Double.valueOf(data.get(position).getPrice())+"元");
        if(data.get(position).getRadio().equals("0")){
            holder.tvPayType.setText("未支付");
        }else if(data.get(position).getRadio().equals("1")){
            holder.tvPayType.setText("支付成功");
        }

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ConsumptionDetailsActivity.class);
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
        private TextView tvType;
        private TextView tvTime;
        private TextView tvPrice;
        private TextView tvPayType;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            tvType = itemView.findViewById(R.id.tv_type);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvPayType = itemView.findViewById(R.id.tv_pay_type);
        }
    }

}
