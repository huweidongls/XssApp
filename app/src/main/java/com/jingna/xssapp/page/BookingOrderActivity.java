package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingOrderActivity extends BaseActivity {

    private Context context = BookingOrderActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_order);

        ButterKnife.bind(BookingOrderActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.btn_sure})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_sure:
                intent.setClass(context, PayBookingOrderActivity.class);
                startActivity(intent);
                break;
        }
    }

}
