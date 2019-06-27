package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.MessageDetailsAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.OrderContentBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.StringUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageDetailsActivity extends BaseActivity {

    private Context context = MessageDetailsActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_service_type)
    TextView tvServiceType;
    @BindView(R.id.tv_order_code)
    TextView tvOrderCode;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_remarks)
    TextView tvRemarks;
    @BindView(R.id.tv_order_type)
    TextView tvOrderType;
    @BindView(R.id.tv_shangmen_time)
    TextView tvShangmenTime;
    @BindView(R.id.tv_start_time)
    TextView tvStartTime;
    @BindView(R.id.tv_end_time)
    TextView tvEndTime;
    @BindView(R.id.ll_worker)
    LinearLayout llWorker;

    private MessageDetailsAdapter adapter;
    private List<OrderContentBean.ObjBean.UserBean> mList;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_details);

        id = getIntent().getStringExtra("id");
        ButterKnife.bind(MessageDetailsActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.order_contentUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.order_contentUrl))
                .addParam("oid", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                OrderContentBean bean = gson.fromJson(data, OrderContentBean.class);
                                tvServiceType.setText(bean.getObj().getService_type());
                                tvOrderCode.setText(bean.getObj().getOrder_code());
                                tvTime.setText(bean.getObj().getAddtime());
                                tvAddress.setText(bean.getObj().getAddress());
                                tvPrice.setText("￥"+bean.getObj().getPrice());
                                if(StringUtils.isEmpty(bean.getObj().getRemarks())){
                                    tvRemarks.setText("无");
                                }else {
                                    tvRemarks.setText(bean.getObj().getRemarks());
                                }
                                String radio = bean.getObj().getRadio();
                                if(radio.equals("0")){
                                    tvOrderType.setText("订单未支付");
                                }else if(radio.equals("1")){
                                    tvOrderType.setText("订单未派单");
                                }else if(radio.equals("2")){
                                    tvOrderType.setText("订单派单成功");
                                }else if(radio.equals("3")){
                                    tvOrderType.setText("订单服务开始");
                                }else if(radio.equals("4")){
                                    tvOrderType.setText("订单服务结束");
                                }else if(radio.equals("5")){
                                    tvOrderType.setText("订单已评价");
                                }else if(radio.equals("6")){
                                    tvOrderType.setText("订单已退款");
                                }else if(radio.equals("7")){
                                    tvOrderType.setText("订单退款中");
                                }else if(radio.equals("8")){
                                    tvOrderType.setText("订单退款成功");
                                }
                                //工人列表
                                mList = bean.getObj().getUser();
                                if(mList.size()>0){
                                    llWorker.setVisibility(View.VISIBLE);
                                    tvShangmenTime.setText(bean.getObj().getPretime());
                                    tvStartTime.setText(bean.getObj().getService_start_time());
                                    tvEndTime.setText(bean.getObj().getService_end_time());
                                    adapter = new MessageDetailsAdapter(mList);
                                    LinearLayoutManager manager = new LinearLayoutManager(context){
                                        @Override
                                        public boolean canScrollVertically() {
                                            return false;
                                        }
                                    };
                                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                                    recyclerView.setLayoutManager(manager);
                                    recyclerView.setAdapter(adapter);
                                }else {
                                    llWorker.setVisibility(View.GONE);
                                    tvShangmenTime.setText(bean.getObj().getPretime());
                                }
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

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
