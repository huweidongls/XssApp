package com.jingna.xssapp.fragment;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.FragmentAllOrderAdapter;
import com.jingna.xssapp.base.BaseFragment;
import com.jingna.xssapp.bean.OrderListBean;
import com.jingna.xssapp.bean.TopayOrderBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.page.PayBookingOrderActivity;
import com.jingna.xssapp.util.SpUtils;
import com.jingna.xssapp.wxapi.WXShare;
import com.scwang.smartrefresh.header.MaterialHeader;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.footer.ClassicsFooter;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/5/10.
 */

public class FragmentAllOrder extends BaseFragment {

    @BindView(R.id.refresh)
    SmartRefreshLayout smartRefreshLayout;
    @BindView(R.id.rv)
    RecyclerView recyclerView;

    private FragmentAllOrderAdapter adapter;
    private List<OrderListBean.ObjBean> mList;

    private int page = 1;

    private PopupWindow popupWindow;
    private View view;

    private IWXAPI api;
    private static final int SDK_PAY_FLAG = 1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_all_order, null);

        api = WXAPIFactory.createWXAPI(getContext(), null);
        api.registerApp(WXShare.APP_ID);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
    }

    private void initData() {

        smartRefreshLayout.setRefreshHeader(new MaterialHeader(getContext()));
        smartRefreshLayout.setRefreshFooter(new ClassicsFooter(getContext()));
        smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                ViseHttp.POST(NetUrl.order_listUrl)
                        .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.order_listUrl))
                        .addParam("uid", SpUtils.getUid(getContext()))
                        .addParam("page", "1")
                        .addParam("type", "0")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optInt("code") == 200){
                                        Gson gson = new Gson();
                                        OrderListBean bean = gson.fromJson(data, OrderListBean.class);
                                        mList.clear();
                                        mList.addAll(bean.getObj());
                                        adapter.notifyDataSetChanged();
                                        page = 2;
                                    }
                                    refreshLayout.finishRefresh(500);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishRefresh(500);
                            }
                        });
            }
        });
        smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull final RefreshLayout refreshLayout) {
                ViseHttp.POST(NetUrl.order_listUrl)
                        .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.order_listUrl))
                        .addParam("uid", SpUtils.getUid(getContext()))
                        .addParam("page", page+"")
                        .addParam("type", "0")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optInt("code") == 200){
                                        Gson gson = new Gson();
                                        OrderListBean bean = gson.fromJson(data, OrderListBean.class);
                                        mList.addAll(bean.getObj());
                                        adapter.notifyDataSetChanged();
                                        page = page+1;
                                    }
                                    refreshLayout.finishLoadMore(500);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }

                            @Override
                            public void onFail(int errCode, String errMsg) {
                                refreshLayout.finishLoadMore(500);
                            }
                        });
            }
        });

        ViseHttp.POST(NetUrl.order_listUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.order_listUrl))
                .addParam("uid", SpUtils.getUid(getContext()))
                .addParam("page", "1")
                .addParam("type", "0")
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                OrderListBean bean = gson.fromJson(data, OrderListBean.class);
                                mList = bean.getObj();
                                adapter = new FragmentAllOrderAdapter(mList);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
                                adapter.setListener(new FragmentAllOrderAdapter.ClickListener() {
                                    @Override
                                    public void onPay(int pos) {
                                        showPayPop(mList.get(pos).getId());
                                    }
                                });
                                page = 2;
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

    private void showPayPop(final String id){

        view = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_pay_type, null);

        RelativeLayout rlWx = view.findViewById(R.id.rl_wx);
        RelativeLayout rlZfb = view.findViewById(R.id.rl_zfb);

        rlZfb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                ViseHttp.POST(NetUrl.topay_OrderUrl)
                        .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.topay_OrderUrl))
                        .addParam("oid", id)
                        .addParam("pay_type", "2")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optInt("code") == 300){
                                        String s = jsonObject.optString("obj");
                                        aliPay(s);
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
        });

        rlWx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
                ViseHttp.POST(NetUrl.topay_OrderUrl)
                        .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.topay_OrderUrl))
                        .addParam("oid", id)
                        .addParam("pay_type", "1")
                        .request(new ACallback<String>() {
                            @Override
                            public void onSuccess(String data) {
                                try {
                                    JSONObject jsonObject = new JSONObject(data);
                                    if(jsonObject.optInt("code") == 200){
                                        Gson gson = new Gson();
                                        TopayOrderBean bean = gson.fromJson(data, TopayOrderBean.class);
                                        wxPay(bean.getObj());
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
        });

        popupWindow = new PopupWindow(view, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setOutsideTouchable(true);
        popupWindow.showAtLocation(getActivity().getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//        popupWindow.showAsDropDown(rlPro);
        // 设置popWindow的显示和消失动画
        popupWindow.setAnimationStyle(R.style.mypopwindow_anim_style_bottom);
        WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
        params.alpha = 0.5f;
        getActivity().getWindow().setAttributes(params);
        popupWindow.update();

        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getActivity().getWindow().getAttributes();
                params.alpha = 1f;
                getActivity().getWindow().setAttributes(params);
            }
        });
    }

    public void wxPay(TopayOrderBean.ObjBean model) {
        api.registerApp(WXShare.APP_ID);
        PayReq req = new PayReq();
        req.appId = model.getAppId();
        req.partnerId = model.getPartnerId();
        req.prepayId = model.getPrepayId();
        req.nonceStr = model.getNonceStr();
        req.timeStamp = model.getTimestamp() + "";
        req.packageValue = "Sign=WXPay";
        req.sign = model.getSign();
        req.extData = "app data";
        api.sendReq(req);
    }

    public void aliPay(String info) {
        final String orderInfo = info;   // 订单信息

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                PayTask alipay = new PayTask(getActivity());
                Map<String, String> result = alipay.payV2(orderInfo,true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };
        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG:
                    Map<String, String> result = (Map<String, String>) msg.obj;
                    if(result.get("resultStatus").equals("9000")){
                        Toast.makeText(getContext(), "支付成功", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }

    };

}
