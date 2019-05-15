package com.jingna.xssapp.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.MainActivity;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.FragmentIndexTuijianAdapter;
import com.jingna.xssapp.base.BaseFragment;
import com.jingna.xssapp.bean.BaiduCityBean;
import com.jingna.xssapp.page.CityActivity;
import com.jingna.xssapp.page.ZixunActivity;
import com.jingna.xssapp.util.Gps;
import com.jingna.xssapp.util.PositionUtil;
import com.jingna.xssapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/5/10.
 */

public class FragmentIndex extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_city)
    TextView tvCity;

    private FragmentIndexTuijianAdapter adapter;
    private List<String> mList;

    private LocationManager locationManager;
    private double latitude = 0.0;
    private double longitude = 0.0;
    private String latLongString = "";

    private Handler handler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 1:
                    getCity();
                    break;
            }
        }

    };

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);

        ButterKnife.bind(this, view);
        locationManager = (LocationManager) getActivity().getSystemService(Context.LOCATION_SERVICE);
        initData();

        return view;
    }

    private void initData() {

        if (locationManager.getProvider(LocationManager.NETWORK_PROVIDER) != null || locationManager.getProvider(LocationManager.GPS_PROVIDER) != null) {
            /*
             * 进行定位
             * provider:用于定位的locationProvider字符串:LocationManager.NETWORK_PROVIDER/LocationManager.GPS_PROVIDER
             * minTime:时间更新间隔，单位：ms
             * minDistance:位置刷新距离，单位：m
             * listener:用于定位更新的监听者locationListener
             */
            getLocation();
        } else {
            //无法定位：1、提示用户打开定位服务；2、跳转到设置界面
            ToastUtil.showShort(getContext(), "无法定位，请打开定位服务");
            Intent i = new Intent();
            i.setAction(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
            startActivity(i);
        }
        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        adapter = new FragmentIndexTuijianAdapter(mList);
        GridLayoutManager manager = new GridLayoutManager(getContext(), 3){
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.ll_city, R.id.ll_zixun, R.id.rl_more})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.ll_city:
                intent.setClass(getContext(), CityActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_zixun:
                intent.setClass(getContext(), ZixunActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_more:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.selectFragment(1);
                break;
        }
    }

    public void getLocation() {
        new Thread() {
            @Override
            public void run() {
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    Log.i("123123", "没权限");
                    return;
                }
                Log.i("123123", "有权限");
                Location location = locationManager
                        .getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                if (location != null) {
                    Gps gps = PositionUtil.Gps84_To_bd09(location.getLatitude(), location.getLongitude());
                    latitude = gps.getWgLat();
                    longitude = gps.getWgLon();
                    Log.e("123123", latitude+"----"+longitude);
                    handler.sendEmptyMessage(1);
                }
            }
        }.start();
    }

    public void getCity() {
        try {
            String url = "http://api.map.baidu.com/geocoder?output=json&location=" + latitude + "," + longitude + "&key=8dDPAEEMwPNZgxg4YhNUXqWoV8GNItO1";
            ViseHttp.GET(url)
                    .request(new ACallback<String>() {
                        @Override
                        public void onSuccess(String data) {
                            try {
                                JSONObject jsonObject = new JSONObject(data);
                                if (jsonObject.getString("status").equals("OK")) {
                                    Gson gson = new Gson();
                                    BaiduCityBean model = gson.fromJson(data, BaiduCityBean.class);
                                    latLongString = model.getResult().getAddressComponent().getCity();
                                    tvCity.setText(latLongString);
                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void onFail(int errCode, String errMsg) {

                        }
                    });

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
