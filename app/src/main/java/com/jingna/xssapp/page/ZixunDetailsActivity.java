package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.bean.NewsContentBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.Base64Utils;
import com.jingna.xssapp.util.HtmlFromUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZixunDetailsActivity extends BaseActivity {

    private Context context = ZixunDetailsActivity.this;

    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_time)
    TextView tvTime;

    private String id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zixun_details);

        id = getIntent().getStringExtra("id");
        ButterKnife.bind(ZixunDetailsActivity.this);
        initData();

    }

    private void initData() {

        ViseHttp.POST(NetUrl.news_contentUrl)
                .addParam("app_key", getToken(NetUrl.BASE_URL+NetUrl.news_contentUrl))
                .addParam("newsid", id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                NewsContentBean bean = gson.fromJson(data, NewsContentBean.class);
                                tvTitle.setText(bean.getObj().getTitle());
                                tvTime.setText(bean.getObj().getAddtime());
                                String content = bean.getObj().getContent();
                                content = Base64Utils.setDecrypt(content);
                                HtmlFromUtils.setTextFromHtml(ZixunDetailsActivity.this, tv, content);
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
