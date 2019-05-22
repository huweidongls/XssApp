package com.jingna.xssapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.xssapp.MainActivity;
import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseFragment;
import com.jingna.xssapp.bean.MemberInfoBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.page.BookingOrderActivity;
import com.jingna.xssapp.page.ConsumptionRecordsActivity;
import com.jingna.xssapp.page.LoginActivity;
import com.jingna.xssapp.page.MessageActivity;
import com.jingna.xssapp.page.MyAddressActivity;
import com.jingna.xssapp.page.MyCommentActivity;
import com.jingna.xssapp.page.MyCouponsActivity;
import com.jingna.xssapp.page.PersonInformationActivity;
import com.jingna.xssapp.page.SetActivity;
import com.jingna.xssapp.util.SpUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

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
    @BindView(R.id.tv_kefu_phone)
    TextView tvKefuPhone;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;

    private String uid = "";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, null);

        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        uid = SpUtils.getUid(getContext());
    }

    private void initData() {

        ViseHttp.POST(NetUrl.memberInfoUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.memberInfoUrl))
                .addParam("uid", SpUtils.getUid(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                MemberInfoBean memberInfoBean = gson.fromJson(data, MemberInfoBean.class);
                                tvKefuPhone.setText(memberInfoBean.getObj().getTel());
                                Glide.with(getContext()).load(NetUrl.BASE_URL+memberInfoBean.getObj().getHeadimg()).into(ivAvatar);
                                String num = memberInfoBean.getObj().getNum();
                                new QBadgeView(getContext())
                                        .bindTarget(rlCouponsNum)
                                        .setShowShadow(false)
                                        .setBadgeBackgroundColor(Color.parseColor("#F83030"))
                                        .setBadgeTextColor(Color.parseColor("#FFFFFF"))
                                        .setBadgeGravity(Gravity.END|Gravity.CENTER_VERTICAL)
                                        .setBadgeTextSize(13, true)
                                        .setBadgeNumber(Integer.parseInt(num));
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

    @OnClick({R.id.rl_fast_order, R.id.rl_message, R.id.rl_consumption_records, R.id.rl_address, R.id.ll_edit, R.id.ll_order,
    R.id.rl_login, R.id.rl_coupons, R.id.rl_my_comment, R.id.iv_set})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_fast_order:
                if(uid.equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), BookingOrderActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl_message:
                if(uid.equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), MessageActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl_consumption_records:
                if(uid.equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), ConsumptionRecordsActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl_address:
                if(uid.equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), MyAddressActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_edit:
                if(uid.equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), PersonInformationActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_order:
                if(uid.equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    MainActivity mainActivity = (MainActivity) getActivity();
                    mainActivity.selectFragment(2);
                }
                break;
            case R.id.rl_login:
                if(uid.equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {

                }
                break;
            case R.id.rl_coupons:
                if(uid.equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), MyCouponsActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.rl_my_comment:
                if(uid.equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), MyCommentActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.iv_set:
                if(uid.equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(getContext(), SetActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }

}
