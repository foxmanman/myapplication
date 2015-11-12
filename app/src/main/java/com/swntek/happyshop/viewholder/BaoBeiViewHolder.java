package com.swntek.happyshop.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/19 10:50
 * 邮箱：429883793@qq.com
 */
public class BaoBeiViewHolder extends RecyclerView.ViewHolder {



    public BaoBeiViewHolder(View itemView) {
        super(itemView);
//        LayoutInflater.from(MyApplication.mcontext).inflate(R.fgm_goods_intro.view_collect_item, null);
        ButterKnife.bind(this, itemView);

    }
}
