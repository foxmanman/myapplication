package com.swntek.happyshop.base;

import android.annotation.TargetApi;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.readystatesoftware.systembartint.SystemBarTintManager;
import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.ActivityCollector;

/**
 * Created by wgyhello on 15/9/1.
 */
public class BaseFragmentActivity extends FragmentActivity {

    public RelativeLayout rl_title,rl_left,rl_right;
    public TextView tv_title;
    public ViewGroup contentLayout;
    public Gson gson;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (null != savedInstanceState) {
            savedInstanceState.putParcelable("android:support:fragments", null);
        }
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        initWindow();

    }

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

//        rl_title = (RelativeLayout) findViewById(R.id.rl_title);
//        rl_left = (RelativeLayout) rl_title.getChildAt(0);
//        tv_title = (TextView) rl_title.getChildAt(1);
//        rl_right = (RelativeLayout) rl_title.getChildAt(2);
//
//        contentLayout = (ViewGroup) findViewById(R.id.content_layout);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }




}
