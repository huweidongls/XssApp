package com.jingna.xssapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.MemberMessageBean;

import java.util.List;

/**
 * Created by Administrator on 2019/5/13.
 */

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.ViewHolder> {

    private Context context;
    private List<MemberMessageBean.ObjBean> data;

    public MessageAdapter(List<MemberMessageBean.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_message, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        holder.tvTime.setText(data.get(position).getAddtime());
        holder.tvName.setText(data.get(position).getTypename());
        holder.tvText.setText(data.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout ll;
        private TextView tvTime;
        private TextView tvName;
        private TextView tvText;

        public ViewHolder(View itemView) {
            super(itemView);
            ll = itemView.findViewById(R.id.ll);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvName = itemView.findViewById(R.id.tv_name);
            tvText = itemView.findViewById(R.id.tv_text);
        }
    }

}
