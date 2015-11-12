package com.swntek.happyshop.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.swntek.happyshop.R;
import com.swntek.happyshop.activity.GoodsOneActivity;
import com.swntek.happyshop.adapter.GoodsListAdapter;
import com.swntek.happyshop.Util.WgyneedUtil;
import com.swntek.happyshop.Util.Xlog;
import com.swntek.happyshop.base.BaseFrgment;
import com.swntek.happyshop.view.CustomListView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/18 21:06
 * 邮箱：429883793@qq.com
 * 产品列表(名字有待重构)
 *
 */
public class FirstFgm extends BaseFrgment implements AdapterView.OnItemClickListener, BaseSliderView.OnSliderClickListener, SwipeRefreshLayout.OnRefreshListener,CustomListView.OnLoadMoreListener {
    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.rl_left)
    RelativeLayout rlLeft;
    @Bind(R.id.tv_title_all)
    TextView title;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.rl_right)
    RelativeLayout rlRight;
    //    @Bind(R.id.rl_title)
//    RelativeLayout rlTitle;
    @Bind(R.id.lv)
    CustomListView lv;
    @Bind(R.id.swipe_container)
    SwipeRefreshLayout swipeContainer;
    /*@Bind(R.id.content_layout)
    RelativeLayout contentLayout;*/
    LayoutInflater inflater;

    final int LOADING = 0;
    final int CHECKCHACHE = 1;
    boolean iscaching = false;
    Handler handler = new Handler() {

        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case LOADING:
                    swipeContainer.setRefreshing(true);
                    break;

            }
        }
    };

    private SliderLayout mDemoSlider;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);


//        Fresco.initialize(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {
        this.inflater = inflater;

        View view = inflater.inflate(R.layout.fgm_first, null);
        ButterKnife.bind(getActivity(), view);
        initTitle();
        setBanner(view);
        initView();

        return view;
    }
    /**
     * 配置banner
     * 不确定是不是做这个功能
     */
    private void setBanner(View view) {
        RelativeLayout rl_banner = (RelativeLayout) inflater.inflate(R.layout.banner, null);
        mDemoSlider = (SliderLayout) rl_banner.findViewById(R.id.slider);
        HashMap<String, Integer> file_maps = new HashMap<String, Integer>();
        file_maps.put("热销中", R.mipmap.fairground01);
       /*file_maps.put("Big Bang Theory", R.drawable.fairground02);
        file_maps.put("House of Cards", R.drawable.fairground03);
        file_maps.put("Game of Thrones", R.drawable.fairground02);*/
        for (String name : file_maps.keySet()) {
            TextSliderView textSliderView = new TextSliderView(getActivity());
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image("res://包名(实际可以是任何字符串甚至留空)/" + file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.CenterCrop)
                    .setOnSliderClickListener(this);
            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra", "1");
            mDemoSlider.addSlider(textSliderView);
        }

        mDemoSlider.setPresetTransformer(SliderLayout.Transformer.Accordion);
        mDemoSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mDemoSlider.setCustomAnimation(new DescriptionAnimation());
        mDemoSlider.setDuration(0);
        lv.addHeaderView(rl_banner, null, true);
    }


    private void initTitle() {
       WgyneedUtil.setSwipeColor(swipeContainer);
       swipeContainer.setOnRefreshListener(this);
        title.setText("产品");

    }
    private void initView(){
        GoodsListAdapter adapter = new GoodsListAdapter(getActivity());
        lv.setAdapter(adapter);
        lv.requestFocus();
        lv.setOnItemClickListener(this);
//        lv.setOnLoadListener(this);

        lv.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
        mDemoSlider.startAutoCycle();
        Xlog.d("onresume");
    }

    @Override
    public void onPause() {
        mDemoSlider.stopAutoCycle();
        super.onPause();
    }

    @Override
    public void onStop() {
//        mDemoSlider.stopAutoCycle();
        super.onStop();

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
//        ButterKnife.unbind(this);
    }

    @Override
    public void onRefresh() {

    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Xlog.d(slider.getBundle().getString("extra"));
    }

    @Override
    public void onLoadMore() {
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoodsOneActivity.launch(getActivity());
    }
}
