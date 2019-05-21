package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.app.MyApplication;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.CodeBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.Logger;
import com.jingna.xssapp.util.StringUtils;
import com.jingna.xssapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPwdActivity extends BaseActivity {

    private Context context = ForgotPwdActivity.this;

    @BindView(R.id.tv_code)
    TextView tvGetCode;
    @BindView(R.id.et_phone)
    EditText etPhone;
    @BindView(R.id.et_code)
    EditText etCode;

    private String phone = "";
    private String code = "";

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
                phone = etPhone.getText().toString();
                if(StringUtils.isEmpty(phone)){
                    ToastUtil.showShort(context, "电话号码不能为空");
                }else if(!StringUtils.isPhoneNumberValid(phone)){
                    ToastUtil.showShort(context, "请输入正确格式的电话号码");
                }else {
                    ViseHttp.POST(NetUrl.getCodeUrl)
                            .addParam("app_key", getToken(NetUrl.BASE_URL+ NetUrl.getCodeUrl))
                            .addParam("tel", phone)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.optInt("code") == 200){
                                            Logger.e("123123", data);
                                            Gson gson = new Gson();
                                            CodeBean codeBean = gson.fromJson(data, CodeBean.class);
                                            code = codeBean.getObj().getCode();
                                            ToastUtil.showShort(context, jsonObject.optString("message"));
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
                break;
            case R.id.btn_next:
                String eCode = etCode.getText().toString();
                if(eCode.equals(code)){
                    intent.setClass(context, ForgotPwd1Activity.class);
                    intent.putExtra("phone", phone);
                    startActivity(intent);
                    finish();
                }else {
                    ToastUtil.showShort(context, "验证码不正确");
                }
                break;
        }
    }

}
