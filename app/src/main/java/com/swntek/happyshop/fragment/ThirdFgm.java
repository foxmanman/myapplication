package com.swntek.happyshop.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.WgyneedUtil;
import com.swntek.happyshop.Util.Xlog;
import com.swntek.happyshop.activity.ConfirmCollectOrderActivity;
import com.swntek.happyshop.activity.GoodsOneActivity;
import com.swntek.happyshop.adapter.CollectAdapter;
import com.swntek.happyshop.base.BaseFrgment;
import com.swntek.happyshop.view.CustomListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/18 21:06
 * 邮箱：429883793@qq.com
 * 购物车
 */
public class ThirdFgm extends BaseFrgment implements View.OnClickListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.rl_left)
    RelativeLayout rlLeft;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.rl_right)
    RelativeLayout rlRight;//titlebar
    /*@Bind(R.id.rl_title)
    RelativeLayout rlTitle;*/
    @Bind(R.id.iv_collect_backcall)
    ImageView iv_collect_backcall;//返回
    @Bind(R.id.lv)
    CustomListView lv;//listView的具体
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;//下拉刷新
    @Bind(R.id.content_layout)
    RelativeLayout contentLayout;//listView的外边框
   /* @Bind(R.id.cb_collect_item_chose)
    CheckBox cbCollectItemChose;*/
   /* @Bind(R.id.tv_pay)
    TextView tvPay;*/
    @Bind(R.id.tv_buy)//结算
    TextView tvBuy;
    @Bind(R.id.ll_pay)//结算和合计的总体
    LinearLayout llPay;

    final int LOADING = 0;
    final int CHECKCHACHE = 1;
    boolean iscaching = false;



    Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOADING:
                    swipeContainer.setRefreshing(true);
                    break;

            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout view = (RelativeLayout) inflater.inflate(
                R.layout.fgm_third, null);
        ButterKnife.bind(this, view);
        initTitle();
        return view;
    }

    private void initTitle() {
        title.setText("购物车");
        WgyneedUtil.setSwipeColor(swipeContainer);
        swipeContainer.setOnRefreshListener(this);
        lv.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        handler.sendEmptyMessage(0);
        getData();

    }

    private void getData() {

        tvBuy.setOnClickListener(this);
        lv.setOnItemClickListener(this);
        CollectAdapter adapter = new CollectAdapter(getActivity(), null);
        lv.setAdapter(adapter);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        Xlog.d("onresume");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoodsOneActivity.launch(getActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_buy:
                ConfirmCollectOrderActivity.launch(getActivity());
                break;
        }
    }
}
