package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.app.MyApplication;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.CodeBean;
import com.jingna.xssapp.bean.RegisterBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.Logger;
import com.jingna.xssapp.util.SpUtils;
import com.jingna.xssapp.util.StringUtils;
import com.jingna.xssapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    private Context context = LoginActivity.this;

    @BindView(R.id.et_pwd)
    EditText etPwd;
    @BindView(R.id.iv_is_show_pwd)
    ImageView ivIsShowPwd;
    @BindView(R.id.et_register_pwd)
    EditText etRegisterPwd;
    @BindView(R.id.iv_is_show_register_pwd)
    ImageView ivIsShowRegisterPwd;
    @BindView(R.id.tv_code)
    TextView tvGetCode;
    @BindView(R.id.ll_login)
    LinearLayout llLogin;
    @BindView(R.id.ll_register)
    LinearLayout llRegister;
    @BindView(R.id.view_login)
    View viewLogin;
    @BindView(R.id.view_register)
    View viewRegister;
    @BindView(R.id.et_register_phone)
    EditText etRegisterPhone;
    @BindView(R.id.et_register_code)
    EditText etRegisterCode;
    @BindView(R.id.et_phone)
    EditText etPhone;

    private boolean isShowPwd = false;
    private boolean isShowRegisterPwd = false;
    private int type = 1;
    private String code = "";
    private String codePhone = "";
    private String phone = "";

    public TextView getCode_btn() {
        return tvGetCode;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ButterKnife.bind(LoginActivity.this);
        MyApplication.editPwdTimeCount.setActivity(LoginActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_is_show_pwd, R.id.rl_is_show_register_pwd, R.id.rl_get_code, R.id.ll_type_login,
    R.id.ll_type_register, R.id.tv_forgot_pwd, R.id.btn_fast_register, R.id.btn_login})
    public void onClick(View view){
        Intent intent = new Intent();
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
            case R.id.rl_is_show_register_pwd:
                if(isShowRegisterPwd){
                    isShowRegisterPwd = false;
                    Glide.with(context).load(R.mipmap.bukejian).into(ivIsShowRegisterPwd);
                    etRegisterPwd.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    etRegisterPwd.setSelection(etRegisterPwd.getText().length());
                }else {
                    isShowRegisterPwd = true;
                    Glide.with(context).load(R.mipmap.kejian).into(ivIsShowRegisterPwd);
                    etRegisterPwd.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    etRegisterPwd.setSelection(etRegisterPwd.getText().length());
                }
                break;
            case R.id.rl_get_code:
                MyApplication.editPwdTimeCount.start();
                codePhone = etRegisterPhone.getText().toString();
                if(StringUtils.isPhoneNumberValid(codePhone)){
                    ViseHttp.POST(NetUrl.getCodeUrl)
                            .addParam("app_key", getToken(NetUrl.BASE_URL+ NetUrl.getCodeUrl))
                            .addParam("tel", codePhone)
                            .addParam("type", "1")
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
                }else {
                    ToastUtil.showShort(context, "请输入正确格式的手机号码");
                }
                break;
            case R.id.ll_type_login:
                viewLogin.setVisibility(View.VISIBLE);
                viewRegister.setVisibility(View.GONE);
                llLogin.setVisibility(View.VISIBLE);
                llRegister.setVisibility(View.GONE);
                break;
            case R.id.ll_type_register:
                viewLogin.setVisibility(View.GONE);
                viewRegister.setVisibility(View.VISIBLE);
                llLogin.setVisibility(View.GONE);
                llRegister.setVisibility(View.VISIBLE);
                break;
            case R.id.tv_forgot_pwd:
                intent.setClass(context, ForgotPwdActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_fast_register:
                //快速注册
                phone = etRegisterPhone.getText().toString();
                String curCode = etRegisterCode.getText().toString();
                String pwd = etRegisterPwd.getText().toString();
                if(StringUtils.isEmpty(phone)||StringUtils.isEmpty(curCode)||StringUtils.isEmpty(pwd)){
                    ToastUtil.showShort(context, "信息不能为空");
                }else if(!StringUtils.isPhoneNumberValid(phone)){
                    ToastUtil.showShort(context, "请输入正确的手机号码");
                }else if (curCode.equals(code)&&phone.equals(codePhone)){
                    ViseHttp.POST(NetUrl.registerUrl)
                            .addParam("app_key", getToken(NetUrl.BASE_URL+ NetUrl.registerUrl))
                            .addParam("tel", phone)
                            .addParam("password", pwd)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.optInt("code") == 200){
                                            Logger.e("123123", data);
                                            Gson gson = new Gson();
                                            RegisterBean registerBean = gson.fromJson(data, RegisterBean.class);
                                            SpUtils.setUid(context, registerBean.getObj().getUid());
                                            SpUtils.setPhoneNum(context, phone);
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
                }else {
                    ToastUtil.showShort(context, "验证码不正确");
                }
                break;
            case R.id.btn_login:
                //登录
                String loginPhone = etPhone.getText().toString();
                String loginPwd = etPwd.getText().toString();
                if(StringUtils.isEmpty(loginPhone)||StringUtils.isEmpty(loginPwd)){
                    ToastUtil.showShort(context, "手机号或密码不能为空");
                }else if(!StringUtils.isPhoneNumberValid(loginPhone)){
                    ToastUtil.showShort(context, "请输入正确格式的手机号码");
                }else {
                    ViseHttp.POST(NetUrl.loginUrl)
                            .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.loginUrl))
                            .addParam("user", loginPhone)
                            .addParam("password", loginPwd)
                            .request(new ACallback<String>() {
                                @Override
                                public void onSuccess(String data) {
                                    try {
                                        JSONObject jsonObject = new JSONObject(data);
                                        if(jsonObject.optInt("code") == 200){
                                            Gson gson = new Gson();
                                            RegisterBean registerBean = gson.fromJson(data, RegisterBean.class);
                                            SpUtils.setUid(context, registerBean.getObj().getUid());
                                            SpUtils.setPhoneNum(context, phone);
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
