package com.jingna.xssapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.ServiceTypeAndServiceListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/10.
 */

public class FenleiRightAdapter extends RecyclerView.Adapter<FenleiRightAdapter.ViewHolder> {

    private Context context;
    private List<ServiceTypeAndServiceListBean.ObjBean> data;

    public FenleiRightAdapter(List<ServiceTypeAndServiceListBean.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_fenlei_right, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvTitle.setText(data.get(position).getTypename());

        List<ServiceTypeAndServiceListBean.ObjBean.ServiceBean> list = data.get(position).getService();
        FenleiRightRvAdapter rvAdapter = new FenleiRightRvAdapter(list);
        GridLayoutManager manager = new GridLayoutManager(context, 3);
        holder.rv.setLayoutManager(manager);
        holder.rv.setAdapter(rvAdapter);

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView rv;
        private TextView tvTitle;

        public ViewHolder(View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }

}
