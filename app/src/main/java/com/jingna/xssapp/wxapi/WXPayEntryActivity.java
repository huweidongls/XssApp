package com.jingna.xssapp.wxapi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.util.ToastUtil;
import com.tencent.mm.opensdk.modelbase.BaseReq;
import com.tencent.mm.opensdk.modelbase.BaseResp;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.IWXAPIEventHandler;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WXPayEntryActivity extends BaseActivity implements IWXAPIEventHandler {
    private IWXAPI api;

    private RelativeLayout rlBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wxpay_entry);
        api = WXAPIFactory.createWXAPI(this, WXShare.APP_ID);
        api.registerApp(WXShare.APP_ID);
        api.handleIntent(getIntent(), this);

        initData();

    }

    private void initData() {

        rlBack = findViewById(R.id.rl_back);
        rlBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        api.handleIntent(intent, this);
    }

    @Override
    public void onReq(BaseReq baseReq) {

    }

    @Override
    public void onResp(BaseResp baseResp) {
        switch (baseResp.errCode) {
            case 0:
                ToastUtil.showShort(WXPayEntryActivity.this, "支付成功");
                break;
            case -1:
                ToastUtil.showShort(WXPayEntryActivity.this, "支付失败");
                break;
            case -2:
                break;
        }
    }
}
