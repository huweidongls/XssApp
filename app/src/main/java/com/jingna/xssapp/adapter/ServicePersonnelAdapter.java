package com.jingna.xssapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.jingna.xssapp.R;
import com.jingna.xssapp.page.ServicePersonnelDetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/22.
 */

public class ServicePersonnelAdapter extends RecyclerView.Adapter<ServicePersonnelAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public ServicePersonnelAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_servicepersonnel, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        ServicePersonnelItemAdapter itemAdapter = new ServicePersonnelItemAdapter(list);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        holder.recyclerView.setLayoutManager(manager);
        holder.recyclerView.setAdapter(itemAdapter);

        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, ServicePersonnelDetailsActivity.class);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private RecyclerView recyclerView;
        private LinearLayout ll;

        public ViewHolder(View itemView) {
            super(itemView);
            recyclerView = itemView.findViewById(R.id.rv);
            ll = itemView.findViewById(R.id.ll);
        }
    }

}
