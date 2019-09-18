package com.jingna.xssapp.broadcastreceiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jingna.xssapp.MainActivity;
import com.jingna.xssapp.util.Logger;

/**
 * Created by Administrator on 2019/9/18.
 */

public class MyReceiver extends BroadcastReceiver {

    private MainActivity activity;
    private Context context;

    public MyReceiver(Activity activity, Context context) {
        this.activity = (MainActivity) activity;
        this.context = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        activity.selectFragment(2);
        Logger.e("123123", "走了广播");
    }
}
