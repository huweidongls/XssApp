package com.jingna.xssapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.jingna.xssapp.app.MyApplication;
import com.jingna.xssapp.base.BaseActivity;
import com.jingna.xssapp.fragment.FragmentIndex;
import com.jingna.xssapp.fragment.FragmentMy;
import com.jingna.xssapp.fragment.FragmentOrder;
import com.jingna.xssapp.fragment.FragmentService;
import com.jingna.xssapp.util.ToastUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    private Context context = MainActivity.this;

    @BindView(R.id.menu_index)
    ImageButton ibIndex;
    @BindView(R.id.menu_service)
    ImageButton ibService;
    @BindView(R.id.menu_order)
    ImageButton ibOrder;
    @BindView(R.id.menu_my)
    ImageButton ibMy;
    @BindView(R.id.menu1)
    RelativeLayout rl1;
    @BindView(R.id.menu2)
    RelativeLayout rl2;
    @BindView(R.id.menu3)
    RelativeLayout rl3;
    @BindView(R.id.menu4)
    RelativeLayout rl4;

    private long exitTime = 0;

    private List<Fragment> fragmentList = new ArrayList<>();
    private MenuOnClickListener listener = new MenuOnClickListener();

    FragmentManager fragmentManager = getSupportFragmentManager();
    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(MainActivity.this);
        init();

    }

    /**
     * 初始化各个组件
     */
    private void init() {

        ibIndex.setOnClickListener(listener);
        ibService.setOnClickListener(listener);
        ibOrder.setOnClickListener(listener);
        ibMy.setOnClickListener(listener);

        rl1.setOnClickListener(listener);
        rl2.setOnClickListener(listener);
        rl3.setOnClickListener(listener);
        rl4.setOnClickListener(listener);
        Fragment fragmentIndex = new FragmentIndex();
        Fragment fragmentService = new FragmentService();
        Fragment fragmentOrder = new FragmentOrder();
        Fragment fragmentMy = new FragmentMy();

        fragmentList.add(fragmentIndex);
        fragmentList.add(fragmentService);
        fragmentList.add(fragmentOrder);
        fragmentList.add(fragmentMy);

        fragmentTransaction.add(R.id.fl_container, fragmentIndex);
        fragmentTransaction.add(R.id.fl_container, fragmentService);
        fragmentTransaction.add(R.id.fl_container, fragmentOrder);
        fragmentTransaction.add(R.id.fl_container, fragmentMy);

        fragmentTransaction.show(fragmentIndex).hide(fragmentService).hide(fragmentOrder).hide(fragmentMy);
        fragmentTransaction.commit();

        selectButton(ibIndex);

    }

    private class MenuOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.menu_index:
                    selectButton(ibIndex);
                    switchFragment(0);
                    break;
                case R.id.menu_service:
                    selectButton(ibService);
                    switchFragment(1);
                    break;
                case R.id.menu_order:
                    selectButton(ibOrder);
                    switchFragment(2);
                    break;
                case R.id.menu_my:
                    selectButton(ibMy);
                    switchFragment(3);
                    break;
                case R.id.menu1:
                    selectButton(ibIndex);
                    switchFragment(0);
                    break;
                case R.id.menu2:
                    selectButton(ibService);
                    switchFragment(1);
                    break;
                case R.id.menu3:
                    selectButton(ibOrder);
                    switchFragment(2);
                    break;
                case R.id.menu4:
                    selectButton(ibMy);
                    switchFragment(3);
                    break;
            }

        }
    }

    /**
     * 选择隐藏与显示的Fragment
     *
     * @param index 显示的Frgament的角标
     */
    public void switchFragment(int index) {
        fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        for (int i = 0; i < fragmentList.size(); i++) {
            if (index == i) {
                fragmentTransaction.show(fragmentList.get(index));
            } else {
                fragmentTransaction.hide(fragmentList.get(i));
            }
        }
        fragmentTransaction.commit();
    }

    /**
     * 控制底部菜单按钮的选中
     *
     * @param v
     */
    public void selectButton(View v) {
        ibIndex.setSelected(false);
        ibService.setSelected(false);
        ibOrder.setSelected(false);
        ibMy.setSelected(false);
        v.setSelected(true);
    }

    @Override
    public void onBackPressed() {
        backtrack();
    }

    /**
     * 退出销毁所有activity
     */
    private void backtrack() {
        if (System.currentTimeMillis() - exitTime > 2000) {
            ToastUtil.showShort(context, "再按一次退出程序");
            exitTime = System.currentTimeMillis();
        } else {
            MyApplication.getInstance().exit();
            exitTime = 0;
        }
    }

}
