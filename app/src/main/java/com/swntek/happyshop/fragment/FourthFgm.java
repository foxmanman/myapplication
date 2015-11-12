package com.swntek.happyshop.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.Xlog;
import com.swntek.happyshop.activity.AboutUsActivity;
import com.swntek.happyshop.activity.AddressActivity;
import com.swntek.happyshop.activity.ChangePwdActivity;
import com.swntek.happyshop.activity.LoginActivity;
import com.swntek.happyshop.activity.MyOrderActivity;
import com.swntek.happyshop.activity.RegistActivity;
import com.swntek.happyshop.activity.SettingActivity;
import com.swntek.happyshop.base.BaseFrgment;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：wgyhello on 15/10/18 21:06
 * 邮箱：429883793@qq.com
 * 我的
 */
public class FourthFgm extends BaseFrgment implements View.OnClickListener, SwipeRefreshLayout.OnRefreshListener {

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
    @Bind(R.id.tv_no_personal_setting)
    ImageView tvNoPersonalSetting;//整个布局
    @Bind(R.id.iv_personal_not_login)
    CircleImageView ivPersonalNotLogin;//图像
    @Bind(R.id.btn_personal_regist)
    TextView btnPersonalRegist;//我的积分
    @Bind(R.id.btn_personal_login)
    TextView btnPersonalLogin;//我的余额
    @Bind(R.id.rl_personal_not_login)
    RelativeLayout rlPersonalNotLogin;
    @Bind(R.id.tv_title)
    TextView tv_title;//用户名

    @Bind(R.id.iv_personal_login_bg)
    ImageView ivPersonalLoginBg;
    @Bind(R.id.tv_personal_setting)
    ImageView tvPersonalSetting;
    @Bind(R.id.iv_personal_login_header)
    CircleImageView ivPersonalLoginHeader;
    @Bind(R.id.tv_personal_username)
    TextView tvPersonalUsername;
    @Bind(R.id.tv_personal_vip_level)
    TextView tvPersonalVipLevel;//vip
    @Bind(R.id.rl_personal_login)
    RelativeLayout rlPersonalLogin;
    @Bind(R.id.tv_personal_order)
    TextView tvPersonalOrder;//积分商城积分
  /*  @Bind(R.id.tv_personal_order_tips)
    TextView tvPersonalOrderTips;*/
    @Bind(R.id.ll_personal_my_buylist)//积分商城总体
    LinearLayout rlPersonalMyBuylist;
    @Bind(R.id.ll_personal_my_collect)//我的订单总体
    LinearLayout rlPersonalMyCollect;
    @Bind(R.id.ll_personal_my_love)//待请假订单
    LinearLayout rlPersonalMyLove;
    @Bind(R.id.ll_personal_my_addr)//我的收藏
    LinearLayout rlPersonalMyAddr;
    @Bind(R.id.tv_about_us)
    TextView tvAboutUs;
    /*@Bind(R.id.tv_personal_chat_tips)
    TextView tvPersonalChatTips;*/
    @Bind(R.id.ll_personal_my_about_us)//优惠券
    LinearLayout rlPersonalMyAboutUs;
//    @Bind(R.id.rl_personal_my_update_pwd)
//    RelativeLayout rlPersonalMyUpdatePwd;
//    @Bind(R.id.ll_personal_my_quit_contain)
//    RelativeLayout llPersonalMyQuitContain;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LinearLayout view = (LinearLayout) inflater.inflate(
                R.layout.activity_personal, null);
        ButterKnife.bind(this, view);
        initView();
        initTitle();
        return view;
    }

    private void initView(){

        btnPersonalRegist.setOnClickListener(this);
        btnPersonalLogin.setOnClickListener(this);
        tvNoPersonalSetting.setOnClickListener(this);
        rlPersonalMyBuylist.setOnClickListener(this);
        rlPersonalMyAddr.setOnClickListener(this);
//        rlPersonalMyUpdatePwd.setOnClickListener(this);
        rlPersonalMyAboutUs.setOnClickListener(this);

    }

    private void initTitle() {
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        handler.sendEmptyMessage(0);

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
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btn_personal_login:
                LoginActivity.launch(getActivity());
                break;
            case R.id.btn_personal_regist:
                RegistActivity.launch(getActivity());
                break;
            case R.id.tv_no_personal_setting:
                SettingActivity.launch(getActivity());
                break;
            case R.id.ll_personal_my_buylist:
                MyOrderActivity.launch(getActivity());
                break;
            case R.id.ll_personal_my_addr:
                AddressActivity.launch(getActivity());
                break;
//            case R.id.rl_personal_my_update_pwd:
//                ChangePwdActivity.launch(getActivity());
//                break;
            case R.id.ll_personal_my_about_us:
                AboutUsActivity.launch(getActivity());
                break;
        }
    }
}
