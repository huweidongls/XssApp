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
import android.widget.Button;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.BookingOrderAdapter;
import com.jingna.xssapp.adapter.PopBookingOrderCouponsAdapter;
import com.jingna.xssapp.adapter.PopBookingOrderNumAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.MemberAddressListBean;
import com.jingna.xssapp.bean.PreAboutBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.Logger;
import com.jingna.xssapp.util.SpUtils;
import com.jingna.xssapp.util.StringUtils;
import com.jingna.xssapp.util.ToastUtil;
import com.jingna.xssapp.widget.CustomDatePicker;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class BookingOrderActivity extends BaseActivity {

    private Context context = BookingOrderActivity.this;

    @BindView(R.id.tv_person_num)
    TextView tvPersonNum;
    @BindView(R.id.tv_service_name)
    TextView tvServiceName;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_address_details)
    TextView tvAddressDetails;
    @BindView(R.id.et_more_note)
    EditText etMoreNote;
    @BindView(R.id.tv_coupons)
    TextView tvCoupons;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_jichu_price)
    TextView tvJichuPrice;
    @BindView(R.id.tv_num_content)
    TextView tvNumContent;
    @BindView(R.id.tv_price)
    TextView tvPrice;
    @BindView(R.id.rv_num)
    RecyclerView rvNum;

    private int personNum = 1;
    private String name = "";
    private String addressId = "";
    private String id = "";

    private PopupWindow popupWindow;
    private View selectNumView;
    private RecyclerView rvSelectNum;
    private PopBookingOrderNumAdapter numAdapter;
    private List<PreAboutBean.ObjBean.ServicePriceBean> numList;
    private Button btnSure;

    private PopupWindow popCoupons;
    private View couponsView;
    private RecyclerView rvCoupons;
    private PopBookingOrderCouponsAdapter couponsAdapter;
    private List<PreAboutBean.ObjBean.CouponBean> couponsList;
    private boolean isCoupons = false;

    private CustomDatePicker customDatePicker;
    private String now;

    private String couponsId = "0";
    private double jichuPrice;
    private double couponsPrice;
    private String isMore = "";

    //多规格输入列表
    private BookingOrderAdapter bookingOrderAdapter;
    private List<PreAboutBean.ObjBean.ServicePriceBean> mBookingList;
    //服务价格id-服务数量id  map
    private Map<String, String> numMap;
    private Map<String, Double> priceMap;
    private double allMoney;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_order);

        id = getIntent().getStringExtra("id");
        name = getIntent().getStringExtra("name");
        isMore = getIntent().getStringExtra("ismore");
        ButterKnife.bind(BookingOrderActivity.this);
        initData();
        initDatePicker();

    }

    private void initDatePicker() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        now = sdf.format(new Date());

        customDatePicker = new CustomDatePicker(this, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) { // 回调接口，获得选中的时间
                tvTime.setText(time);
            }
        }, now, "2100-01-01 00:00"); // 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        customDatePicker.showSpecificTime(true); // 不显示时和分
        customDatePicker.setIsLoop(false); // 不允许循环滚动
    }

    private void initData() {

        numMap = new LinkedHashMap<>();
        priceMap = new LinkedHashMap<>();
        mBookingList = new ArrayList<>();
        bookingOrderAdapter = new BookingOrderAdapter(mBookingList, new BookingOrderAdapter.ClickListener() {
            @Override
            public void onClick(String id, String num, String price) {
                allMoney = 0.00;
                numMap.put(id, num);
                priceMap.put(id, Double.valueOf(price));
                Set set = priceMap.keySet();
                for (Iterator iter = set.iterator(); iter.hasNext(); ) {
                    String key = (String) iter.next();
                    Double value = priceMap.get(key);
                    allMoney = allMoney + value;
                }
                double priceI = jichuPrice + allMoney - couponsPrice;
                tvPrice.setText("￥" + priceI);
            }
        });
        LinearLayoutManager manager = new LinearLayoutManager(context) {
            @Override
            public boolean canScrollVertically() {
                return false;
            }
        };
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvNum.setLayoutManager(manager);
        rvNum.setAdapter(bookingOrderAdapter);

        tvServiceName.setText(name);
        selectNumView = LayoutInflater.from(context).inflate(R.layout.popupwindow_booking_order_num, null);
        rvSelectNum = selectNumView.findViewById(R.id.rv);
        btnSure = selectNumView.findViewById(R.id.btn_sure);
        if (isMore.equals("1")) {
            btnSure.setVisibility(View.VISIBLE);
        } else {
            btnSure.setVisibility(View.GONE);
        }
        couponsView = LayoutInflater.from(context).inflate(R.layout.popupwindow_booking_order_num, null);
        rvCoupons = couponsView.findViewById(R.id.rv);
        ViseHttp.POST(NetUrl.preAboutUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL + NetUrl.preAboutUrl))
                .addParam("uid", SpUtils.getUid(context))
                .addParam("fid", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if (jsonObject.optInt("code") == 200) {
                                Gson gson = new Gson();
                                final PreAboutBean bean = gson.fromJson(data, PreAboutBean.class);
                                tvJichuPrice.setText(bean.getObj().getBasicservice() + "元");
                                //jichuPrice = Double.valueOf(bean.getObj().getBasicservice()+"");
                                //规格
                                numList = bean.getObj().getServicePrice();
                                numAdapter = new PopBookingOrderNumAdapter(numList, isMore, new PopBookingOrderNumAdapter.ClickListener() {
                                    @Override
                                    public void onItemClick(PreAboutBean.ObjBean.ServicePriceBean bean) {
                                        allMoney = 0.00;
                                        tvPrice.setText("￥" + "0.0");
                                        tvNumContent.setText("已选择");
                                        mBookingList.clear();
                                        mBookingList.add(bean);
                                        bookingOrderAdapter.notifyDataSetChanged();
                                        popupWindow.dismiss();
                                    }
                                });
                                LinearLayoutManager manager = new LinearLayoutManager(context);
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                rvSelectNum.setLayoutManager(manager);
                                rvSelectNum.setAdapter(numAdapter);
                                btnSure.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        allMoney = 0.00;
                                        tvPrice.setText("￥" + "0.0");
                                        tvNumContent.setText("已选择");
                                        mBookingList.clear();
                                        numMap.clear();
                                        priceMap.clear();
                                        for (PreAboutBean.ObjBean.ServicePriceBean bean1 : numList) {
                                            if (bean1.getIsSelect() == 1) {
                                                mBookingList.add(bean1);
                                            }
                                        }
                                        bookingOrderAdapter.notifyDataSetChanged();
                                        popupWindow.dismiss();
                                    }
                                });

                                //优惠券
                                couponsList = bean.getObj().getCoupon();
                                if (couponsList.size() > 0) {
                                    tvCoupons.setText("请选择优惠券");
                                    isCoupons = true;
                                    couponsAdapter = new PopBookingOrderCouponsAdapter(couponsList, new PopBookingOrderCouponsAdapter.ClickListener() {
                                        @Override
                                        public void onItemClick(int pos) {
                                            tvCoupons.setText(bean.getObj().getCoupon().get(pos).getOption());
                                            couponsId = bean.getObj().getCoupon().get(pos).getId();
                                            couponsPrice = Double.valueOf(bean.getObj().getCoupon().get(pos).getMoney());
                                            double price = jichuPrice + allMoney - couponsPrice;
                                            tvPrice.setText("￥" + price);
                                            popCoupons.dismiss();
                                        }
                                    });
                                    LinearLayoutManager manager1 = new LinearLayoutManager(context);
                                    manager1.setOrientation(LinearLayoutManager.VERTICAL);
                                    rvCoupons.setLayoutManager(manager1);
                                    rvCoupons.setAdapter(couponsAdapter);
                                } else {
                                    couponsId = "0";
                                    tvCoupons.setText("暂无可用优惠券");
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

    @OnClick({R.id.rl_back, R.id.btn_sure, R.id.rl_jianhao, R.id.rl_jiahao, R.id.rl_address, R.id.rl_num, R.id.rl_coupons, R.id.rl_time})
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_sure:
                if (StringUtils.isEmpty(addressId) || StringUtils.isEmpty(tvTime.getText().toString()) ||
                        mBookingList.size() != numMap.size() || mBookingList.size() == 0) {
                    ToastUtil.showShort(context, "请完善信息后提交");
                } else {
                    Set set = numMap.keySet();
                    String specid_number = "";
                    for (Iterator iter = set.iterator(); iter.hasNext(); ) {
                        String key = (String) iter.next();
                        String value = numMap.get(key);
                        if (!iter.hasNext()) {
                            specid_number = specid_number + key + "-" + value;
                        } else {
                            specid_number = specid_number + key + "-" + value + ",";
                        }
                    }
                    Logger.e("123123", specid_number);
                    double price = jichuPrice + allMoney - couponsPrice;
                    Map<String, String> map = new LinkedHashMap<>();
                    map.put("uid", SpUtils.getUid(context));
                    map.put("fid", id);
                    map.put("cid", addressId);
                    map.put("ptime", tvTime.getText().toString());
                    map.put("price", price + "");
                    map.put("remarks", etMoreNote.getText().toString() + "");
                    map.put("dis_id", couponsId);
                    map.put("specid_number", specid_number);
                    intent.setClass(context, PayBookingOrderActivity.class);
                    intent.putExtra("map", (Serializable) map);
                    startActivity(intent);
                }
                break;
            case R.id.rl_jianhao:
                if (personNum > 1) {
                    personNum = personNum - 1;
                    tvPersonNum.setText(personNum + "");
                }
                break;
            case R.id.rl_jiahao:
                personNum = personNum + 1;
                tvPersonNum.setText(personNum + "");
                break;
            case R.id.rl_address:
                intent.setClass(context, MyAddressActivity.class);
                intent.putExtra("order", 1);
                startActivityForResult(intent, 1001);
                break;
            case R.id.rl_num:
                showSelectNum();
                break;
            case R.id.rl_coupons:
                if (isCoupons) {
                    showCoupons();
                }
                break;
            case R.id.rl_time:
                customDatePicker.show(now);
                break;
        }
    }

    /**
     * 显示选择数量
     */
    private void showCoupons() {

        popCoupons = new PopupWindow(couponsView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
        popCoupons.setTouchable(true);
        popCoupons.setFocusable(true);
        // 设置点击窗口外边窗口消失
        popCoupons.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popCoupons.setOutsideTouchable(true);
        popCoupons.showAtLocation(getWindow().getDecorView(), Gravity.BOTTOM, 0, 0);
//        popupWindow.showAsDropDown(rlPro);
        // 设置popWindow的显示和消失动画
        popCoupons.setAnimationStyle(R.style.mypopwindow_anim_style_bottom);
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popCoupons.update();

        popCoupons.setOnDismissListener(new PopupWindow.OnDismissListener() {

            // 在dismiss中恢复透明度
            public void onDismiss() {
                WindowManager.LayoutParams params = getWindow().getAttributes();
                params.alpha = 1f;
                getWindow().setAttributes(params);
            }
        });
    }

    /**
     * 显示选择数量
     */
    private void showSelectNum() {

        popupWindow = new PopupWindow(selectNumView, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT, true);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1001) {
            MemberAddressListBean.ObjBean bean = (MemberAddressListBean.ObjBean) data.getSerializableExtra("bean");
            tvAddress.setText(bean.getAddress());
            tvAddressDetails.setText(bean.getDetailedaddress());
            addressId = bean.getId();
        }
    }
}
