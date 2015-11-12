package com.swntek.happyshop.base;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.WindowManager;

import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.ActivityCollector;

/**
 * Created by wgyhello on 15/8/26.
 * activity基类
 */
public class BaseActivity extends AppCompatActivity {


    public  ViewGroup contentLayout;
    public Gson gson;
    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        context = this;
        initWindow();

    }

    /**
     * 4.4 沉浸式状态栏
     */
    @TargetApi(19)
    private void initWindow() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintColor(getStatusBarColor());
            tintManager.setStatusBarTintEnabled(true);
        }
    }

    public int getStatusBarColor() {
        return getColorPrimary();
    }

    public int getColorPrimary() {
        TypedValue typedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimary, typedValue, true);
        return typedValue.data;
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
//        contentLayout = (ViewGroup) findViewById(R.id.content_layout);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
