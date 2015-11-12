package com.swntek.happyshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.Xlog;
import com.swntek.happyshop.entity.AddressEntity;
import com.swntek.happyshop.entity.BaoBeiEntity;
import com.swntek.happyshop.entity.GoodsEntity;
import com.swntek.happyshop.viewholder.AddressViewHolder;
import com.swntek.happyshop.viewholder.BaoBeiViewHolder;

import java.util.List;

/**
 * 作者：wgyhello on 15/10/21 13:17
 * 邮箱：429883793@qq.com
 * 购物车确认购买
 */
public class ConfirmBuyAdapter extends BaseAdapter {

    Context context;
    List<BaoBeiEntity> data;
    LayoutInflater inflater;

    public ConfirmBuyAdapter(Context context, List<BaoBeiEntity> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
//        Fresco.initialize(context);
    }

    @Override
    public int getCount() {
        return 30;
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
        Xlog.d("getview " + position);
        View view = convertView;
        BaoBeiViewHolder holder;
//        final GoodsEntity item = data.get(position);
        if (view == null) {
            Xlog.e("null");
            view = inflater.inflate(R.layout.view_confirm_buy_item, null);
            holder = new BaoBeiViewHolder(view);
            view.setTag(holder);

        } else {
            Xlog.e("have");
            holder = (BaoBeiViewHolder) view.getTag();
        }
        setData(null, holder);
        return view;
    }

    private void setData(final GoodsEntity item, BaoBeiViewHolder holder){
//        WgyneedUtil.loadSdvFromRes(holder.sdv,R.drawable.bigbang);

    }
}
