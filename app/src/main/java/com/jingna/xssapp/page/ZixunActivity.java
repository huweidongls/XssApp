package com.jingna.xssapp.page;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.jingna.xssapp.R;
import com.jingna.xssapp.adapter.ViewpagerAdapter;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.fragment.FragmentAllOrder;
import com.jingna.xssapp.fragment.FragmentCompleteOrder;
import com.jingna.xssapp.fragment.FragmentIngOrder;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ZixunActivity extends BaseActivity {

    private Context context = ZixunActivity.this;

    @BindView(R.id.vp)
    ViewPager mViewPager;
    @BindView(R.id.tv_all)
    TextView tvAll;
    @BindView(R.id.view_all)
    View viewAll;
    @BindView(R.id.tv_lingquan)
    TextView tvLingquan;
    @BindView(R.id.view_lingquan)
    View viewLingquan;
    @BindView(R.id.tv_qidai1)
    TextView tvQidai1;
    @BindView(R.id.view_qidai1)
    View viewQidai1;
    @BindView(R.id.tv_qidai2)
    TextView tvQidai2;
    @BindView(R.id.view_qidai2)
    View viewQidai2;

    private FragmentManager mFragmentManager;
    private ViewpagerAdapter mViewPagerFragmentAdapter;
    private List<Fragment> fragmentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_zixun);

        ButterKnife.bind(ZixunActivity.this);
        mFragmentManager = getSupportFragmentManager();
        initData();

    }

    private void initData() {

        fragmentList = new ArrayList<>();
        fragmentList.add(new FragmentAllOrder());
        fragmentList.add(new FragmentIngOrder());
        fragmentList.add(new FragmentCompleteOrder());
        fragmentList.add(new FragmentCompleteOrder());
        mViewPagerFragmentAdapter = new ViewpagerAdapter(mFragmentManager, fragmentList);
        mViewPager.setAdapter(mViewPagerFragmentAdapter);
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:
                        tvAll.setTextColor(Color.parseColor("#F83030"));
                        tvLingquan.setTextColor(Color.parseColor("#333333"));
                        tvQidai1.setTextColor(Color.parseColor("#333333"));
                        tvQidai2.setTextColor(Color.parseColor("#333333"));
                        viewAll.setVisibility(View.VISIBLE);
                        viewLingquan.setVisibility(View.GONE);
                        viewQidai1.setVisibility(View.GONE);
                        viewQidai2.setVisibility(View.GONE);
                        break;
                    case 1:
                        tvAll.setTextColor(Color.parseColor("#333333"));
                        tvLingquan.setTextColor(Color.parseColor("#F83030"));
                        tvQidai1.setTextColor(Color.parseColor("#333333"));
                        tvQidai2.setTextColor(Color.parseColor("#333333"));
                        viewAll.setVisibility(View.GONE);
                        viewLingquan.setVisibility(View.VISIBLE);
                        viewQidai1.setVisibility(View.GONE);
                        viewQidai2.setVisibility(View.GONE);
                        break;
                    case 2:
                        tvAll.setTextColor(Color.parseColor("#333333"));
                        tvLingquan.setTextColor(Color.parseColor("#333333"));
                        tvQidai1.setTextColor(Color.parseColor("#F83030"));
                        tvQidai2.setTextColor(Color.parseColor("#333333"));
                        viewAll.setVisibility(View.GONE);
                        viewLingquan.setVisibility(View.GONE);
                        viewQidai1.setVisibility(View.VISIBLE);
                        viewQidai2.setVisibility(View.GONE);
                        break;
                    case 3:
                        tvAll.setTextColor(Color.parseColor("#333333"));
                        tvLingquan.setTextColor(Color.parseColor("#333333"));
                        tvQidai1.setTextColor(Color.parseColor("#333333"));
                        tvQidai2.setTextColor(Color.parseColor("#F83030"));
                        viewAll.setVisibility(View.GONE);
                        viewLingquan.setVisibility(View.GONE);
                        viewQidai1.setVisibility(View.GONE);
                        viewQidai2.setVisibility(View.VISIBLE);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    @OnClick({R.id.rl_back, R.id.rl_all, R.id.rl_lingquan, R.id.rl_qidai1, R.id.rl_qidai2})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.rl_back:
                finish();
                break;
            case R.id.rl_all:
                mViewPager.setCurrentItem(0);
                break;
            case R.id.rl_lingquan:
                mViewPager.setCurrentItem(1);
                break;
            case R.id.rl_qidai1:
                mViewPager.setCurrentItem(2);
                break;
            case R.id.rl_qidai2:
                mViewPager.setCurrentItem(3);
                break;
        }
    }

}
