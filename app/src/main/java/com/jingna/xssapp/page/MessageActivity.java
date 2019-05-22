package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.MessageAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.MemberMessageBean;
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

public class MessageActivity extends BaseActivity {

    private Context context = MessageActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_message_null)
    ImageView ivMessageNull;

    private MessageAdapter adapter;
    private List<MemberMessageBean.ObjBean> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ButterKnife.bind(MessageActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.member_messageUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.member_messageUrl))
                .addParam("uid", SpUtils.getUid(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                MemberMessageBean bean = gson.fromJson(data, MemberMessageBean.class);
                                mList = bean.getObj();
                                if(mList.size()>0){
                                    adapter = new MessageAdapter(mList);
                                    LinearLayoutManager manager = new LinearLayoutManager(context);
                                    manager.setOrientation(LinearLayoutManager.VERTICAL);
                                    recyclerView.setLayoutManager(manager);
                                    recyclerView.setAdapter(adapter);
                                    recyclerView.setVisibility(View.VISIBLE);
                                    ivMessageNull.setVisibility(View.GONE);
                                }else {
                                    recyclerView.setVisibility(View.GONE);
                                    ivMessageNull.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
