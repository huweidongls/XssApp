package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.app.MyApplication;
import com.jingna.xssapp.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPwdActivity extends BaseActivity {

    private Context context = ForgotPwdActivity.this;

    @BindView(R.id.tv_code)
    TextView tvGetCode;

    public TextView getCode_btn() {
        return tvGetCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd);

        ButterKnife.bind(ForgotPwdActivity.this);
        MyApplication.forgotTimeCount.setActivity(ForgotPwdActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_get_code, R.id.btn_next})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_get_code:
                MyApplication.forgotTimeCount.start();
                break;
            case R.id.btn_next:
                intent.setClass(context, ForgotPwd1Activity.class);
                startActivity(intent);
                break;
        }
    }

}
