package com.jingna.xssapp.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.xssapp.MainActivity;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.FastOrderAdapter;
import com.jingna.xssapp.adapter.FragmentIndexTuijianAdapter;
import com.jingna.xssapp.base.BaseFragment;
import com.jingna.xssapp.bean.IndexBannerBean;
import com.jingna.xssapp.bean.IndexServiceListBean;
import com.jingna.xssapp.bean.NewsListBean;
import com.jingna.xssapp.bean.PayServiceListBean;
import com.jingna.xssapp.bean.PriceListBean;
import com.jingna.xssapp.bean.WxPayBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.page.BookingOrderActivity;
import com.jingna.xssapp.page.CityActivity;
import com.jingna.xssapp.page.LoginActivity;
import com.jingna.xssapp.page.ServiceDetailsActivity;
import com.jingna.xssapp.page.ServicePersonnelActivity;
import com.jingna.xssapp.page.ZixunActivity;
import com.jingna.xssapp.util.SpUtils;
import com.jingna.xssapp.widget.ScrollTextView;
import com.jingna.xssapp.wxapi.WXShare;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.youth.banner.Banner;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2019/5/10.
 */

public class FragmentIndex extends BaseFragment {

    @BindView(R.id.banner)
    Banner banner;
    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_zixun)
    ScrollTextView tvZixun;
    @BindView(R.id.iv1)
    ImageView iv1;
    @BindView(R.id.iv2)
    ImageView iv2;
    @BindView(R.id.iv3)
    ImageView iv3;
    @BindView(R.id.iv4)
    ImageView iv4;
    @BindView(R.id.iv5)
    ImageView iv5;

    private FragmentIndexTuijianAdapter adapter;
    private List<IndexServiceListBean.ObjBean> mList;

    private List<IndexBannerBean.ObjBean> bannerList;
    private List<NewsListBean.ObjBean> newsList;

    private PopupWindow popupWindow;
    private View popView;

    private List<PayServiceListBean.ObjBean> mFastList;
    private FastOrderAdapter adapterFast;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_index, null);

        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        initData();
        initZixun();
        initFive();
        initView();
    }

    private void initView() {

        popView = LayoutInflater.from(getContext()).inflate(R.layout.popupwindow_booking_order_num, null);
        final RecyclerView recyclerView = popView.findViewById(R.id.rv);
        ViseHttp.POST(NetUrl.pay_ServiceListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.pay_ServiceListUrl))
                .addParam("cid", SpUtils.getCityId(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                PayServiceListBean bean = gson.fromJson(data, PayServiceListBean.class);
                                mFastList = bean.getObj();
                                adapterFast = new FastOrderAdapter(mFastList, new FastOrderAdapter.ClickListener() {
                                    @Override
                                    public void onItemClick(int pos) {
                                        Intent intent = new Intent();
                                        intent.setClass(getContext(), BookingOrderActivity.class);
                                        intent.putExtra("id", mFastList.get(pos).getId());
                                        intent.putExtra("name", mFastList.get(pos).getServicename());
                                        startActivity(intent);
                                        popupWindow.dismiss();
                                    }
                                });
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapterFast);
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

    private void initFive() {

        ViseHttp.POST(NetUrl.priceListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.priceListUrl))
                .addParam("cid", SpUtils.getCityId(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                final PriceListBean bean = gson.fromJson(data, PriceListBean.class);
                                if(bean.getObj().size()>0) {
                                    Glide.with(getContext()).load(NetUrl.BASE_URL + bean.getObj().get(0).getImgurl()).into(iv1);
                                    Glide.with(getContext()).load(NetUrl.BASE_URL + bean.getObj().get(1).getImgurl()).into(iv2);
                                    Glide.with(getContext()).load(NetUrl.BASE_URL + bean.getObj().get(2).getImgurl()).into(iv3);
                                    Glide.with(getContext()).load(NetUrl.BASE_URL + bean.getObj().get(3).getImgurl()).into(iv4);
                                    Glide.with(getContext()).load(NetUrl.BASE_URL + bean.getObj().get(4).getImgurl()).into(iv5);
                                    iv1.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent();
                                            intent.setClass(getContext(), ServiceDetailsActivity.class);
                                            intent.putExtra("id", bean.getObj().get(0).getServerid());
                                            startActivity(intent);
                                        }
                                    });
                                    iv2.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent();
                                            intent.setClass(getContext(), ServiceDetailsActivity.class);
                                            intent.putExtra("id", bean.getObj().get(1).getServerid());
                                            startActivity(intent);
                                        }
                                    });
                                    iv3.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent();
                                            intent.setClass(getContext(), ServiceDetailsActivity.class);
                                            intent.putExtra("id", bean.getObj().get(2).getServerid());
                                            startActivity(intent);
                                        }
                                    });
                                    iv4.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent();
                                            intent.setClass(getContext(), ServiceDetailsActivity.class);
                                            intent.putExtra("id", bean.getObj().get(3).getServerid());
                                            startActivity(intent);
                                        }
                                    });
                                    iv5.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            Intent intent = new Intent();
                                            intent.setClass(getContext(), ServiceDetailsActivity.class);
                                            intent.putExtra("id", bean.getObj().get(4).getServerid());
                                            startActivity(intent);
                                        }
                                    });
                                }
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

    private void initZixun() {

        ViseHttp.POST(NetUrl.newslistUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.newslistUrl))
                .addParam("cid", SpUtils.getCityId(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                NewsListBean bean = gson.fromJson(data, NewsListBean.class);
                                newsList = bean.getObj();
                                List<String> list = new ArrayList<>();
                                for (NewsListBean.ObjBean bean1 : newsList){
                                    list.add(bean1.getTitle());
                                }
                                if(list.size()>0){
                                    tvZixun.setList(list);
                                    tvZixun.startScroll();
                                }
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

    private void initBanner() {

        ViseHttp.POST(NetUrl.indexBnnerListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.indexBnnerListUrl))
                .addParam("cid", SpUtils.getCityId(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                IndexBannerBean bannerBean = gson.fromJson(data, IndexBannerBean.class);
                                bannerList = bannerBean.getObj();
                                List<String> list = new ArrayList<>();
                                for (IndexBannerBean.ObjBean bean : bannerList){
                                    list.add(NetUrl.BASE_URL+bean.getImgurl());
                                }
                                init(banner, list);
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

        String[] s = SpUtils.getCityName(getContext()).split("-");
        tvCity.setText(s[1]);

        ViseHttp.POST(NetUrl.indexServiceListUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.indexServiceListUrl))
                .addParam("city_id", SpUtils.getCityId(getContext()))
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                IndexServiceListBean bean = gson.fromJson(data, IndexServiceListBean.class);
                                mList = bean.getObj();
                                adapter = new FragmentIndexTuijianAdapter(mList);
                                GridLayoutManager manager = new GridLayoutManager(getContext(), 3){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);
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

    @OnClick({R.id.ll_city, R.id.ll_zixun, R.id.rl_more, R.id.iv_service_personnel, R.id.iv_yuyue})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.ll_city:
                String[] s = SpUtils.getCityName(getContext()).split("-");
                intent.setClass(getContext(), CityActivity.class);
                intent.putExtra("city", s[1]);
                intent.putExtra("type", 1);
                startActivity(intent);
                break;
            case R.id.ll_zixun:
                intent.setClass(getContext(), ZixunActivity.class);
                startActivity(intent);
                break;
            case R.id.rl_more:
                MainActivity mainActivity = (MainActivity) getActivity();
                mainActivity.selectFragment(1);
                break;
            case R.id.iv_service_personnel:
                intent.setClass(getContext(), ServicePersonnelActivity.class);
                intent.putExtra("id", SpUtils.getCityId(getContext()));
                startActivity(intent);
                break;
            case R.id.iv_yuyue:
                if(SpUtils.getUid(getContext()).equals("0")){
                    intent.setClass(getContext(), LoginActivity.class);
                    startActivity(intent);
                }else {
                    showFastPop();
                }
                break;
        }
    }

    public void showFastPop(){
        popupWindow = new PopupWindow(popView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
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

    @Override
    public void onDestroy() {
        super.onDestroy();
        tvZixun.stopScroll();
    }

}
