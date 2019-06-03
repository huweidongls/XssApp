package com.jingna.xssapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.MemberAddressListBean;
import com.jingna.xssapp.dialog.CustomDialog;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.page.InsertAddressActivity;
import com.jingna.xssapp.util.Logger;
import com.jingna.xssapp.util.ToastUtil;
import com.jingna.xssapp.util.TokenUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2019/5/13.
 */

public class MyAddressAdapter extends RecyclerView.Adapter<MyAddressAdapter.ViewHolder> {

    private Context context;
    private List<MemberAddressListBean.ObjBean> data;
    private ClickListener listener;
    private int order;

    public MyAddressAdapter(List<MemberAddressListBean.ObjBean> data, ClickListener listener, int order) {
        this.data = data;
        this.listener = listener;
        this.order = order;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_my_address, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.tvAddress.setText(data.get(position).getAddress()+data.get(position).getDetailedaddress());
        holder.tvName.setText(data.get(position).getName());
        holder.tvPhone.setText(data.get(position).getTel());
        if(data.get(position).getRadio().equals("0")){
            Glide.with(context).load("#ffffff").into(holder.ivMoren);
        }else if(data.get(position).getRadio().equals("1")){
            Glide.with(context).load(R.mipmap.address_default).into(holder.ivMoren);
        }
        holder.rlDel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(context, "是否删除选中地址", new CustomDialog.ClickListener() {
                    @Override
                    public void onSure() {
                        ViseHttp.POST(NetUrl.delMember_AddressUrl)
                                .addParam("app_key", TokenUtils.getToken(NetUrl.BASE_URL+NetUrl.delMember_AddressUrl))
                                .addParam("id", data.get(position).getId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String d) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(d);
                                            if(jsonObject.optInt("code") == 200){
                                                ToastUtil.showShort(context, jsonObject.optString("message"));
                                                data.remove(position);
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
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                customDialog.show();
            }
        });
        holder.rlEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("id", data.get(position).getId());
                intent.putExtra("type", "编辑地址");
                intent.setClass(context, InsertAddressActivity.class);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(order == 1){
                    listener.onItemClick(position);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvAddress;
        private TextView tvName;
        private TextView tvPhone;
        private ImageView ivMoren;
        private RelativeLayout rlDel;
        private RelativeLayout rlEdit;

        public ViewHolder(View itemView) {
            super(itemView);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvName = itemView.findViewById(R.id.tv_name);
            tvPhone = itemView.findViewById(R.id.tv_phone);
            ivMoren = itemView.findViewById(R.id.iv_moren);
            rlDel = itemView.findViewById(R.id.rl_del);
            rlEdit = itemView.findViewById(R.id.rl_edit);
        }
    }

    public interface ClickListener{
        void onItemClick(int pos);
    }

}
