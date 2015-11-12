package com.swntek.happyshop.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;
import com.swntek.happyshop.base.BaseActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 作者：wgyhello on 15/10/21 14:35
 * 邮箱：429883793@qq.com
 * 我的
 */
public class SettingActivity extends BaseActivity implements View.OnClickListener{


    @Bind(R.id.iv_setting_back)
    RelativeLayout iv_setting_back;

    @Bind(R.id.rl_setting_address)
    RelativeLayout rl_setting_address;

    @Bind(R.id.rl_setting_password)
    RelativeLayout rl_setting_password;

    @Bind(R.id.rl_setting_tickling)
    RelativeLayout rl_setting_tickling;
    /*  @Bind(R.id.rl_setting_aboutme)
      RelativeLayout rl_setting_aboutm;
      @Bind(R.id.tv_setting_nametxt)
      TextView tvSettingNametxt;

      @Bind(R.id.tv_setting_sextxt)
      TextView tvSettingSextxt;*/
    @Bind(R.id.btn_setting_exit)
    Button btn_setting_exit;
    /*RelativeLayout rlSettingAge;
    @Bind(R.id.rl)
    LinearLayout rl;*/

    public static void launch(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
        initView();
    }

    @Override
    protected void onDestroy() {
        ButterKnife.unbind(this);
        super.onDestroy();
    }

    private void initView(){
        iv_setting_back.setOnClickListener(this);
        rl_setting_address.setOnClickListener(this);
        rl_setting_password.setOnClickListener(this);
        rl_setting_tickling.setOnClickListener(this);
        btn_setting_exit.setOnClickListener(this);
        
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.iv_setting_back:
                NickNameActivity.launch(this);
                break;
            case R.id.rl_setting_address:
                AgeActivity.launch(this);
                break;
           /* case R.id.rl_setting_password;
                break;
            case R.id.rl_setting_tickling;
                break;
            case R.id.btn_setting_exit;
                break;*/
        }

    }
}
