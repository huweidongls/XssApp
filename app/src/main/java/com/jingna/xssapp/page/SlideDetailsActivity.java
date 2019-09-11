package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jingna.xssapp.MainActivity;
import com.jingna.xssapp.R;
import com.jingna.xssapp.bean.NewsContentBean;
import com.jingna.xssapp.bean.SlideDetailsBean;
import com.jingna.xssapp.net.NetUrl;
import com.jingna.xssapp.util.Base64Utils;
import com.jingna.xssapp.util.HtmlFromUtils;
import com.jingna.xssapp.util.SpUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;

import org.json.JSONException;
import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SlideDetailsActivity extends AppCompatActivity {
    private Context context = SlideDetailsActivity.this;
    @BindView(R.id.tv_time)
    TextView tv_time;
    @BindView(R.id.tv)
    TextView tv;
    private String id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_details);
        id = getIntent().getStringExtra("id");
        ButterKnife.bind(SlideDetailsActivity.this);
        initData();
    }
    private void initData(){
        ViseHttp.POST(NetUrl.SlideDetaileRow)
                .addParam("id",id)
                .request(new ACallback<String>() {
                    @Override
                    public void onSuccess(String data) {
                        try {
                            JSONObject jsonObject = new JSONObject(data);
                            if(jsonObject.optInt("code") == 200){
                                Gson gson = new Gson();
                                SlideDetailsBean bean = gson.fromJson(data,SlideDetailsBean.class);
                                tv_time.setText(bean.getObj().getAddtime());
                                String content = bean.getObj().getContent();
                                content = Base64Utils.setDecrypt(content);
                                HtmlFromUtils.setTextFromHtml(SlideDetailsActivity.this, tv, content);
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
