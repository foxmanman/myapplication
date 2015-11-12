package com.swntek.happyshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.WgyneedUtil;
import com.swntek.happyshop.Util.Xlog;
import com.swntek.happyshop.entity.GoodsEntity;
import com.swntek.happyshop.viewholder.GoodsListViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：wgyhello on 15/10/19 09:47
 * 邮箱：429883793@qq.com
 * 首页商品列表
 */
public class GoodsListAdapter extends BaseAdapter {

    Context context;
    /*List<GoodsEntity> data=new ArrayList<GoodsEntity>();*/
    public int[] shopproduct=
            {R.mipmap.fairground02,
                    R.mipmap.fairground03,
                    R.mipmap.fairground02
            };
    LayoutInflater inflater;

    public GoodsListAdapter(Context context) {
        this.context = context;
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
        Xlog.d("getview "+position);
        View view = convertView;
        GoodsListViewHolder holder;
//        final GoodsEntity item = data.get(position);
        if (view == null) {
            Xlog.e("null");
            Fresco.initialize(context);
            view = inflater.inflate(R.layout.view_product_items, null);
            holder = new GoodsListViewHolder(view);
            view.setTag(holder);

        } else {
            Xlog.e("have");
            holder = (GoodsListViewHolder) view.getTag();
        }
       /* setData(null, holder);*/
        holder.product_sdv.setBackgroundResource(shopproduct[position]);
        return view;
    }

    private void setData(final GoodsEntity item, GoodsListViewHolder holder){
        WgyneedUtil.loadSdvFromRes(holder.product_sdv,R.drawable.fairground02);

    }
}
