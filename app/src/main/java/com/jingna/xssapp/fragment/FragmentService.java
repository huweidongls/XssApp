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
import com.jingna.xssapp.adapter.FenleiLeftAdapter;
import com.jingna.xssapp.adapter.FenleiRightAdapter;
import com.jingna.xssapp.base.BaseFragment;
import com.jingna.xssapp.bean.ServiceTypeAndServiceListBean;
import com.jingna.xssapp.bean.ServiceTypeListBean;
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
 * Created by Administrator on 2019/5/10.
 */

public class FragmentService extends BaseFragment {

    @BindView(R.id.rv_fenlei)
    RecyclerView rvFenlei;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;

    private FenleiLeftAdapter leftAdapter;
    private List<ServiceTypeListBean.ObjBean> mList;
    private FenleiRightAdapter rightAdapter;
    private List<ServiceTypeAndServiceListBean.ObjBean> mList1;

    private String id = "";
    private String city = "";

    public static FragmentService newInstance(String id, String city) {
        FragmentService newFragment = new FragmentService();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("city", city);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, null);

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
            city = args.getString("city");
        }
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        ViseHttp.POST(NetUrl.serviceTypeListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.serviceTypeListUrl))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                final Gson gson = new Gson();
                                ServiceTypeListBean bean = gson.fromJson(data, ServiceTypeListBean.class);
                                mList = bean.getObj();
                                leftAdapter = new FenleiLeftAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                rvFenlei.setLayoutManager(manager);
                                rvFenlei.setAdapter(leftAdapter);
                                leftAdapter.setListener(new FenleiLeftAdapter.ItemClickListener() {
                                    @Override
                                    public void onItemClick(int i) {
                                        ViseHttp.POST(NetUrl.serviceTypeAndServiceListUrl)
                                                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.serviceTypeAndServiceListUrl))
                                                .addParam("city", id)
                                                .addParam("sid", mList.get(i).getId())
                                                .request(new ACallback<String>() {
                                                    @Override
                                                    public void onSuccess(String data) {
                                                        try {
                                                            JSONObject jsonObject1 = new JSONObject(data);
                                                            if(jsonObject1.optInt("code") == 200){
                                                                Gson gson1 = new Gson();
                                                                ServiceTypeAndServiceListBean bean1 = gson1.fromJson(data, ServiceTypeAndServiceListBean.class);
                                                                mList1 = bean1.getObj();
                                                                rightAdapter = new FenleiRightAdapter(mList1);
                                                                LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
                                                                manager1.setOrientation(LinearLayoutManager.VERTICAL);
                                                                rvRight.setLayoutManager(manager1);
                                                                rvRight.setAdapter(rightAdapter);
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
                                });
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
