package com.swntek.happyshop.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/19 10:50
 * 邮箱：429883793@qq.com
 */
public class CollectViewHolder extends RecyclerView.ViewHolder {



    public CollectViewHolder(View itemView) {
        super(itemView);
//        LayoutInflater.from(MyApplication.mcontext).inflate(R.fgm_goods_intro.view_collect_item, null);
        ButterKnife.bind(this, itemView);

    }
}
