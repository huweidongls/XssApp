package com.jingna.xssapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.OrderListBean;
import com.jingna.xssapp.dialog.CustomDialog;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.page.CommentOrderActivity;
import com.jingna.xssapp.page.MessageDetailsActivity;
import com.jingna.xssapp.util.ToastUtil;
import com.jingna.xssapp.util.TokenUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by Administrator on 2019/5/14.
 */

public class FragmentAllOrderAdapter extends RecyclerView.Adapter<FragmentAllOrderAdapter.ViewHolder> {

    private Context context;
    private List<OrderListBean.ObjBean> data;
    private ClickListener listener;

    public FragmentAllOrderAdapter(List<OrderListBean.ObjBean> data) {
        this.data = data;
    }

    public void setListener(ClickListener listener){
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_all_order, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        Glide.with(context).load(NetUrl.BASE_URL+data.get(position).getIco()).into(holder.iv);
        holder.tvName.setText(data.get(position).getServicename());
        String radio = data.get(position).getRadio();
        if(radio.equals("0")){
            holder.tvType.setText("未支付");
            holder.tvToPay.setVisibility(View.VISIBLE);
            holder.tvCancelOrder.setVisibility(View.VISIBLE);
            holder.tvComment.setVisibility(View.GONE);
            holder.tvCancelYuyue.setVisibility(View.GONE);
            holder.tvDelete.setVisibility(View.GONE);
            holder.tvOrderRefund.setVisibility(View.GONE);
            holder.tvSure.setVisibility(View.GONE);
        }else if(radio.equals("1")){
            holder.tvType.setText("未派单");
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvComment.setVisibility(View.GONE);
            holder.tvCancelYuyue.setVisibility(View.VISIBLE);
            holder.tvDelete.setVisibility(View.GONE);
            holder.tvOrderRefund.setVisibility(View.GONE);
            holder.tvSure.setVisibility(View.GONE);
        }else if(radio.equals("2")){
            holder.tvType.setText("已接单");
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvComment.setVisibility(View.GONE);
            holder.tvCancelYuyue.setVisibility(View.VISIBLE);
            holder.tvDelete.setVisibility(View.GONE);
            holder.tvOrderRefund.setVisibility(View.GONE);
            holder.tvSure.setVisibility(View.GONE);
        }else if(radio.equals("3")){
            holder.tvType.setText("服务开始");
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvComment.setVisibility(View.GONE);
            holder.tvCancelYuyue.setVisibility(View.GONE);
            holder.tvDelete.setVisibility(View.GONE);
            holder.tvOrderRefund.setVisibility(View.VISIBLE);
            holder.tvSure.setVisibility(View.GONE);
        }else if(radio.equals("4")){
            holder.tvType.setText("服务结束");
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvComment.setVisibility(View.GONE);
            holder.tvCancelYuyue.setVisibility(View.GONE);
            holder.tvDelete.setVisibility(View.GONE);
            holder.tvOrderRefund.setVisibility(View.VISIBLE);
            holder.tvSure.setVisibility(View.VISIBLE);
        }else if(radio.equals("5")){
            holder.tvType.setText("已评价");
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvComment.setVisibility(View.GONE);
            holder.tvCancelYuyue.setVisibility(View.GONE);
            holder.tvDelete.setVisibility(View.VISIBLE);
            holder.tvOrderRefund.setVisibility(View.GONE);
            holder.tvSure.setVisibility(View.GONE);
        }else if(radio.equals("6")){
            holder.tvType.setText("已退款");
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvComment.setVisibility(View.GONE);
            holder.tvCancelYuyue.setVisibility(View.GONE);
            holder.tvDelete.setVisibility(View.VISIBLE);
            holder.tvOrderRefund.setVisibility(View.GONE);
            holder.tvSure.setVisibility(View.GONE);
        }else if(radio.equals("7")){
            holder.tvType.setText("退款中");
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvComment.setVisibility(View.GONE);
            holder.tvCancelYuyue.setVisibility(View.GONE);
            holder.tvDelete.setVisibility(View.GONE);
            holder.tvOrderRefund.setVisibility(View.GONE);
            holder.tvSure.setVisibility(View.GONE);
        }else if(radio.equals("8")){
            holder.tvType.setText("退款成功");
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvComment.setVisibility(View.GONE);
            holder.tvCancelYuyue.setVisibility(View.GONE);
            holder.tvDelete.setVisibility(View.VISIBLE);
            holder.tvOrderRefund.setVisibility(View.GONE);
            holder.tvSure.setVisibility(View.GONE);
        }else if(radio.equals("9")){
            holder.tvType.setText("确认完成");
            holder.tvToPay.setVisibility(View.GONE);
            holder.tvCancelOrder.setVisibility(View.GONE);
            holder.tvComment.setVisibility(View.VISIBLE);
            holder.tvCancelYuyue.setVisibility(View.GONE);
            holder.tvDelete.setVisibility(View.GONE);
            holder.tvOrderRefund.setVisibility(View.GONE);
            holder.tvSure.setVisibility(View.GONE);
        }

        holder.tvOrderId.setText("订单编号："+data.get(position).getOrder_code());
        holder.tvTime.setText("预约时间："+data.get(position).getPretime());
        holder.tvAddress.setText("服务地址："+data.get(position).getAddress());

        holder.tvSure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(context, "是否确认服务完成", new CustomDialog.ClickListener() {
                    @Override
                    public void onSure() {
                        ViseHttp.POST(NetUrl.orderCompleteUrl)
                                .addParam("app_key", TokenUtils.getToken(NetUrl.BASE_URL+NetUrl.orderCompleteUrl))
                                .addParam("oid", data.get(position).getId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String d) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(d);
                                            if(jsonObject.optInt("code") == 200){
                                                ToastUtil.showShort(context, "服务确认成功");
                                                data.get(position).setRadio("9");
                                                notifyDataSetChanged();
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

        holder.tvOrderRefund.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(context, "是否发起退款申请", new CustomDialog.ClickListener() {
                    @Override
                    public void onSure() {
                        ViseHttp.POST(NetUrl.order_RefundUrl)
                                .addParam("app_key", TokenUtils.getToken(NetUrl.BASE_URL+NetUrl.order_RefundUrl))
                                .addParam("oid", data.get(position).getId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String d) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(d);
                                            if(jsonObject.optInt("code") == 200){
                                                ToastUtil.showShort(context, "成功发起退款申请");
                                                data.get(position).setRadio("7");
                                                notifyDataSetChanged();
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
        holder.ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, MessageDetailsActivity.class);
                intent.putExtra("id", data.get(position).getId());
                context.startActivity(intent);
            }
        });
        holder.tvComment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(context, CommentOrderActivity.class);
                intent.putExtra("id", data.get(position).getId());
                context.startActivity(intent);
            }
        });
        holder.tvToPay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onPay(position);
            }
        });
        holder.tvCancelYuyue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(context, "是否取消预约订单", new CustomDialog.ClickListener() {
                    @Override
                    public void onSure() {
                        ViseHttp.POST(NetUrl.orderRefundUrl)
                                .addParam("app_key", TokenUtils.getToken(NetUrl.BASE_URL+NetUrl.orderRefundUrl))
                                .addParam("oid", data.get(position).getId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String d) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(d);
                                            if(jsonObject.optInt("code") == 200){
                                                ToastUtil.showShort(context, "订单取消成功");
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
        holder.tvCancelOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(context, "是否取消订单", new CustomDialog.ClickListener() {
                    @Override
                    public void onSure() {
                        ViseHttp.POST(NetUrl.cancel_orderUrl)
                                .addParam("app_key", TokenUtils.getToken(NetUrl.BASE_URL+NetUrl.cancel_orderUrl))
                                .addParam("oid", data.get(position).getId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String d) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(d);
                                            if(jsonObject.optInt("code") == 200){
                                                ToastUtil.showShort(context, "订单取消成功");
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
        holder.tvDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialog customDialog = new CustomDialog(context, "是否删除订单", new CustomDialog.ClickListener() {
                    @Override
                    public void onSure() {
                        ViseHttp.POST(NetUrl.cancel_orderUrl)
                                .addParam("app_key", TokenUtils.getToken(NetUrl.BASE_URL+NetUrl.cancel_orderUrl))
                                .addParam("oid", data.get(position).getId())
                                .request(new ACallback<String>() {
                                    @Override
                                    public void onSuccess(String d) {
                                        try {
                                            JSONObject jsonObject = new JSONObject(d);
                                            if(jsonObject.optInt("code") == 200){
                                                ToastUtil.showShort(context, "订单删除成功");
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
    }

    @Override
    public int getItemCount() {
        return data == null ? 0 : data.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tvDelete;
        private LinearLayout ll;
        private ImageView iv;
        private TextView tvName;
        private TextView tvType;
        private TextView tvOrderId;
        private TextView tvTime;
        private TextView tvAddress;
        private TextView tvToPay;
        private TextView tvCancelOrder;
        private TextView tvComment;
        private TextView tvCancelYuyue;
        private TextView tvOrderRefund;
        private TextView tvSure;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDelete = itemView.findViewById(R.id.tv_delete);
            ll = itemView.findViewById(R.id.ll);
            iv = itemView.findViewById(R.id.iv);
            tvName = itemView.findViewById(R.id.tv_name);
            tvType = itemView.findViewById(R.id.tv_type);
            tvOrderId = itemView.findViewById(R.id.tv_order_id);
            tvTime = itemView.findViewById(R.id.tv_time);
            tvAddress = itemView.findViewById(R.id.tv_address);
            tvToPay = itemView.findViewById(R.id.tv_to_pay);
            tvCancelOrder = itemView.findViewById(R.id.tv_cancel_order);
            tvComment = itemView.findViewById(R.id.tv_comment);
            tvCancelYuyue = itemView.findViewById(R.id.tv_cancel_yuyue);
            tvOrderRefund = itemView.findViewById(R.id.tv_order_refund);
            tvSure = itemView.findViewById(R.id.tv_sure);
        }
    }

    public interface ClickListener{
        void onPay(int pos);
    }

}
