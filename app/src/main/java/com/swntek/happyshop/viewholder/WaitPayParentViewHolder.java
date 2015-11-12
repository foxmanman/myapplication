package com.swntek.happyshop.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;
import com.swntek.happyshop.application.MyApplication;
import com.swntek.happyshop.view.MyListView;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/19 10:50
 * 邮箱：429883793@qq.com
 */
public class WaitPayParentViewHolder extends RecyclerView.ViewHolder {


    @Bind(R.id.tv_wait_parent_item_oid)
    public TextView tvWaitParentItemOid;
    @Bind(R.id.lv_buylist_item_listview)
    public MyListView lvBuylistItemListview;
    @Bind(R.id.tv_wait_pay_goods_pay)
    public TextView tvWaitPayGoodsPay;
    @Bind(R.id.tv_wait_pay_goods_cancel)
    public TextView tvWaitPayGoodsCancel;
    @Bind(R.id.rl)
    public  RelativeLayout rl;
    @Bind(R.id.rl_wait_parent_item_bottom_contain)
    public RelativeLayout rlWaitParentItemBottomContain;

    public WaitPayParentViewHolder(View itemView) {
        super(itemView);
//        LayoutInflater.from(MyApplication.mcontext).inflate(R.layout.view_wait_parent_item, null);
        ButterKnife.bind(this, itemView);

    }
}
