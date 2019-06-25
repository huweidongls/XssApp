package com.jingna.xssapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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
    private String isMore;

    public PopBookingOrderNumAdapter(List<PreAboutBean.ObjBean.ServicePriceBean> data, String isMore, ClickListener listener) {
        this.data = data;
        this.listener = listener;
        this.isMore = isMore;
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
                if(isMore.equals("0")){
                    listener.onItemClick(data.get(position));
                }else if(isMore.equals("1")){
                    if(data.get(position).getIsSelect() == 0){
                        data.get(position).setIsSelect(1);
                        Glide.with(context).load(R.mipmap.zhifu_select).into(holder.iv);
                    }else {
                        data.get(position).setIsSelect(0);
                        Glide.with(context).load(R.mipmap.zhifu_unselect).into(holder.iv);
                    }
                }
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
        private ImageView iv;

        public ViewHolder(View itemView) {
            super(itemView);
            rl = itemView.findViewById(R.id.rl);
            tv = itemView.findViewById(R.id.tv);
            iv = itemView.findViewById(R.id.iv);
        }
    }

    public interface ClickListener{
        void onItemClick(PreAboutBean.ObjBean.ServicePriceBean bean);
    }

}
