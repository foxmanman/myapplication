package com.swntek.happyshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.Xlog;
import com.swntek.happyshop.entity.GoodsEntity;
import com.swntek.happyshop.entity.WaitPayItemEntity;
import com.swntek.happyshop.entity.WaitPayPatientEntity;
import com.swntek.happyshop.viewholder.AddressViewHolder;
import com.swntek.happyshop.viewholder.WaitPayItemViewHolder;

import java.util.List;

/**
 * 作者：wgyhello on 15/10/21 13:17
 * 邮箱：429883793@qq.com
 * 我的订单－》等待付款的一条商品
 */
public class WaitPayItemAdapter extends BaseAdapter {

    Context context;
    List<WaitPayItemEntity> data;
    LayoutInflater inflater;

    public WaitPayItemAdapter(Context context, List<WaitPayItemEntity> data) {
        this.context = context;
        this.data = data;
        inflater = LayoutInflater.from(context);
//        Fresco.initialize(context);
    }

    @Override
    public int getCount() {
        return 3;
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
        WaitPayItemViewHolder holder;
//        final GoodsEntity item = data.get(position);
        if (view == null) {
            Xlog.e("null");
            view = inflater.inflate(R.layout.view_item_wait_pay, null);
            holder = new WaitPayItemViewHolder(view);
            view.setTag(holder);

        } else {
            Xlog.e("have");
            holder = (WaitPayItemViewHolder) view.getTag();
        }
        setData(null, holder);
        return view;
    }

    private void setData(final GoodsEntity item, WaitPayItemViewHolder holder){
//        WgyneedUtil.loadSdvFromRes(holder.sdv,R.drawable.bigbang);

    }
}
