package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.ServicePersonnelDetailsImgAdapter;
import com.jingna.xssapp.adapter.ServicePersonnelDetailsItemAdapter;
import com.jingna.xssapp.adapter.ServicePersonnelDetailsTrainAdapter;
import com.jingna.xssapp.adapter.ServicePersonnelDetailsWorkAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.WokerContentBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.Base64Utils;
import com.jingna.xssapp.util.HtmlFromUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ServicePersonnelDetailsActivity extends BaseActivity {

    private Context context = ServicePersonnelDetailsActivity.this;

    @BindView(R.id.rv_sign)
    RecyclerView rvSign;
    @BindView(R.id.rv_work)
    RecyclerView rvWork;
    @BindView(R.id.iv_avatar)
    ImageView ivAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_age)
    TextView tvAge;
    @BindView(R.id.tv_service_num)
    TextView tvServiceNum;
    @BindView(R.id.tv_essentialinformation)
    TextView tvEssentialinformation;
    @BindView(R.id.rv_train)
    RecyclerView rvTrain;
    @BindView(R.id.rv_img)
    RecyclerView rvImg;

    private ServicePersonnelDetailsItemAdapter itemAdapter;
    private List<WokerContentBean.ObjBean.TableBean> mList;
    private ServicePersonnelDetailsWorkAdapter workAdapter;
    private List<WokerContentBean.ObjBean.ExperienceBean> workList;
    private ServicePersonnelDetailsTrainAdapter trainAdapter;
    private List<WokerContentBean.ObjBean.TrainBean> trainList;
    private ServicePersonnelDetailsImgAdapter imgAdapter;
    private List<WokerContentBean.ObjBean.ImgBean> imgList;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_personnel_details);

        id = getIntent().getStringExtra("id");
        ButterKnife.bind(ServicePersonnelDetailsActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.wokerContentUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.wokerContentUrl))
                .addParam("wid", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                WokerContentBean bean = gson.fromJson(data, WokerContentBean.class);
                                Glide.with(context).load(NetUrl.BASE_URL+bean.getObj().getHeadimg()).into(ivAvatar);
                                tvName.setText(bean.getObj().getName());
                                tvAge.setText(bean.getObj().getAge()+"岁");
                                tvServiceNum.setText("服务次数："+bean.getObj().getServicenum()+"次");
                                String essentialinformation = bean.getObj().getEssentialinformation();
                                essentialinformation = Base64Utils.setDecrypt(essentialinformation);
                                HtmlFromUtils.setTextFromHtml(ServicePersonnelDetailsActivity.this, tvEssentialinformation, essentialinformation);
                                workList = bean.getObj().getExperience();
                                workAdapter = new ServicePersonnelDetailsWorkAdapter(workList, ServicePersonnelDetailsActivity.this);
                                LinearLayoutManager manager = new LinearLayoutManager(context){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager.setOrientation(LinearLayoutManager.VERTICAL);
                                rvWork.setLayoutManager(manager);
                                rvWork.setAdapter(workAdapter);
                                trainList = bean.getObj().getTrain();
                                trainAdapter = new ServicePersonnelDetailsTrainAdapter(trainList);
                                LinearLayoutManager manager1 = new LinearLayoutManager(context){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                manager1.setOrientation(LinearLayoutManager.VERTICAL);
                                rvTrain.setLayoutManager(manager1);
                                rvTrain.setAdapter(trainAdapter);
                                imgList = bean.getObj().getImg();
                                imgAdapter = new ServicePersonnelDetailsImgAdapter(imgList);
                                GridLayoutManager manager2 = new GridLayoutManager(context, 2){
                                    @Override
                                    public boolean canScrollVertically() {
                                        return false;
                                    }
                                };
                                rvImg.setLayoutManager(manager2);
                                rvImg.setAdapter(imgAdapter);
                                mList = bean.getObj().getTable();
                                itemAdapter = new ServicePersonnelDetailsItemAdapter(mList);
                                LinearLayoutManager manager3 = new LinearLayoutManager(context);
                                manager3.setOrientation(LinearLayoutManager.HORIZONTAL);
                                rvSign.setLayoutManager(manager3);
                                rvSign.setAdapter(itemAdapter);
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

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
