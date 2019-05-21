package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.MyAddressAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.MemberAddressListBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.SpUtils;
import com.jingna.xssapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAddressActivity extends BaseActivity {

    private Context context = MyAddressActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.ll_message_null)
    LinearLayout llMessageNull;

    private MyAddressAdapter adapter;
    private List<MemberAddressListBean.ObjBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        ButterKnife.bind(MyAddressActivity.this);

    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

        ViseHttp.POST(NetUrl.memberAddressListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.memberAddressListUrl))
                .addParam("uid", SpUtils.getUid(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                MemberAddressListBean bean = gson.fromJson(data, MemberAddressListBean.class);
                                mList = bean.getObj();
                                if(mList.size()>0){
                                    adapter = new MyAddressAdapter(mList);
                                    LinearLayoutManager manager = new LinearLayoutManager(context);
                                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                                    recyclerView.setLayoutManager(manager);
                                    recyclerView.setAdapter(adapter);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    llMessageNull.setVisibility(View.GONE);
                                }else {
                                    recyclerView.setVisibility(View.GONE);
                                    llMessageNull.setVisibility(View.VISIBLE);
                                }
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

    @OnClick({R.id.rl_back, R.id.btn_insert})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_insert:
                intent.setClass(context, InsertAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

}
