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
 * Created by Administrator on 2019/5/22.
 */

public class ServicePersonnelDetailsItemAdapter extends RecyclerView.Adapter<ServicePersonnelDetailsItemAdapter.ViewHolder> {

    private Context context;
    private List<WokerContentBean.ObjBean.TableBean> data;

    public ServicePersonnelDetailsItemAdapter(List<WokerContentBean.ObjBean.TableBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_servicepersonnel_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tv.setText(data.get(position).getType_name());
    }

    @Override
    public int getItemCount() {
        if(data == null){
            return 0;
        }else if(data.size() > 3){
            return 3;
        }else {
            return data.size();
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }

}
