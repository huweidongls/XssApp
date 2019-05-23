package com.jingna.xssapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.WokerContentBean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */

public class ServicePersonnelDetailsTrainAdapter extends RecyclerView.Adapter<ServicePersonnelDetailsTrainAdapter.ViewHolder> {

    private Context context;
    private List<WokerContentBean.ObjBean.TrainBean> data;

    public ServicePersonnelDetailsTrainAdapter(List<WokerContentBean.ObjBean.TrainBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_service_personnel_details_train, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getOrganizationname());
        holder.tvTime.setText(data.get(position).getStart_time()+"-"+data.get(position).getEnd_time());
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tvTime;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
        }
    }

}
