package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.MainActivity;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.AllCityAdapter;
import com.jingna.xssapp.adapter.CityAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.AllCityBean;
import com.jingna.xssapp.bean.OpenCityListBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.SpUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.zhouzhuo.zzletterssidebar.ZzLetterSideBar;
import me.zhouzhuo.zzletterssidebar.interf.OnLetterTouchListener;

public class CityActivity extends BaseActivity {

    private Context context = CityActivity.this;

    private RecyclerView recyclerView;
    private TextView tvCurCity;

    private ListView listView;
    private ZzLetterSideBar sideBar;
    private TextView dialog;

    private CityAdapter adapter;
    private List<OpenCityListBean.ObjBean> mList;

    private String curCity = "";
    private int type;

    private AllCityAdapter allCityAdapter;
    private List<AllCityBean.ObjBean> mAllList;

    private View header;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);

        type = getIntent().getIntExtra("type", 0);
        curCity = getIntent().getStringExtra("city");
        ButterKnife.bind(CityActivity.this);
        initHeader();
        initData();

    }

    private void initHeader() {

        header = LayoutInflater.from(context).inflate(R.layout.header_city, null);
        recyclerView = header.findViewById(R.id.rv);
        tvCurCity = header.findViewById(R.id.tv_cur_city);

        tvCurCity.setText(curCity);

        ViseHttp.POST(NetUrl.openCityListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.openCityListUrl))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                OpenCityListBean bean = gson.fromJson(data, OpenCityListBean.class);
                                mList = bean.getObj();
                                adapter = new CityAdapter(mList, new CityAdapter.ClickListener() {
                                    @Override
                                    public void onItemClick(int pos) {
                                        if(type == 1){
                                            SpUtils.setCityId(context, mList.get(pos).getId());
                                            SpUtils.setCityName(context, mList.get(pos).getCity_area());
                                            finish();
                                        }else if(type == 0){
                                            Intent intent = new Intent();
                                            intent.setClass(context, MainActivity.class);
                                            SpUtils.setCityId(context, mList.get(pos).getId());
                                            SpUtils.setCityName(context, mList.get(pos).getCity_area());
                                            startActivity(intent);
                                            finish();
                                        }
                                    }
                                });
                                LinearLayoutManager manager = new LinearLayoutManager(context){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
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

    private void initData() {

        sideBar = (ZzLetterSideBar) findViewById(R.id.sidebar);
        dialog = (TextView) findViewById(R.id.tv_dialog);
        listView = (ListView) findViewById(R.id.list_view);

        ViseHttp.POST(NetUrl.allcityUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.allcityUrl))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                AllCityBean bean = gson.fromJson(data, AllCityBean.class);
                                mAllList = bean.getObj();
                                allCityAdapter = new AllCityAdapter(context, mAllList);
                                listView.setAdapter(allCityAdapter);
                                //update data
//                                adapter.updateListView(mDatas);
//        tvFoot.setText(mDatas.size() + "位联系人");
                                //设置右侧触摸监听
                                sideBar.setLetterTouchListener(listView, allCityAdapter, dialog, new OnLetterTouchListener() {
                                    @Override
                                    public void onLetterTouch(String letter, int position) {
                                    }

                                    @Override
                                    public void onActionUp() {
                                    }
                                });
                                listView.addHeaderView(header);
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
