package com.swntek.happyshop.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.Xlog;
import com.swntek.happyshop.adapter.WaitPayParentAdapter;
import com.swntek.happyshop.base.BaseFrgment;
import com.swntek.happyshop.view.CustomListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/18 21:06
 * 邮箱：429883793@qq.com
 * 我的订单－》待付款
 */
public class WaitPayFgm extends BaseFrgment implements SwipeRefreshLayout.OnRefreshListener {

    final int LOADING = 0;
    final int CHECKCHACHE = 1;
    boolean iscaching = false;
    Handler handler = new Handler() {

        public void handleMessage(Message msg) {
            switch (msg.what) {
                case LOADING:
//                    swipeContainer.setRefreshing(true);
                    break;

            }
        }
    };
    @Bind(R.id.lv)
    CustomListView lv;
    @Bind(R.id.content_layout)
    RelativeLayout contentLayout;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        RelativeLayout view = (RelativeLayout) inflater.inflate(
                R.layout.fgm_wait_pay, null);
        ButterKnife.bind(this, view);
        initTitle();
        return view;
    }

    private void initTitle() {
        WaitPayParentAdapter adapter = new WaitPayParentAdapter(getActivity(),null);
        lv.setAdapter(adapter);

//        WgyneedUtil.setSwipeColor(swipeContainer);
//        swipeContainer.setOnRefreshListener(this);
//        lv.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        handler.sendEmptyMessage(0);
//        getData();

    }

//    private void getData(){
//        CollectAdapter adapter = new CollectAdapter(getActivity(),null);
//        lv.setAdapter(adapter);
//    }

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
//        ButterKnife.unbind(this);
        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {

    }
}
