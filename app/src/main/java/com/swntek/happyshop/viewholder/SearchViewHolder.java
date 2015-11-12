package com.swntek.happyshop.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by wgyhello on 15/9/2.
 */
public class SearchViewHolder extends RecyclerView.ViewHolder {


    @Bind(R.id.tv_search)
    public TextView tvSearch;
//    @Bind(R.id.et_search)
//    public EditText etSearch;
//    @Bind(R.id.tv_cancle)
//    public TextView tvCancle;
//    @Bind(R.id.ll_search)
//    public LinearLayout llSearch;

    public SearchViewHolder(View itemView) {
        super(itemView);
//        LayoutInflater.from(MyApplication.mcontext).inflate(R.fgm_goods_intro.custom_search, null);

        ButterKnife.bind(this, itemView);

    }
}
