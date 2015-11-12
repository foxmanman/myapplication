package com.swntek.happyshop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.swntek.happyshop.R;
import com.swntek.happyshop.base.BaseActivity;

/**
 * 作者：wgyhello on 15/10/19 22:22
 * 邮箱：429883793@qq.com
 * 注册界面
 */
public class RegistActivity extends BaseActivity {





    public static void launch(Context context){
        Intent intent = new Intent(context,RegistActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);
    }
}
