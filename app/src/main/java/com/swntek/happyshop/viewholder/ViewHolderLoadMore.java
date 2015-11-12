package com.swntek.happyshop.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;


import com.swntek.happyshop.R;

import fr.castorflex.android.circularprogressbar.CircularProgressBar;

/**
 * Created by wgy on 2015/8/7.
 */
public class ViewHolderLoadMore extends RecyclerView.ViewHolder{
    public CircularProgressBar pull_to_refresh_progress;
    public TextView load_more;

    public ViewHolderLoadMore(View view) {
        super(view);
        pull_to_refresh_progress = (CircularProgressBar) view.findViewById(R.id.pull_to_refresh_progress);
        load_more = (TextView) view.findViewById(R.id.load_more);
        load_more.setText("正在加载");
    }
}
