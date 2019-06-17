package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.MessageDetailsAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.OrderContentBean;
import com.jingna.xssapp.dialog.CustomDialog;
import com.jingna.xssapp.net.NetUrl;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OrderDetailsActivity extends BaseActivity {

    private Context context = OrderDetailsActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.tv_service_type)
    TextView tvServiceType;
    @BindView(R.id.tv_order_code)
    TextView tvOrderCode;
    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_address)
    TextView tvAddress;
    @BindView(R.id.tv_remarks)
    TextView tvRemarks;

    private MessageDetailsAdapter adapter;
    private List<String> mList;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_details);

        id = getIntent().getStringExtra("id");
        ButterKnife.bind(OrderDetailsActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.order_contentUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.order_contentUrl))
                .addParam("oid", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                OrderContentBean bean = gson.fromJson(data, OrderContentBean.class);
                                tvServiceType.setText(bean.getObj().getService_type());
                                tvOrderCode.setText(bean.getObj().getOrder_code());
                                tvTime.setText(bean.getObj().getAddtime());
                                tvAddress.setText(bean.getObj().getAddress());
                                tvRemarks.setText(bean.getObj().getRemarks());
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {

                    }
                });

//        mList = new ArrayList<>();
//        mList.add("");
//        mList.add("");
//        adapter = new MessageDetailsAdapter(mList);
//        LinearLayoutManager manager = new LinearLayoutManager(context){
//            @Override
//            public boolean canScrollVertically() {
//                return false;
//            }
//        };
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        recyclerView.setLayoutManager(manager);
//        recyclerView.setAdapter(adapter);

    }

    @OnClick({R.id.rl_back, R.id.btn_sure, R.id.btn_cancel})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_sure:
                CustomDialog customDialog = new CustomDialog(context, "工人是否已经开始服务，为防止产生纠纷，服务过程中建议采用视频记录", new CustomDialog.ClickListener() {
                    @Override
                    public void onSure() {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
                customDialog.show();
                break;
            case R.id.btn_cancel:
                CustomDialog customDialog1 = new CustomDialog(context, "已服务中取消订单，已支付费用不能全部退回，请联系客服人员或提交申请，等待客服人员人工处理，是否继续取消预约？", new CustomDialog.ClickListener() {
                    @Override
                    public void onSure() {

                    }

                    @Override
                    public void onCancel() {

                    }
                });
                customDialog1.show();
                break;
        }
    }

}
