package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.ZixunAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.ZixunListBean;
import com.jingna.xssapp.net.NetUrl;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SearchZixunActivity extends BaseActivity {

    private Context context = SearchZixunActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private ZixunAdapter adapter;
    private List<ZixunListBean.ObjBean> mList;

    private String text = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_zixun);

        text = getIntent().getStringExtra("text");
        ButterKnife.bind(SearchZixunActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.seachNewsUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.seachNewsUrl))
                .addParam("text", text)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                ZixunListBean bean = gson.fromJson(data, ZixunListBean.class);
                                mList = bean.getObj();
                                adapter = new ZixunAdapter(mList);
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
