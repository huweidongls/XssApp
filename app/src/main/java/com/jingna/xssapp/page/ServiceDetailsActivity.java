package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.ServiceDetailsCommentAdapter;
import com.jingna.xssapp.adapter.ServiceDetailsPriceAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.ServiceContentBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.Base64Utils;
import com.jingna.xssapp.util.HtmlFromUtils;
import com.jingna.xssapp.util.Logger;
import com.jingna.xssapp.util.SpUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServiceDetailsActivity extends BaseActivity {

    private Context context = ServiceDetailsActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_title)
    ImageView ivTitle;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_text)
    TextView tvText;
    @BindView(R.id.tv_reference)
    TextView tvReference;
    @BindView(R.id.tv_servicestandards)
    TextView tvServicestandards;
    @BindView(R.id.tv_professionaltools)
    TextView tvProfessionaltools;
    @BindView(R.id.tv_serviceguarantee)
    TextView tvServiceguarantee;
    @BindView(R.id.rv_price)
    RecyclerView rvPrice;
    @BindView(R.id.tv_comment_num)
    TextView tvCommentNum;
    @BindView(R.id.ll_comment)
    LinearLayout llComment;
    @BindView(R.id.tv_comment)
    TextView tvComment;

    private ServiceDetailsCommentAdapter adapter;
    private List<ServiceContentBean.ObjBean.EvaluateBean> mList;
    private ServiceDetailsPriceAdapter priceAdapter;
    private List<ServiceContentBean.ObjBean.PriceBean> priceList;

    private String id = "";
    private ServiceContentBean bean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_details);

        id = getIntent().getStringExtra("id");
        ButterKnife.bind(ServiceDetailsActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.serviceContentUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.serviceContentUrl))
                .addParam("fid", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                bean = gson.fromJson(data, ServiceContentBean.class);
                                Glide.with(context).load(NetUrl.BASE_URL+bean.getObj().getBackimg()).into(ivTitle);
                                tvName.setText(bean.getObj().getServicename());
                                tvText.setText(bean.getObj().getServicetext());
                                String reference = bean.getObj().getReference();
                                reference = Base64Utils.setDecrypt(reference);
                                HtmlFromUtils.setTextFromHtml(ServiceDetailsActivity.this, tvReference, reference);
                                String servicestandards = bean.getObj().getServicestandards();
                                servicestandards = Base64Utils.setDecrypt(servicestandards);
                                HtmlFromUtils.setTextFromHtml(ServiceDetailsActivity.this, tvServicestandards, servicestandards);
                                String professionaltools = bean.getObj().getProfessionaltools();
                                professionaltools = Base64Utils.setDecrypt(professionaltools);
                                HtmlFromUtils.setTextFromHtml(ServiceDetailsActivity.this, tvProfessionaltools, professionaltools);
                                String serviceguarantee = bean.getObj().getServiceguarantee();
                                serviceguarantee = Base64Utils.setDecrypt(serviceguarantee);
                                HtmlFromUtils.setTextFromHtml(ServiceDetailsActivity.this, tvServiceguarantee, serviceguarantee);
                                priceList = bean.getObj().getPrice();
                                priceAdapter = new ServiceDetailsPriceAdapter(priceList);
                                LinearLayoutManager manager = new LinearLayoutManager(context){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                rvPrice.setLayoutManager(manager);
                                rvPrice.setAdapter(priceAdapter);
                                mList = bean.getObj().getEvaluate();
                                if(mList.size()>0){
                                    tvComment.setVisibility(View.GONE);
                                    tvCommentNum.setText("用户评价（"+mList.size()+"）");
                                    adapter = new ServiceDetailsCommentAdapter(mList);
                                    LinearLayoutManager manager1 = new LinearLayoutManager(context){
                                        @Override
                                        public boolean canScrollVertically() {
                                            return false;
                                        }
                                    };
                                    manager1.setOrientation(LinearLayoutManager.VERTICAL);
                                    recyclerView.setLayoutManager(manager1);
                                    recyclerView.setAdapter(adapter);
                                }else {
                                    tvCommentNum.setText("用户评价（0）");
                                    tvComment.setVisibility(View.VISIBLE);
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

    @OnClick({R.id.rl_back, R.id.tv_sure})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.tv_sure:
                if(SpUtils.getUid(context).equals("0")){
                    intent.setClass(context, LoginActivity.class);
                    startActivity(intent);
                }else {
                    intent.setClass(context, BookingOrderActivity.class);
                    intent.putExtra("id", bean.getObj().getId());
                    intent.putExtra("name", bean.getObj().getServicename());
                    intent.putExtra("ismore", bean.getObj().getSpecifications_status());
                    startActivity(intent);
                    finish();
                }
                break;
        }
    }

}
