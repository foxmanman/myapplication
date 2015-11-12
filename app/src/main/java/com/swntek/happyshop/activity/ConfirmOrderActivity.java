package com.swntek.happyshop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;
import com.swntek.happyshop.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/21 14:35
 * 邮箱：429883793@qq.com
 * 单个商品确认订单
 */
public class ConfirmOrderActivity extends BaseActivity implements View.OnClickListener {

    @Bind(R.id.iv_confirm_order_back)
    RelativeLayout ivConfirmOrderBack;
    @Bind(R.id.rl_confirm_order_title_contain)
    RelativeLayout rlConfirmOrderTitleContain;
    @Bind(R.id.iv_confirm_order_arrow_location)
    ImageView ivConfirmOrderArrowLocation;
    @Bind(R.id.rl)
    RelativeLayout rl;
    @Bind(R.id.iv_confirm_order_arrow_right)
    ImageView ivConfirmOrderArrowRight;
    @Bind(R.id.tv_confirm_buy_no_addr_tips)
    TextView tvConfirmBuyNoAddrTips;
    @Bind(R.id.tv_confirm_order_addr)
    TextView tvConfirmOrderAddr;
    @Bind(R.id.tv_confirm_order_addr_name)
    TextView tvConfirmOrderAddrName;
    @Bind(R.id.tv_confirm_order_addr_tel)
    TextView tvConfirmOrderAddrTel;
    @Bind(R.id.rl_confirm_buy_contain)
    RelativeLayout rlConfirmBuyContain;
    @Bind(R.id.rl_confirm_goods_contain)
    RelativeLayout rlConfirmGoodsContain;
    @Bind(R.id.rl_confirm_order_addr_contain)
    RelativeLayout rlConfirmOrderAddrContain;
    @Bind(R.id.iv_confirm_order_icon)
    ImageView ivConfirmOrderIcon;
    @Bind(R.id.fl_confirm_order_icon_contain)
    RelativeLayout flConfirmOrderIconContain;
    @Bind(R.id.tv_confirm_order_goods_name)
    TextView tvConfirmOrderGoodsName;
    @Bind(R.id.tv_confirm_order_goods_extra_title)
    TextView tvConfirmOrderGoodsExtraTitle;
    @Bind(R.id.tv_confirm_order_goods_count)
    TextView tvConfirmOrderGoodsCount;
    @Bind(R.id.tv_confirm_order_goods_price)
    TextView tvConfirmOrderGoodsPrice;
    @Bind(R.id.tv_product_order_count_txt)
    TextView tvProductOrderCountTxt;
    @Bind(R.id.iv_product_order_minus)
    ImageView ivProductOrderMinus;
    @Bind(R.id.tv_product_order_count)
    TextView tvProductOrderCount;
    @Bind(R.id.iv_product_order_plus)
    ImageView ivProductOrderPlus;
    @Bind(R.id.ll_product_order_count_contain)
    LinearLayout llProductOrderCountContain;
    @Bind(R.id.tv_product_order_count_content)
    TextView tvProductOrderCountContent;
    @Bind(R.id.tv_product_order_total_prict_txt)
    TextView tvProductOrderTotalPrictTxt;
    @Bind(R.id.ll_product_order_goods_detail_contain)
    LinearLayout llProductOrderGoodsDetailContain;
    @Bind(R.id.tv_product_order_mail_price)
    TextView tvProductOrderMailPrice;
    @Bind(R.id.tv_confirm_order_confirm)
    TextView tvConfirmOrderConfirm;
    @Bind(R.id.tv_confirm_order_total_price)
    TextView tvConfirmOrderTotalPrice;
    @Bind(R.id.rl_confirm_order_confirm_contain)
    RelativeLayout rlConfirmOrderConfirmContain;

    public static void launch(Context context) {
        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        context.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmorder);
        ButterKnife.bind(this);
        initView();

    }


    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    private  void initView(){
        rlConfirmOrderAddrContain.setOnClickListener(this);
        tvConfirmOrderConfirm.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.rl_confirm_order_addr_contain:
                AddressActivity.launch(this);
                break;
         case R.id.tv_confirm_order_confirm:
                AccountActivity.launch(this);
                break;
        }
    }
}
