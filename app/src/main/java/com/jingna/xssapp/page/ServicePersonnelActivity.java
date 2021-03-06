package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.ServicePersonnelAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.WokerListBean;
import com.jingna.xssapp.net.NetUrl;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicePersonnelActivity extends BaseActivity {

    private Context context = ServicePersonnelActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private ServicePersonnelAdapter adapter;
    private List<WokerListBean.ObjBean> mList;

    private String cityId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_personnel);

        cityId = getIntent().getStringExtra("id");
        ButterKnife.bind(ServicePersonnelActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.wokerListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.wokerListUrl))
                .addParam("city_id", cityId)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                WokerListBean bean = gson.fromJson(data, WokerListBean.class);
                                mList = bean.getObj();
                                adapter = new ServicePersonnelAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(context);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
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

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
