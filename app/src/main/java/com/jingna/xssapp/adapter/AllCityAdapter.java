package com.jingna.xssapp.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.AllCityBean;

import java.util.List;

import me.zhouzhuo.zzletterssidebar.adapter.BaseSortListViewAdapter;
import me.zhouzhuo.zzletterssidebar.viewholder.BaseViewHolder;

/**
 * Created by Administrator on 2019/6/21.
 */

public class AllCityAdapter extends BaseSortListViewAdapter<AllCityBean.ObjBean, AllCityAdapter.ViewHolder> {

    private Context context;
    private List<AllCityBean.ObjBean> data;

    public AllCityAdapter(Context ctx, List<AllCityBean.ObjBean> datas) {
        super(ctx, datas);
        this.context = ctx;
        this.data = datas;
    }

    @Override
    public int getLayoutId() {
        return R.layout.recyclerview_city;
    }

    @Override
    public ViewHolder getViewHolder(View view) {
        ViewHolder holder = new ViewHolder();
        holder.tvTitle = view.findViewById(R.id.tv_title);
        return holder;
    }

    @Override
    public void bindValues(ViewHolder viewHolder, int position) {
        viewHolder.tvTitle.setText(data.get(position).getArea_name());
    }

    public static class ViewHolder extends BaseViewHolder {
        protected TextView tvTitle;
    }

}
