package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.view.View;

import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZixunDetailsActivity extends BaseActivity {

    private Context context = ZixunDetailsActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zixun_details);

        ButterKnife.bind(ZixunDetailsActivity.this);

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
