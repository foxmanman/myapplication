package com.swntek.happyshop.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.swntek.happyshop.R;
import com.swntek.happyshop.application.MyApplication;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/19 10:50
 * 邮箱：429883793@qq.com
 */
public class GoodsListViewHolder extends RecyclerView.ViewHolder {


    @Bind(R.id.product_sdv)
    public SimpleDraweeView product_sdv;
    /*@Bind(R.id.tv_item_product_price)
    public TextView tvItemProductPrice;*/
    /*@Bind(R.id.iv_item_product_favorite)
    public ImageView ivItemProductFavorite;*/
    @Bind(R.id.tv_item_product_content_hots)
    public TextView tvItemProductContentHots;
    @Bind(R.id.tv_item_product_content_name)
    public TextView tvItemProductContentName;
   /* @Bind(R.id.tv_item_product_content_effect)
    public TextView tvItemProductContentEffect;*/
    @Bind(R.id.ll_item_product_content)
    public RelativeLayout llItemProductContent;
    @Bind(R.id.ll_item_product_single)
    public LinearLayout llItemProductSingle;
    @Bind(R.id.iv_item_product_subject_bg)
    public ImageView ivItemProductSubjectBg;
    @Bind(R.id.tv_item_product_subject_title)
    public TextView tvItemProductSubjectTitle;
    @Bind(R.id.tv_item_product_subject_name)
    public TextView tvItemProductSubjectName;
    @Bind(R.id.rl_item_product_subject)
    public RelativeLayout rlItemProductSubject;

    public GoodsListViewHolder(View itemView) {
        super(itemView);
        LayoutInflater.from(MyApplication.mcontext).inflate(R.layout.view_product_items, null);
        ButterKnife.bind(this, itemView);

    }
}
