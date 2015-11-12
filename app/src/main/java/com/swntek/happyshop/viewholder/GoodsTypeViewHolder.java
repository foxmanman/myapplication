package com.swntek.happyshop.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.swntek.happyshop.R;
import com.swntek.happyshop.application.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/19 10:50
 * 邮箱：429883793@qq.com
 */
public class GoodsTypeViewHolder extends RecyclerView.ViewHolder {


    @Bind(R.id.stype_dv)
    public SimpleDraweeView stype_dv;

    public GoodsTypeViewHolder(View itemView) {
        super(itemView);
//        LayoutInflater.from(MyApplication.mcontext).inflate(R.fgm_goods_intro.item_drug_type, null);
        ButterKnife.bind(this, itemView);

    }
}
