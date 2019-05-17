package com.jingna.xssapp.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.xssapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/17.
 */

public class ServiceDetailsCommentAdapter extends RecyclerView.Adapter<ServiceDetailsCommentAdapter.ViewHolder> {

    private Context context;
    private List<String> data;

    public ServiceDetailsCommentAdapter(List<String> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_service_details_comment, parent, false);
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
        CommentPicAdapter picAdapter = new CommentPicAdapter(list);
        GridLayoutManager manager = new GridLayoutManager(context, 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        holder.rv.setLayoutManager(manager);
        holder.rv.setAdapter(picAdapter);
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
