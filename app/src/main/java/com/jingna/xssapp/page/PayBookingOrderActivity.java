package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.net.NetUrl;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import java.util.Map;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayBookingOrderActivity extends BaseActivity {

    private Context context = PayBookingOrderActivity.this;

    private Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_booking_order);

        map = (Map<String, String>) getIntent().getSerializableExtra("map");
        ButterKnife.bind(PayBookingOrderActivity.this);

    }

    private void initData() {

        ViseHttp.POST(NetUrl.order_insertUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.order_insertUrl))
                .addParams(map)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {

                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

    }

    @OnClick({R.id.rl_back, R.id.btn_to_pay})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_to_pay:
                initData();
//                intent.setClass(context, BookingOrderResultActivity.class);
//                startActivity(intent);
                break;
        }
    }

}
