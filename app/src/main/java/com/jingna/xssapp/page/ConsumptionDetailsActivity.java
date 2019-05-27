package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.TransactionContentBean;
import com.jingna.xssapp.net.NetUrl;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConsumptionDetailsActivity extends BaseActivity {

    private Context context = ConsumptionDetailsActivity.this;

    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.tv_radio)
    TextView tvRadio;
    @BindView(R.id.tv_pay_type)
    TextView tvPayType;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_order_sn)
    TextView tvOrderSn;
    @BindView(R.id.tv_wx_order_sn)
    TextView tvWxOrderSn;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consumption_details);

        id = getIntent().getStringExtra("id");
        ButterKnife.bind(ConsumptionDetailsActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.transaction_ContentUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.transaction_ContentUrl))
                .addParam("id", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                TransactionContentBean bean = gson.fromJson(data, TransactionContentBean.class);
                                tvPrice.setText(bean.getObj().getPrice()+"元");
                                if(bean.getObj().getRadio().equals("1")){
                                    tvRadio.setText("支付成功");
                                }else {
                                    tvRadio.setText("未支付");
                                }
                                if(bean.getObj().getPay_type().equals("1")){
                                    tvPayType.setText("微信");
                                }else if(bean.getObj().getPay_type().equals("2")){
                                    tvPayType.setText("支付宝");
                                }
                                tvTime.setText(bean.getObj().getPay_time());
                                tvOrderSn.setText(bean.getObj().getOrder_sn());
                                tvWxOrderSn.setText(bean.getObj().getWx_order_sn());
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
