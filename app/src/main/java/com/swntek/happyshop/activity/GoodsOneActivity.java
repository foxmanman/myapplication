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
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.ToastUtil;
import com.swntek.happyshop.base.BaseActivity;
import com.swntek.happyshop.fragment.GoodsCommentFgm;
import com.swntek.happyshop.fragment.GoodsIntroFgm;
import com.swntek.happyshop.fragment.GoodsParamFgm;
import com.swntek.happyshop.fragment.GoodsPromiseFgm;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

public class GoodsOneActivity extends BaseActivity implements View.OnClickListener{

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

    FragmentPagerAdapter mAdapter;
    List<Fragment> mtabs;
    @Bind(R.id.tv_collect)
    TextView tvCollect;
    @Bind(R.id.tv_buy)
    TextView tvBuy;
    @Bind(R.id.ll_collect_buy)
    LinearLayout llCollectBuy;


    public static void launch(Context context) {
        Intent intent = new Intent(context, GoodsOneActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_one);
        ButterKnife.bind(this);
        initView();

    }

    private void initView() {

        title.setText("商品详情");

        final List<String> titles = new ArrayList<>();
        titles.add("商品介绍");
        titles.add("规格参数");
        titles.add("服务承诺");
        titles.add("评论(1)");

        tabLayout.addTab(tabLayout.newTab().setText("商品介绍"));
        tabLayout.addTab(tabLayout.newTab().setText("规格参数"));
        tabLayout.addTab(tabLayout.newTab().setText("服务承诺"));
        tabLayout.addTab(tabLayout.newTab().setText("评论(1)"));

        mtabs = new ArrayList<>();

        GoodsIntroFgm fgm1 = new GoodsIntroFgm();
        GoodsParamFgm fgm2 = new GoodsParamFgm();
        GoodsPromiseFgm fgm3 = new GoodsPromiseFgm();
        GoodsCommentFgm fgm4 = new GoodsCommentFgm();
        mtabs.add(fgm1);
        mtabs.add(fgm2);
        mtabs.add(fgm3);
        mtabs.add(fgm4);

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
        tvBuy.setOnClickListener(this);
        tvCollect.setOnClickListener(this);


    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_buy:

                ConfirmOrderActivity.launch(this);

                break;
            case R.id.tv_collect:
                ToastUtil.toast(this,"已加入购物车",contentLayout,1);
                break;
        }

    }
}