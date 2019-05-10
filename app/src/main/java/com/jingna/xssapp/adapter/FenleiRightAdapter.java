package com.jingna.xssapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.xssapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/10.
 */

public class FenleiRightAdapter extends RecyclerView.Adapter<FenleiRightAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public FenleiRightAdapter(List<String> data) {
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
        List<String> list = new ArrayList<>();
        list.add("");
        list.add("");
        list.add("");
        list.add("");
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

        public ViewHolder(View itemView) {
            super(itemView);
            rv = itemView.findViewById(R.id.rv);
        }
    }

}
