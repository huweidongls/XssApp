package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseActivity {

    private Context context = SetActivity.this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set);

        ButterKnife.bind(SetActivity.this);

    }

    @OnClick({R.id.rl_back, R.id.rl_about})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_about:
                intent.setClass(context, AboutActivity.class);
                startActivity(intent);
                break;
        }
    }

}
