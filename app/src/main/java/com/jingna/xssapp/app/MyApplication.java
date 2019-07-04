package com.jingna.xssapp.app;

import android.app.Activity;
import android.app.Application;

import com.baidu.mapapi.CoordType;
import com.baidu.mapapi.SDKInitializer;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.EditPwdTimeCount;
import com.jingna.xssapp.util.ForgotTimeCount;
import com.vise.xsnow.http.ViseHttp;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Administrator on 2019/5/10.
 */

public class MyApplication extends Application {

    private static MyApplication instance;
    private List<Activity> mList = new LinkedList<Activity>();
    public static EditPwdTimeCount editPwdTimeCount;
    public static ForgotTimeCount forgotTimeCount;

    public MyApplication() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //在使用SDK各组件之前初始化context信息，传入ApplicationContext
        SDKInitializer.initialize(this);
        //自4.3.0起，百度地图SDK所有接口均支持百度坐标和国测局坐标，用此方法设置您使用的坐标类型.
        //包括BD09LL和GCJ02两种坐标，默认是BD09LL坐标。
        SDKInitializer.setCoordType(CoordType.BD09LL);
        ViseHttp.init(this);
        ViseHttp.CONFIG().baseUrl(NetUrl.BASE_URL);
        editPwdTimeCount = new EditPwdTimeCount(60000, 1000);
        forgotTimeCount = new ForgotTimeCount(60000, 1000);
    }

    public synchronized static MyApplication getInstance() {
        if (null == instance) {
            instance = new MyApplication();
        }
        return instance;
    }

    // add Activity
    public void addActivity(Activity activity) {
        mList.add(activity);
    }

    public void exit() {
        try {
            for (Activity activity : mList) {
                if (activity != null)
                    activity.finish();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.exit(0);
        }
    }

    public void onLowMemory() {
        super.onLowMemory();
        System.gc();
    }

}
