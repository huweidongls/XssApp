package com.jingna.xssapp.page;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.xssapp.MainActivity;
import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.WxPayBean;
import com.jingna.xssapp.broadcastreceiver.MyReceiver;
import com.jingna.xssapp.broadcastreceiver.PayReceiver;
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
    @BindView(R.id.iv_wx)
    ImageView ivWx;
    @BindView(R.id.iv_zfb)
    ImageView ivZfb;

    private Map<String, String> map;
    private IWXAPI api;

    private static final int SDK_PAY_FLAG = 1;
    private String payType = "1";

    private PayReceiver receiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_booking_order);

        api = WXAPIFactory.createWXAPI(PayBookingOrderActivity.this, null);
        api.registerApp(WXShare.APP_ID);
        map = (Map<String, String>) getIntent().getSerializableExtra("map");
        ButterKnife.bind(PayBookingOrderActivity.this);
        receiver = new PayReceiver(PayBookingOrderActivity.this, context);
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.jingna.xss.payweixin");
        registerReceiver(receiver, filter);
        initData();

    }

    private void initData() {

        tvPrice.setText(map.get("price"));

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    private void pay() {

        map.put("pay_type", payType);
        map.put("city", SpUtils.getCityId(context));
        ViseHttp.POST(NetUrl.order_insertUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.order_insertUrl))
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
                            }else if(jsonObject.optInt("code") == 300){
                                String s = jsonObject.optString("obj");
                                aliPay(s);
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

    @OnClick({R.id.rl_back, R.id.btn_to_pay, R.id.rl_wx, R.id.rl_zfb})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_to_pay:
//                ToastUtil.showShort(context, SpUtils.getCityId(context));
                pay();
//                intent.setClass(context, BookingOrderResultActivity.class);
//                startActivity(intent);
                break;
            case R.id.rl_wx:
                payType = "1";
                Glide.with(context).load(R.mipmap.zhifu_select).into(ivWx);
                Glide.with(context).load(R.mipmap.zhifu_unselect).into(ivZfb);
                break;
            case R.id.rl_zfb:
                payType = "2";
                Glide.with(context).load(R.mipmap.zhifu_unselect).into(ivWx);
                Glide.with(context).load(R.mipmap.zhifu_select).into(ivZfb);
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

    public void aliPay(String info) {
        final String orderInfo = info;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(PayBookingOrderActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    Map<String, String> result = (Map<String, String>) msg.obj;
                    if(result.get("resultStatus").equals("9000")){
                        Toast.makeText(context, "支付成功", Toast.LENGTH_SHORT).show();
                        sendBroadcast(new Intent("com.jingna.xss.PAY_SUCCESS"));
                        PayBookingOrderActivity.this.finish();
                    }else {
                        sendBroadcast(new Intent("com.jingna.xss.PAY_SUCCESS"));
                        PayBookingOrderActivity.this.finish();
                    }
                    break;
            }
        }

    };

//    public class MyRecever extends BroadcastReceiver {
//        @Override
//        public void onReceive(Context context, Intent intent) {
//            finish();
//        }
//    }

}
