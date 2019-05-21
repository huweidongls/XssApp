package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.StringUtils;
import com.jingna.xssapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ForgotPwd1Activity extends BaseActivity {

    private Context context = ForgotPwd1Activity.this;

    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.iv_is_show_pwd)
    ImageView ivIsShowPwd;
    @BindView(R.id.et_old_pwd)
    EditText etOldPwd;

    private String phone = "";

    private boolean isShowPwd = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pwd1);

        phone = getIntent().getStringExtra("phone");
        ButterKnife.bind(ForgotPwd1Activity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_is_show_pwd, R.id.btn_sure})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_is_show_pwd:
                if(isShowPwd){
                    isShowPwd = false;
                    Glide.with(context).load(R.mipmap.bukejian).into(ivIsShowPwd);
                    etPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                }else {
                    isShowPwd = true;
                    Glide.with(context).load(R.mipmap.kejian).into(ivIsShowPwd);
                    etPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etPwd.setSelection(etPwd.getText().length());
                }
                break;
            case R.id.btn_sure:
                String oldPwd = etOldPwd.getText().toString();
                String pwd = etPwd.getText().toString();
                if(StringUtils.isEmpty(oldPwd)||StringUtils.isEmpty(pwd)){
                    ToastUtil.showShort(context, "密码不能为空");
                }else if(!oldPwd.equals(pwd)){
                    ToastUtil.showShort(context, "新旧密码不一致");
                }else {
                    ViseHttp.POST(NetUrl.forgetPwdUrl)
                            .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.forgetPwdUrl))
                            .addParam("tel", phone)
                            .addParam("x_password", pwd)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.optInt("code") == 200){
                                            ToastUtil.showShort(context, jsonObject.optString("message"));
                                            finish();
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
        }
    }

}
