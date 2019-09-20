package com.jingna.xssapp.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.jingna.xssapp.page.PayBookingOrderActivity;

/**
 * Created by Administrator on 2019/9/20.
 */

public class PayReceiver extends BroadcastReceiver {

    private PayBookingOrderActivity activity;
    private Context cxt;

    public PayReceiver(PayBookingOrderActivity activity, Context context) {
        this.activity = activity;
        this.cxt = context;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        activity.finish();
    }

}
