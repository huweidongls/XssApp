package com.jingna.xssapp.page;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.MessageAdapter;
import com.jingna.xssapp.base.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MessageActivity extends BaseActivity {

    private Context context = MessageActivity.this;

    @BindView(R.id.rv)
    RecyclerView recyclerView;
    @BindView(R.id.iv_message_null)
    ImageView ivMessageNull;

    private MessageAdapter adapter;
    private List<String> mList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);

        ButterKnife.bind(MessageActivity.this);
        initData();

    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        if(mList.size()>0){
            adapter = new MessageAdapter(mList);
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

    @OnClick({R.id.rl_back})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
        }
    }

}
