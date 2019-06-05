package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.WxPayBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.Logger;
import com.jingna.xssapp.util.SpUtils;
import com.jingna.xssapp.util.ToastUtil;
import com.jingna.xssapp.wxapi.WXShare;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayBookingOrderActivity extends BaseActivity {

    private Context context = PayBookingOrderActivity.this;

    @BindView(R.id.tv_price)
    TextView tvPrice;

    private Map<String, String> map;
    private IWXAPI api;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_booking_order);

        api = WXAPIFactory.createWXAPI(PayBookingOrderActivity.this, null);
        api.registerApp(WXShare.APP_ID);
        map = (Map<String, String>) getIntent().getSerializableExtra("map");
        ButterKnife.bind(PayBookingOrderActivity.this);
        initData();

    }

    private void initData() {

        tvPrice.setText(map.get("price"));

    }

    private void pay() {

        map.put("pay_type", "1");
        ViseHttp.POST(NetUrl.order_insertUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.order_insertUrl))
                .addParam("cid", SpUtils.getCityId(context))
                .addParams(map)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            Logger.e("123123", data);
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                WxPayBean payBean = gson.fromJson(data, WxPayBean.class);
                                wxPay(payBean.getObj());
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

    @OnClick({R.id.rl_back, R.id.btn_to_pay})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_to_pay:
                pay();
//                intent.setClass(context, BookingOrderResultActivity.class);
//                startActivity(intent);
                break;
        }
    }

    public void wxPay(WxPayBean.ObjBean model) {
        api.registerApp(WXShare.APP_ID);
        PayReq req = new PayReq();
        req.appId = model.getAppId();
        req.partnerId = model.getPartnerId();
        req.prepayId = model.getPrepayId();
        req.nonceStr = model.getNonceStr();
        req.timeStamp = model.getTimestamp() + "";
        req.packageValue = "Sign=WXPay";
        req.sign = model.getSign();
        req.extData = "app data";
        api.sendReq(req);
    }

}
