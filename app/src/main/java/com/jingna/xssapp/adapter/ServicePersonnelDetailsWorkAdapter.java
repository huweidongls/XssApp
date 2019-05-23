package com.jingna.xssapp.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.WokerContentBean;
import com.jingna.xssapp.util.Base64Utils;
import com.jingna.xssapp.util.HtmlFromUtils;

import java.util.List;

/**
 * Created by Administrator on 2019/5/23.
 */

public class ServicePersonnelDetailsWorkAdapter extends RecyclerView.Adapter<ServicePersonnelDetailsWorkAdapter.ViewHolder> {

    private Context context;
    private List<WokerContentBean.ObjBean.ExperienceBean> data;
    private Activity activity;

    public ServicePersonnelDetailsWorkAdapter(List<WokerContentBean.ObjBean.ExperienceBean> data, Activity activity) {
        this.data = data;
        this.activity = activity;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_personnelservice_details_work, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(data.get(position).getCompanyname());
        holder.tvTime.setText(data.get(position).getStart_time()+"-"+data.get(position).getEnd_time());
        holder.tvPosition.setText(data.get(position).getPosition());
        String description = data.get(position).getJobdescription();
        description = Base64Utils.setDecrypt(description);
        HtmlFromUtils.setTextFromHtml(activity, holder.tvDescription, description);
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvName;
        private TextView tvTime;
        private TextView tvPosition;
        private TextView tvDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvPosition = itemView.findViewById(R.id.tv_position);
            tvDescription = itemView.findViewById(R.id.tv_description);
        }
    }

}
