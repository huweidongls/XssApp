package com.jingna.xssapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.PreAboutBean;
import com.jingna.xssapp.dialog.NumDialog;

import java.util.List;

/**
 * Created by Administrator on 2019/6/25.
 */

public class BookingOrderAdapter extends RecyclerView.Adapter<BookingOrderAdapter.ViewHolder> {

    private Context context;
    private List<PreAboutBean.ObjBean.ServicePriceBean> data;
    private ClickListener listener;

    public BookingOrderAdapter(List<PreAboutBean.ObjBean.ServicePriceBean> data, ClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_booking_order, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvNum.setText(null);
        holder.tvNumName.setText(data.get(position).getTollitem()+"：");
        holder.tvNumDanwei.setText(data.get(position).getText());
        holder.rlNumContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumDialog numDialog = new NumDialog(context, new NumDialog.ClickListener() {
                    @Override
                    public void onClick(String num) {
                        holder.tvNum.setText(num);
                        double price = Double.valueOf(data.get(position).getPrice())*Integer.valueOf(num);
                        listener.onClick(data.get(position).getId(), num, price+"");
                    }
                });
                numDialog.show();
            }
        });
        holder.tvNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NumDialog numDialog = new NumDialog(context, new NumDialog.ClickListener() {
                    @Override
                    public void onClick(String num) {
                        holder.tvNum.setText(num);
                        double price = Double.valueOf(data.get(position).getPrice())*Integer.valueOf(num);
                        listener.onClick(data.get(position).getId(), num, price+"");
                    }
                });
                numDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvNumName;
        private TextView tvNum;
        private TextView tvNumDanwei;
        private RelativeLayout rlNumContent;

        public ViewHolder(View itemView) {
            super(itemView);
            tvNumName = itemView.findViewById(R.id.tv_num_name);
            tvNum = itemView.findViewById(R.id.tv_num);
            tvNumDanwei = itemView.findViewById(R.id.tv_num_danwei);
            rlNumContent = itemView.findViewById(R.id.rl_num_content);
        }
    }

    public interface ClickListener{
        void onClick(String id, String num, String price);
    }

}
