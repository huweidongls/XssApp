package com.jingna.xssapp.page;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.MyAddressAdapter;
import com.jingna.xssapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MyAddressActivity extends BaseActivity {

    private Context context = MyAddressActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_message_null)
    ImageView ivMessageNull;

    private MyAddressAdapter adapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_address);

        ButterKnife.bind(MyAddressActivity.this);
        initData();

    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        if(mList.size()>0){
            adapter = new MyAddressAdapter(mList);
            LinearLayoutManager manager = new LinearLayoutManager(context);
            manager.setOrientation(LinearLayoutManager.VERTICAL);
            recyclerView.setLayoutManager(manager);
            recyclerView.setAdapter(adapter);
            recyclerView.setVisibility(View.VISIBLE);
            ivMessageNull.setVisibility(View.GONE);
        }else {
            recyclerView.setVisibility(View.GONE);
            ivMessageNull.setVisibility(View.VISIBLE);
        }

    }

    @OnClick({R.id.rl_back, R.id.btn_insert})
    public void onClick(View view){
        Intent intent = new Intent();
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.btn_insert:
                intent.setClass(context, InsertAddressActivity.class);
                startActivity(intent);
                break;
        }
    }

}
