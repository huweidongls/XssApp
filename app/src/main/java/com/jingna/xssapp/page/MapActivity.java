package com.jingna.xssapp.page;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.CoordListBean;
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

public class MapActivity extends BaseActivity {

    private Context context = MapActivity.this;

    @BindView(R.id.mapview)
    MapView mMapView;

    private BaiduMap mBaiduMap;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        id = getIntent().getStringExtra("id");
        ButterKnife.bind(MapActivity.this);

        mBaiduMap = mMapView.getMap();
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.coordListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.coordListUrl))
                .addParam("oid", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                CoordListBean bean = gson.fromJson(data, CoordListBean.class);

                                //绘制线
                                List<CoordListBean.ObjBean.ResBean> coordList = bean.getObj().getRes();
                                if(coordList.size()>=2){
                                    List<LatLng> points = new ArrayList<LatLng>();
                                    for(int a = 0; a<coordList.size(); a++){
                                        points.add(new LatLng(Double.valueOf(coordList.get(a).getLat()), Double.valueOf(coordList.get(a).getLng())));
                                    }
                                    OverlayOptions ooPolyline = new PolylineOptions().width(10)
                                            .color(Color.parseColor("#2681fc")).points(points);
                                    mBaiduMap.addOverlay(ooPolyline);

                                    //构建Marker图标
                                    BitmapDescriptor bitmapStart = BitmapDescriptorFactory
                                            .fromResource(R.mipmap.location_big);
                                    BitmapDescriptor bitmapEnd = BitmapDescriptorFactory
                                            .fromResource(R.mipmap.location_big);
                                    List<OverlayOptions> options = new ArrayList<OverlayOptions>();
                                        options.add(new MarkerOptions().position(new LatLng(Double.valueOf(bean.getObj().getLat()),
                                                Double.valueOf(bean.getObj().getLng()))).icon(bitmapEnd));
                                        options.add(new MarkerOptions().position(new LatLng(Double.valueOf(coordList.get(coordList.size()-1).getLat()),
                                                Double.valueOf(coordList.get(coordList.size()-1).getLng()))).icon(bitmapStart));
                                    mBaiduMap.addOverlays(options);

                                    //设定中心点坐标
                                    LatLng cenpt =  new LatLng(Double.valueOf(coordList.get(coordList.size()-1).getLat()),
                                            Double.valueOf(coordList.get(coordList.size()-1).getLng()));
                                    //定义地图状态
                                    MapStatus mMapStatus = new MapStatus.Builder()
                                            //要移动的点
                                            .target(cenpt)
                                            //放大地图到20倍
                                            .zoom(15)
                                            .build();
                                    //定义MapStatusUpdate对象，以便描述地图状态将要发生的变化
                                    MapStatusUpdate mMapStatusUpdate = MapStatusUpdateFactory.newMapStatus(mMapStatus);
                                    //改变地图状态
                                    mBaiduMap.setMapStatus(mMapStatusUpdate);

                                }

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

    @Override
    protected void onResume() {
        super.onResume();
        mMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mMapView.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMapView.onDestroy();
    }

}
