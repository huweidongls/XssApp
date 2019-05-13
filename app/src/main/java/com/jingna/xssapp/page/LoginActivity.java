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
import com.jingna.xssapp.R;
import com.jingna.xssapp.app.MyApplication;
import com.jingna.xssapp.base.BaseActivity;

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

    private boolean isShowPwd = false;
    private boolean isShowRegisterPwd = false;
    private int type = 1;

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
    R.id.ll_type_register, R.id.tv_forgot_pwd})
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
                break;
        }
    }

}
