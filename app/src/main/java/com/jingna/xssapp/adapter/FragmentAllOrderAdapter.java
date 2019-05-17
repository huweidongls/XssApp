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
import com.jingna.xssapp.dialog.CustomDialog;
import com.jingna.xssapp.page.OrderDetailsActivity;

import java.util.List;

/**
 * Created by Administrator on 2019/5/14.
 */

public class FragmentAllOrderAdapter extends RecyclerView.Adapter<FragmentAllOrderAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public FragmentAllOrderAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_all_order, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, OrderDetailsActivity.class);
                context.startActivity(intent);
            }
        });
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(context, "是否删除订单", new CustomDialog.ClickListener() {
                    @Override
                    public void onSure() {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
                customDialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDelete;
        private LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDelete = itemView.findViewById(R.id.tv_delete);
            ll = itemView.findViewById(R.id.ll);
        }
    }

}
