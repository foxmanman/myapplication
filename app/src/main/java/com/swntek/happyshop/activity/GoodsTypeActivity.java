package com.swntek.happyshop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.swntek.happyshop.R;
import com.swntek.happyshop.adapter.GoodsListAdapter;
import com.swntek.happyshop.base.BaseActivity;
import com.swntek.happyshop.view.CustomListView;

import butterknife.Bind;
import butterknife.ButterKnife;


/**
 * 作者：wgyhello on 15/10/21 14:35
 * 邮箱：429883793@qq.com
 * 商品  特定类型的列表
 */
public class GoodsTypeActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @Bind(R.id.tv_left)
    TextView tvLeft;
    @Bind(R.id.rl_left)
    RelativeLayout rlLeft;
    @Bind(R.id.title)
    TextView title;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.rl_right)
    RelativeLayout rlRight;
    @Bind(R.id.rl_title)
    RelativeLayout rlTitle;
    @Bind(R.id.lv)
    CustomListView lv;
    @Bind(R.id.content_layout)
    RelativeLayout contentLayout;



    public static void launch(Context context){
        Intent intent = new Intent(context,GoodsTypeActivity.class);
        context.startActivity(intent);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fresco.initialize(this);
        setContentView(R.layout.activity_goods_list);
        ButterKnife.bind(this);
        initView();
        getData();
    }

    @Override
    protected void onDestroy() {
//        ButterKnife.unbind(this);
        super.onDestroy();
    }

    private void initView(){
        title.setText("分类列表");
    }

    private void getData(){
        GoodsListAdapter adapter = new GoodsListAdapter(context);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GoodsOneActivity.launch(this);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
//        finish();
    }
}
