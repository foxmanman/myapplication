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
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.WgyneedUtil;
import com.swntek.happyshop.Util.Xlog;
import com.swntek.happyshop.activity.GoodsTypeActivity;
import com.swntek.happyshop.activity.SearchActivity;
import com.swntek.happyshop.adapter.GoodsTypeAdapter;
import com.swntek.happyshop.base.BaseFrgment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/18 21:06
 * 邮箱：429883793@qq.com
 * 分类
 */
public class SecondFgm extends BaseFrgment implements View.OnClickListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {


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
   // @Bind(R.id.tv_search)
   /* TextView tvSearch;
    @Bind(R.id.ll_search)*/
    //RelativeLayout llSearch;
    @Bind(R.id.lv_calssifypro)
    ListView lv_calssifypro;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
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
                R.layout.fgm_second, null);
        ButterKnife.bind(this, view);
//        Fresco.initialize(getActivity());
        initTitle();
        getData();
        return view;
    }

    private void initTitle() {
        //llSearch.setOnClickListener(this);
        WgyneedUtil.setSwipeColor(swipeContainer);
        swipeContainer.setOnRefreshListener(this);
        lv_calssifypro.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        handler.sendEmptyMessage(0);

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
        ButterKnife.unbind(this);
        super.onDestroyView();

    }

    private void getData(){
        GoodsTypeAdapter adapter = new GoodsTypeAdapter(getContext());
        lv_calssifypro.setAdapter(adapter);
        lv_calssifypro.setOnItemClickListener(this);

    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoodsTypeActivity.launch(getActivity());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.lv_calssifypro:
                //SearchActivity.launch(getActivity());
                break;
        }
    }
}
