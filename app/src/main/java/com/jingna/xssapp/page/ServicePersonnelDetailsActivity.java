package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.ServicePersonnelItemAdapter;
import com.jingna.xssapp.base.BaseActivity;

import java.util.ArrayList;
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

    private ServicePersonnelItemAdapter itemAdapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_personnel_details);

        ButterKnife.bind(ServicePersonnelDetailsActivity.this);
        initData();

    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        itemAdapter = new ServicePersonnelItemAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(context);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rvSign.setLayoutManager(manager);
        rvSign.setAdapter(itemAdapter);

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
