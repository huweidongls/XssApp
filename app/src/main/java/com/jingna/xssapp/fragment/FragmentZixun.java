package com.jingna.xssapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.ZixunAdapter;
import com.jingna.xssapp.base.BaseFragment;
import com.jingna.xssapp.bean.ZixunListBean;
import com.jingna.xssapp.net.NetUrl;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/5/15.
 */

public class FragmentZixun extends BaseFragment {

    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private ZixunAdapter adapter;
    private List<ZixunListBean.ObjBean> mList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_zixun, null);

        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        ViseHttp.POST(NetUrl.news_ListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.news_ListUrl))
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
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
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

}
