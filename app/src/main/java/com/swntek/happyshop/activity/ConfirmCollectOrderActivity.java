package com.swntek.happyshop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;
import com.swntek.happyshop.adapter.ConfirmBuyAdapter;
import com.swntek.happyshop.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/21 14:35
 * 邮箱：429883793@qq.com
 * 购物车确认订单
 */
public class ConfirmCollectOrderActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_confirm_buy_back)
    RelativeLayout ivConfirmBuyBack;
    @Bind(R.id.rl_confirm_buy_title_contain)
    RelativeLayout rlConfirmBuyTitleContain;
    @Bind(R.id.iv_confirm_buy_location)
    ImageView ivConfirmBuyLocation;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.iv_confirm_buy_arrow_right)
    ImageView ivConfirmBuyArrowRight;
    @Bind(R.id.tv_confirm_buy_no_addr_tips)
    TextView tvConfirmBuyNoAddrTips;
    @Bind(R.id.tv_confirm_buy_addr_name)
    TextView tvConfirmBuyAddrName;
    @Bind(R.id.tv_confirm_buy_addr_tel)
    TextView tvConfirmBuyAddrTel;
    @Bind(R.id.rl_confirm_buy_contain)
    RelativeLayout rlConfirmBuyContain;
    @Bind(R.id.tv_confirm_buy_addr)
    TextView tvConfirmBuyAddr;
    @Bind(R.id.rl_confirm_goods_contain)
    RelativeLayout rlConfirmGoodsContain;
    @Bind(R.id.rl_confirm_buy_addr_contain)
    RelativeLayout rlConfirmBuyAddrContain;
    @Bind(R.id.lv_confirm_buy_listview)
    ListView lvConfirmBuyListview;
    @Bind(R.id.tv_confirm_buy_count_content)
    TextView tvConfirmBuyCountContent;
    @Bind(R.id.tv_confirm_buy_total_prict_txt)
    TextView tvConfirmBuyTotalPrictTxt;
    @Bind(R.id.ll_confirm_buy_goods_detail_contain)
    LinearLayout llConfirmBuyGoodsDetailContain;
    @Bind(R.id.tv_confirm_buy_mail_price)
    TextView tvConfirmBuyMailPrice;
    @Bind(R.id.tv_confirm_buy_confirm)
    TextView tvConfirmBuyConfirm;
    @Bind(R.id.tv_confirm_buy_total_price)
    TextView tvConfirmBuyTotalPrice;
    @Bind(R.id.rl_confirm_buy_confirm_contain)
    RelativeLayout rlConfirmBuyConfirmContain;

    public static void launch(Context context) {
        Intent intent = new Intent(context, ConfirmCollectOrderActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_buy);
        ButterKnife.bind(this);
        initView();

    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    private void initView(){
        tvConfirmBuyConfirm.setOnClickListener(this);
        ConfirmBuyAdapter adapter = new ConfirmBuyAdapter(this,null);
        lvConfirmBuyListview.setAdapter(adapter);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_confirm_buy_confirm:
                AccountActivity.launch(this);
                break;
        }
    }
}
