package com.swntek.happyshop.base;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.Xlog;
import com.swntek.happyshop.viewholder.ViewHolderLoadMore;


/**
 * Created by wgy on 2015/8/11.
 * baseadapter for recyclerView  can loadmore and so on ...
 * recycleview 基类
 */
public abstract class BaseRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    int typeloadmore = 99, type0 = 0;

    int loadMoreStatus;
    int notifyPosition;
    LoadMoreListener listener;

    public interface LoadMoreListener {
        void loadMore(int position);
    }

    /**
     * 获取数据源的数量
     */
    public abstract int getDataCnt();


    public void setLoadMoreStatus(int status) {
        loadMoreStatus = status;
        switch (status) {
            case 0:
                return;
            case 1:
                notifyItemChanged(getDataCnt());
                return;
            case 2:
                notifyItemChanged(getDataCnt());
                return;
        }

    }

    /**创建数据源的viewholder*/
    /** fgm_goods_intro parent false*/
    public abstract RecyclerView.ViewHolder createNormalViewHolder(ViewGroup parent, int viewType);

    /**bind normal viewholder*/
    public abstract void bindNormalViewHolder(RecyclerView.ViewHolder holder, final int position);

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == 0) {
            return createNormalViewHolder(parent, viewType);
        } else {
            View view99 = LayoutInflater.from(parent.getContext()).inflate(R.layout.listfooter_more, parent, false);
            return new ViewHolderLoadMore(view99);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        if (type == typeloadmore) {
            setLoadMoreByStatus(holder, position);
        } else {
            bindNormalViewHolder(holder, position);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return switchItemViewType(position);
    }

    private int switchItemViewType(int position) {
        if (position == getDataCnt()) {
            return typeloadmore;
        } else {
            return type0;
        }
    }

    @Override
    public int getItemCount() {
        return getDataCnt() + 1;
    }

    /** 0 可以加载 ，1 正在加载  ，2 没有更多数据 */
    private void setLoadMoreByStatus(RecyclerView.ViewHolder holder, int position) {
        switch (loadMoreStatus) {
            case 0:
                Xlog.d("setLoadMoreByStatus========0");
                ((ViewHolderLoadMore) holder).pull_to_refresh_progress.setVisibility(View.VISIBLE);
                ((ViewHolderLoadMore) holder).load_more.setText("正在加载...");
                loadMoreStatus = 1;
                listener.loadMore(position);
                break;
            case 1:
                Xlog.d("setLoadMoreByStatus========1");
                ((ViewHolderLoadMore) holder).pull_to_refresh_progress.setVisibility(View.VISIBLE);
                ((ViewHolderLoadMore) holder).load_more.setText("正在加载...");
                break;
            case 2:
                Xlog.d("setLoadMoreByStatus========2");
                ((ViewHolderLoadMore) holder).pull_to_refresh_progress.setVisibility(View.GONE);
                ((ViewHolderLoadMore) holder).load_more.setText("没有更多数据");
                break;
        }

    }
}
