package com.swntek.happyshop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.swntek.happyshop.R;
import com.swntek.happyshop.adapter.AddressAdapter;
import com.swntek.happyshop.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * 作者：wgyhello on 15/10/21 14:35
 * 邮箱：429883793@qq.com
 * 地址管理界面
 */
public class AddressActivity extends BaseActivity implements View.OnClickListener{

    @Bind(R.id.iv_address_back)
    RelativeLayout ivAddressBack;
    @Bind(R.id.rl_address_title_contain)
    RelativeLayout rlAddressTitleContain;
    @Bind(R.id.ll_address_add_contain)
    LinearLayout llAddressAddContain;
    @Bind(R.id.lv_address_listview)
    ListView lvAddressListview;

    public static void launch(Context context) {
        Intent intent = new Intent(context, AddressActivity.class);
        context.startActivity(intent);


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    private void initView(){

        AddressAdapter addressAdapter = new AddressAdapter(context,null);
        lvAddressListview.setAdapter(addressAdapter);
        llAddressAddContain.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ll_address_add_contain:
                NewAddressActivity.launch(this);
                break;
        }
    }
}
