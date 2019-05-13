package com.jingna.xssapp.app;

import android.app.Activity;
import android.app.Application;

import com.jingna.xssapp.util.Const;
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
        ViseHttp.init(this);
        ViseHttp.CONFIG().baseUrl(Const.BASE_URL);
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
