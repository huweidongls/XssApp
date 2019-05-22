package com.jingna.xssapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.FenleiLeftAdapter;
import com.jingna.xssapp.adapter.FenleiRightAdapter;
import com.jingna.xssapp.base.BaseFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/5/10.
 */

public class FragmentService extends BaseFragment {

    @BindView(R.id.rv_fenlei)
    RecyclerView rvFenlei;
    @BindView(R.id.rv_right)
    RecyclerView rvRight;

    private FenleiLeftAdapter leftAdapter;
    private List<String> mList;
    private FenleiRightAdapter rightAdapter;
    private List<String> mList1;

    private String id = "";
    private String city = "";

    public static FragmentService newInstance(String id, String city) {
        FragmentService newFragment = new FragmentService();
        Bundle bundle = new Bundle();
        bundle.putString("id", id);
        bundle.putString("city", city);
        newFragment.setArguments(bundle);
        return newFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_service, null);

        Bundle args = getArguments();
        if (args != null) {
            id = args.getString("id");
            city = args.getString("city");
        }
        ButterKnife.bind(this, view);
        initData();

        return view;
    }

    private void initData() {

        mList = new ArrayList<>();
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        mList.add("");
        leftAdapter = new FenleiLeftAdapter(mList);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        rvFenlei.setLayoutManager(manager);
        rvFenlei.setAdapter(leftAdapter);

        mList1 = new ArrayList<>();
        mList1.add("");
        mList1.add("");
        mList1.add("");
        rightAdapter = new FenleiRightAdapter(mList1);
        LinearLayoutManager manager1 = new LinearLayoutManager(getContext());
        manager1.setOrientation(LinearLayoutManager.VERTICAL);
        rvRight.setLayoutManager(manager1);
        rvRight.setAdapter(rightAdapter);

    }
}
