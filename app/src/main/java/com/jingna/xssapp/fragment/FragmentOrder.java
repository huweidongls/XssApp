package com.jingna.xssapp.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jingna.xssapp.R;
import com.jingna.xssapp.base.BaseFragment;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2019/5/10.
 */

public class FragmentOrder extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, null);

        ButterKnife.bind(this, view);

        return view;
    }
}
