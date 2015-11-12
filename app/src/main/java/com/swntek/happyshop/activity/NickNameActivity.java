package com.swntek.happyshop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.swntek.happyshop.R;
import com.swntek.happyshop.base.BaseActivity;

/**
 * 作者：wgyhello on 15/10/21 14:35
 * 邮箱：429883793@qq.com
 * 修改昵称界面
 */
public class NickNameActivity extends BaseActivity {



    public static void launch(Context context){
        Intent intent = new Intent(context,NickNameActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nickname);
    }
}
