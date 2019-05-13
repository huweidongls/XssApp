package com.jingna.xssapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseFragment;
import com.jingna.xssapp.page.ConsumptionRecordsActivity;
import com.jingna.xssapp.page.LoginActivity;
import com.jingna.xssapp.page.MessageActivity;
import com.jingna.xssapp.page.MyAddressActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import q.rorbin.badgeview.QBadgeView;

/**
 * Created by Administrator on 2019/5/10.
 */

public class FragmentMy extends BaseFragment {

    @BindView(R.id.rl_coupons_num)
    RelativeLayout rlCouponsNum;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);

        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        new QBadgeView(getContext())
                .bindTarget(rlCouponsNum)
                .setShowShadow(false)
                .setBadgeBackgroundColor(Color.parseColor("#F83030"))
                .setBadgeTextColor(Color.parseColor("#FFFFFF"))
                .setBadgeGravity(Gravity.END|Gravity.CENTER_VERTICAL)
                .setBadgeTextSize(13, true)
                .setBadgeNumber(95);

    }

    @OnClick({R.id.rl_fast_order, R.id.rl_message, R.id.rl_consumption_records, R.id.rl_address})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_fast_order:
                intent.setClass(getContext(), LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_message:
                intent.setClass(getContext(), MessageActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_consumption_records:
                intent.setClass(getContext(), ConsumptionRecordsActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_address:
                intent.setClass(getContext(), MyAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

}
