package com.swntek.happyshop.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.WgyneedUtil;
import com.swntek.happyshop.Util.Xlog;
import com.swntek.happyshop.entity.GoodsEntity;
import com.swntek.happyshop.entity.GoodsTypeEntity;
import com.swntek.happyshop.viewholder.GoodsTypeViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * 作者：wgyhello on 15/10/19 17:24
 * 邮箱：429883793@qq.com
 * 分类的商品列表
 */
public class GoodsTypeAdapter extends BaseAdapter{

    Context context;
    /*List<GoodsTypeEntity> data;*/
   /* GoodsTypeEntity goods=new GoodsTypeEntity();*/
    public int[] shopcalssify=
            {R.mipmap.classify03,
                    R.mipmap.classify05,
                    R.mipmap.classify07,
                    R.mipmap.classify10};
    LayoutInflater inflater;

    public GoodsTypeAdapter(Context context) {
        this.context = context;


        inflater = LayoutInflater.from(context);
//        Fresco.initialize(context);
    }

    @Override
    public int getCount() {
       // Log.i("TAG",data.toString());
        return shopcalssify.length;
    }

    @Override
    public Object getItem(int position) {
        return shopcalssify[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Xlog.d("getview " + position);
        View view = convertView;
        GoodsTypeViewHolder holder;
//        final GoodsEntity item = data.get(position);
        if (view == null) {
            Xlog.e("null");
            view = inflater.inflate(R.layout.item_drug_type, null);
            holder = new GoodsTypeViewHolder(view);
            view.setTag(holder);

        } else {
            Xlog.e("have");
            holder = (GoodsTypeViewHolder) view.getTag();
        }
       //setData(null, holder);
        holder.stype_dv.setBackgroundResource(shopcalssify[position]);
       /* holder.stype_dv.setBackgroundResource(shopcalssify.[position]);*/
        return view;
    }

    private void setData(final GoodsEntity item, GoodsTypeViewHolder holder){
        WgyneedUtil.loadSdvFromRes(holder.stype_dv, R.mipmap.icon_image_default);

    }
}
