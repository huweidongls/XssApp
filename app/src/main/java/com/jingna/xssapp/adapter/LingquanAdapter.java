package com.jingna.xssapp.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.LingquanBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.SpUtils;
import com.jingna.xssapp.util.ToastUtil;
import com.jingna.xssapp.util.TokenUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2019/5/15.
 */

public class LingquanAdapter extends RecyclerView.Adapter<LingquanAdapter.ViewHolder> {

    private Context context;
    private List<LingquanBean.ObjBean> data;

    public LingquanAdapter(List<LingquanBean.ObjBean> data) {
        this.data = data;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_lingquan, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        holder.tvTitle.setText(data.get(position).getDiscountname());
        holder.tvText.setText(data.get(position).getText());
        holder.tvTime.setText("有效期："+data.get(position).getTermofvalidity());
        holder.tvPrice.setText(data.get(position).getMoney());
        holder.tvDecript.setText(data.get(position).getDecript());
        String radio = data.get(position).getRadio();
        if(radio.equals("0")){
            holder.tvUse.setBackgroundResource(R.drawable.bg_ffffff_14dp);
            holder.tvUse.setTextColor(Color.parseColor("#F83030"));
            holder.tvUse.setText("立即领取");
        }else if(radio.equals("1")){
            holder.tvUse.setBackgroundResource(R.drawable.bg_999999_14dp);
            holder.tvUse.setTextColor(Color.parseColor("#333333"));
            holder.tvUse.setText("已领取");
        }
        holder.tvUse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(data.get(position).getRadio().equals("0")){
                    ViseHttp.POST(NetUrl.get_couponsUrl)
                            .addParam("app_key", TokenUtils.getToken(NetUrl.BASE_URL+NetUrl.get_couponsUrl))
                            .addParam("uid", SpUtils.getUid(context))
                            .addParam("cid", data.get(position).getId())
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String d) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(d);
                                        if(jsonObject.optInt("code") == 200){
                                            ToastUtil.showShort(context, jsonObject.optString("message"));
                                            data.get(position).setRadio("1");
                                            notifyDataSetChanged();
                                        }else {
                                            ToastUtil.showShort(context, jsonObject.optString("message"));
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }

                                @Override
                                public void onFail(int errCode, String errMsg) {

                                }
                            });
                }else if(data.get(position).getRadio().equals("1")){

                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvTitle;
        private TextView tvText;
        private TextView tvTime;
        private TextView tvPrice;
        private TextView tvDecript;
        private TextView tvUse;

        public ViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvText = itemView.findViewById(R.id.tv_text);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvPrice = itemView.findViewById(R.id.tv_price);
            tvDecript = itemView.findViewById(R.id.tv_decript);
            tvUse = itemView.findViewById(R.id.tv_use);
        }
    }

}
