package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.CouponsAdapter;
import com.jingna.xssapp.adapter.FastOrderAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.CouponListBean;
import com.jingna.xssapp.bean.PayServiceListBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.SpUtils;
import com.jingna.xssapp.util.ToastUtil;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyCouponsActivity extends BaseActivity {

    private Context context = MyCouponsActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    private PopupWindow popupWindow;
    private View popView;
    private CouponsAdapter adapter;
    private List<CouponListBean.ObjBean> mList;
    private List<PayServiceListBean.ObjBean> mLists;
    private FastOrderAdapter adapters;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_coupons);
        ButterKnife.bind(MyCouponsActivity.this);
        initData();
        initViews();
       // adapters.getItemCount();
    }
    private void initViews() {
        popView = LayoutInflater.from(context).inflate(R.layout.popupwindow_booking_order_num, null);
        final RecyclerView recyclerView = popView.findViewById(R.id.rv);
        ViseHttp.POST(NetUrl.pay_ServiceListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.pay_ServiceListUrl))
                .addParam("cid", SpUtils.getCityId(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                PayServiceListBean bean = gson.fromJson(data, PayServiceListBean.class);
                                mLists = bean.getObj();
                                adapters = new FastOrderAdapter(mLists, new FastOrderAdapter.ClickListener() {
                                    @Override
                                    public void onItemClick(int pos) {
                                        Intent intent = new Intent();
                                        intent.setClass(context, BookingOrderActivity.class);
                                        intent.putExtra("id", mLists.get(pos).getId());
                                        intent.putExtra("name", mLists.get(pos).getServicename());
                                        intent.putExtra("ismore", mLists.get(pos).getSpecifications_status());
                                        startActivity(intent);
                                        popupWindow.dismiss();
                                    }
                                });
                                LinearLayoutManager manager = new LinearLayoutManager(context);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapters);
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
    private void initData() {

        ViseHttp.POST(NetUrl.couponListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.couponListUrl))
                .addParam("uid", SpUtils.getUid(context))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                CouponListBean bean = gson.fromJson(data, CouponListBean.class);
                                mList = bean.getObj();
                                adapter = new CouponsAdapter(mList, new CouponsAdapter.ClickListener() {
                                    @Override
                                    public void onPayOrder(int pos) {
                                        showFastPop();
                                    }
                                });
                                LinearLayoutManager manager = new LinearLayoutManager(context);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                            }else {
                                ToastUtil.showShort(context, jsonObject.optString("message"));
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
    public void showFastPop(){
        popupWindow = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//        popupWindow.showAsDropDown(rlPro);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style_bottom);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.update();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
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

}
