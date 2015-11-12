package com.swntek.happyshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.Xlog;
import com.swntek.happyshop.entity.CollectEntity;
import com.swntek.happyshop.entity.GoodsEntity;
import com.swntek.happyshop.viewholder.CollectViewHolder;

import java.util.List;

/**
 * 作者：wgyhello on 15/10/19 09:47
 * 邮箱：429883793@qq.com
 * 购物车列表
 */
public class CollectAdapter extends BaseAdapter {

    Context context;
    List<CollectEntity> data;
    LayoutInflater inflater;

    public CollectAdapter(Context context, List<CollectEntity> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
//        Fresco.initialize(context);
    }

    @Override
    public int getCount() {
        return 1;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Xlog.d("getview "+position);
        View view = convertView;
        CollectViewHolder holder;
//        final GoodsEntity item = data.get(position);
        if (view == null) {
            Xlog.e("null");
            view = inflater.inflate(R.layout.view_collect_item, null);
            holder = new CollectViewHolder(view);
            view.setTag(holder);

        } else {
            Xlog.e("have");
            holder = (CollectViewHolder) view.getTag();
        }
        setData(null, holder);
        return view;
    }

    private void setData(final GoodsEntity item, CollectViewHolder holder){
//        WgyneedUtil.loadSdvFromRes(holder.sdv,R.drawable.bigbang);

    }
}
