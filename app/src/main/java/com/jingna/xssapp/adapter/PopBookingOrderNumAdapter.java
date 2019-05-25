package com.jingna.xssapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.PreAboutBean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/25.
 */

public class PopBookingOrderNumAdapter extends RecyclerView.Adapter<PopBookingOrderNumAdapter.ViewHolder> {

    private Context context;
    private List<PreAboutBean.ObjBean.ServicePriceBean> data;
    private ClickListener listener;

    public PopBookingOrderNumAdapter(List<PreAboutBean.ObjBean.ServicePriceBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_pop_booking_order_num, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tv.setText(data.get(position).getTollitem()+"/"+data.get(position).getPrice()+"元/"+data.get(position).getText());
        holder.rl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position, holder.tv.getText().toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RelativeLayout rl;
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            rl = itemView.findViewById(R.id.rl);
            tv = itemView.findViewById(R.id.tv);
        }
    }

    public interface ClickListener{
        void onItemClick(int pos, String content);
    }

}
