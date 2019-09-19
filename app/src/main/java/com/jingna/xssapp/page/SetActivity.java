package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.VersionBean;
import com.jingna.xssapp.dialog.CustomDialog;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.SpUtils;
import com.jingna.xssapp.util.ToastUtil;
import com.jingna.xssapp.util.VersionUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {

    private Context context = SetActivity.this;

    @BindView(R.id.tv_version)
    TextView tvVersion;

    private String versionCode = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        ButterKnife.bind(SetActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.get_version)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.get_version))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                VersionBean bean = gson.fromJson(data, VersionBean.class);
                                tvVersion.setText("当前版本 V"+bean.getObj().getVersion());
                                versionCode = bean.getObj().getContent();
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

    @OnClick({R.id.rl_back, R.id.rl_about, R.id.btn_exit, R.id.ll_version})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_about:
                intent.setClass(context, AboutActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_exit:
                CustomDialog customDialog = new CustomDialog(context, "是否退出登录", new CustomDialog.ClickListener() {
                    @Override
                    public void onSure() {
                        SpUtils.clear(context);
                        finish();
                    }

                    @Override
                    public void onCancel() {

                    }
                });
                customDialog.show();
                break;
            case R.id.ll_version:
                int code = Integer.valueOf(versionCode);
                int curCode = VersionUtils.packageCode(context);
                if(curCode<code){
                    ToastUtil.showShort(context, "有新版本，请去应用商店下载！");
                }else {
                    ToastUtil.showShort(context, "已是最新版本！");
                }
                break;
        }
    }

}
