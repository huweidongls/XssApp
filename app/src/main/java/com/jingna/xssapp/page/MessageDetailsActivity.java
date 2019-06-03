package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.MessageDetailsAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.OrderContentBean;
import com.jingna.xssapp.net.NetUrl;
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

    private MessageDetailsAdapter adapter;
    private List<String> mList;

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
                                tvRemarks.setText(bean.getObj().getRemarks());
                                String radio = bean.getObj().getRadio();
                                if(radio.equals("0")){
                                    tvOrderType.setText("未支付");
                                }else if(radio.equals("1")){
                                    tvOrderType.setText("未派单");
                                }else if(radio.equals("2")){
                                    tvOrderType.setText("派单成功");
                                }else if(radio.equals("3")){
                                    tvOrderType.setText("服务开始");
                                }else if(radio.equals("4")){
                                    tvOrderType.setText("服务结束");
                                }else if(radio.equals("5")){
                                    tvOrderType.setText("已评价");
                                }else if(radio.equals("6")){
                                    tvOrderType.setText("已退款");
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

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
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
