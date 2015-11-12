package com.swntek.happyshop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.ToastUtil;
import com.swntek.happyshop.base.BaseActivity;
import com.swntek.happyshop.fragment.GoodsCommentFgm;
import com.swntek.happyshop.fragment.GoodsIntroFgm;
import com.swntek.happyshop.fragment.GoodsParamFgm;
import com.swntek.happyshop.fragment.GoodsPromiseFgm;
import com.swntek.happyshop.fragment.OrderDoneFgm;
import com.swntek.happyshop.fragment.WaitDeliverFgm;
import com.swntek.happyshop.fragment.WaitPayFgm;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 作者：wgyhello on 15/10/21 14:35
 * 邮箱：429883793@qq.com
 * 我的订单界面
 */
public class MyOrderActivity extends BaseActivity implements View.OnClickListener {


    FragmentPagerAdapter mAdapter;
    List<Fragment> mtabs;
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.rl_left)
    RelativeLayout rlLeft;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.rl_right)
    RelativeLayout rlRight;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.tab_layout)
    TabLayout tabLayout;
    @Bind(R.id.vp)
    ViewPager vp;
    @Bind(R.id.content_layout)
    RelativeLayout contentLayout;


    public static void launch(Context context) {
        Intent intent = new Intent(context, MyOrderActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {

        final List<String> titles = new ArrayList<>();
        titles.add("待付款");
        titles.add("待收货");
        titles.add("已完成");

        tabLayout.addTab(tabLayout.newTab().setText("待付款"));
        tabLayout.addTab(tabLayout.newTab().setText("待收货"));
        tabLayout.addTab(tabLayout.newTab().setText("已完成"));

        mtabs = new ArrayList<>();

        WaitPayFgm fgm1 = new WaitPayFgm();
        WaitDeliverFgm fgm2 = new WaitDeliverFgm();
        OrderDoneFgm fgm3 = new OrderDoneFgm();
        mtabs.add(fgm1);
        mtabs.add(fgm2);
        mtabs.add(fgm3);

        mAdapter = new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public int getCount() {
                return mtabs.size();
            }

            @Override
            public void destroyItem(ViewGroup container, int position,
                                    Object object) {
                // super.destroyItem(container, position, object);
            }

            @Override
            public Fragment getItem(int arg0) {
                return mtabs.get(arg0);
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return titles.get(position);
            }
        };

        tabLayout.setTabsFromPagerAdapter(mAdapter);
        vp.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(vp);



    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_buy:

                ConfirmOrderActivity.launch(this);

                break;
            case R.id.tv_collect:
                ToastUtil.toast(this, "已加入购物车", contentLayout, 1);
                break;
        }

    }
}