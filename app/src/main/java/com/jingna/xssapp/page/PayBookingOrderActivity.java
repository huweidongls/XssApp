package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class PayBookingOrderActivity extends BaseActivity {

    private Context context = PayBookingOrderActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_booking_order);

        ButterKnife.bind(PayBookingOrderActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_to_pay})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_to_pay:
                intent.setClass(context, BookingOrderResultActivity.class);
                startActivity(intent);
                break;
        }
    }

}
