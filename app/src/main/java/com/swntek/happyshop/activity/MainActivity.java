package com.swntek.happyshop.activity;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.VolleyError;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.swntek.happyshop.R;
import com.swntek.happyshop.Util.Xcontent;
import com.swntek.happyshop.application.MyApplication;
import com.swntek.happyshop.base.BaseFragmentActivity;
import com.swntek.happyshop.fragment.FirstFgm;
import com.swntek.happyshop.fragment.FourthFgm;
import com.swntek.happyshop.fragment.SecondFgm;
import com.swntek.happyshop.fragment.ThirdFgm;

import junit.runner.Version;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 作者：wgyhello on 15/10/21 14:35
 * 邮箱：429883793@qq.com
 * 主界面
 */
public class MainActivity extends BaseFragmentActivity implements OnClickListener {

    private RelativeLayout firstLayout, secondLayout, thirdLayout, fourthLayout,rl_scanning;

    private LinearLayout content_layout;

    private Fragment firstFgm, secondFgm, thirdFgm, fourthFgm;

    private ImageView firstImg, secondImg, thirdImg, fourthImg;
    public TextView firstTv, secondTv, thirdTv, fourthTv, msg_mark;

    public static Fragment currentFragment;

    public static boolean isforeground;

    public static boolean isUpdate;

    public boolean isShowMark;

    public static void launch(Context context) {
        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(
                WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN
                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        Fresco.initialize(this);

        setContentView(R.layout.activity_main);

        initUI();
        initTab();
    }

    @Override
    protected void onStart() {
        super.onStart();
//        checkVersion();
    }

    @Override
    protected void onResume() {
        super.onResume();
//        checkMark();
        isforeground = true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        isforeground = false;
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initUI() {
        firstLayout = (RelativeLayout) findViewById(R.id.rl_first);
        secondLayout = (RelativeLayout) findViewById(R.id.rl_second);
        thirdLayout = (RelativeLayout) findViewById(R.id.rl_third);
        fourthLayout = (RelativeLayout) findViewById(R.id.rl_fourth);
        content_layout = (LinearLayout) findViewById(R.id.content_layout);
        rl_scanning = (RelativeLayout)findViewById(R.id.rl_scanning);

        firstLayout.setOnClickListener(this);
        secondLayout.setOnClickListener(this);
        thirdLayout.setOnClickListener(this);
        fourthLayout.setOnClickListener(this);
        rl_scanning.setOnClickListener(this);

        firstImg = (ImageView) findViewById(R.id.iv_first);
        secondImg = (ImageView) findViewById(R.id.iv_second);
        thirdImg = (ImageView) findViewById(R.id.iv_third);
        fourthImg = (ImageView) findViewById(R.id.iv_fourth);

        firstTv = (TextView) findViewById(R.id.tv_first);
        secondTv = (TextView) findViewById(R.id.tv_second);
        thirdTv = (TextView) findViewById(R.id.tv_third);
        fourthTv = (TextView) findViewById(R.id.tv_fourth);
        msg_mark = (TextView) findViewById(R.id.msg_mark);

    }

    private void initTab() {
        if (firstFgm == null) {
            firstFgm = new FirstFgm();
        }
        if (!firstFgm.isAdded()) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.content_layout, firstFgm, "tab1").commit();
            currentFragment = firstFgm;
//            firstImg.setImageResource(R.mipmap.appointmenthl);
            firstTv.setSelected(true);
        }
    }

    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_first:
                clickTab1Layout();
                break;
            case R.id.rl_second:
                clickTab2Layout();
                break;
            case R.id.rl_third:
                clickTab3Layout();
                break;
            case R.id.rl_fourth:
                clickTab4Layout();
                break;
            case R.id.rl_scanning://扫描码二维
                /*clickTab5Layout();*/
            default:
                break;
        }
    }

    private void clickTab1Layout() {
        if (firstFgm == null) {
            firstFgm = new FirstFgm();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),
                firstFgm, "tab1");
        setothertext();
        firstTv.setSelected(true);
        firstImg.setSelected(true);
//        firstImg.setImageResource(R.mipmap.appointmenthl);
    }

    private void clickTab2Layout() {
        if (secondFgm == null) {
            secondFgm = new SecondFgm();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),
                secondFgm, "tab2");
        setothertext();
        secondTv.setSelected(true);
//        secondImg.setImageResource(R.mipmap.patientlisthl);
    }

    private void clickTab3Layout() {
        if (thirdFgm == null) {
            thirdFgm = new ThirdFgm();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),
                thirdFgm, "tab3");
        setothertext();
        thirdTv.setSelected(true);
//        thirdImg.setImageResource(R.mipmap.settinghl);
    }

    private void clickTab4Layout() {
        if (fourthFgm == null) {
            fourthFgm = new FourthFgm();
        }
        addOrShowFragment(getSupportFragmentManager().beginTransaction(),
                fourthFgm, "tab4");
        setothertext();
        fourthTv.setSelected(true);
//        thirdImg.setImageResource(R.mipmap.settinghl);
    }
    //扫描二维码
   /* private void clickTab5Layout(){

    }*/
    private void setothertext() {
        firstTv.setSelected(false);
        secondTv.setSelected(false);
        thirdTv.setSelected(false);
        fourthTv.setSelected(false);

//        firstImg.setImageResource(R.mipmap.appointment);
//        secondImg.setImageResource(R.mipmap.patientlist);
//        thirdImg.setImageResource(R.mipmap.setting);
    }

    private void addOrShowFragment(FragmentTransaction transaction,
                                   Fragment fragment, String tag) {
        if (currentFragment == fragment) {
            return;
        }
        if (!fragment.isAdded()) {
            transaction.hide(currentFragment)
                    .add(R.id.content_layout, fragment, tag).commit();
        } else {
            transaction.hide(currentFragment).show(fragment).commit();
        }
        currentFragment = fragment;
    }

    /**
     * home实现 和 输入法的关闭
     */
    @Override
    public void onBackPressed() {
//        if(currentFragment==secondFgm&&SecondFgm.searchStatus){
//            ((SecondFgm)secondFgm).cancleSearch();
//        }else{
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.addCategory(Intent.CATEGORY_HOME);
        startActivity(i);
//        }
    }
    /** mark */
//    private void checkMark(){
//        Map<String,String> map = new HashMap<>();
//        MyApplication.volley(Xcontent.newCommentCntOfMyTasks, map, new MyApplication.VolleyCallBack() {
//            @Override
//            public void netSuccess(String response) {
//                NewMarkEntity entity = NewMarkEntity.jsontobeanone(response,gson);
//                String mark = entity.getNewcommentcnt();
//                if("0".equals(mark)){
//                    clearMark();
//                }else{
//                    showMark();
//                }
//            }
//
//            @Override
//            public void netFail(VolleyError error) {
//
//            }
//        });
//
//    }

//    public void showMark(){
//        isShowMark = true;
//        if(null!=((ThirdFgm)thirdFgm)){
//            ((ThirdFgm)thirdFgm).mark.setVisibility(View.VISIBLE);
//        }
//        msg_mark.setVisibility(View.VISIBLE);
//
//    }
//    public void clearMark(){
//        isShowMark = false;
//        if(null!=((ThirdFgm)thirdFgm)){
//            ((ThirdFgm)thirdFgm).mark.setVisibility(View.GONE);
//        }
//        msg_mark.setVisibility(View.GONE);
//
//    }


    /**
     * 检查服务器最新版本
     */
//    private void checkVersion() {
//
//        Map<String,String> map = new HashMap<>();
//
//        MyApplication.volley(Xcontent.versionurl, map, new MyApplication.VolleyCallBack() {
//            @Override
//            public void netSuccess(String response) {
//                Version version = Version.jsontobeanone(response, gson);
//                PackageManager packageManager = getPackageManager();
//                // getPackageName()是你当前类的包名，0代表是获取版本信息
//                PackageInfo packInfo = null;
//                try {
//                    packInfo = packageManager.getPackageInfo(MainActivity.this.getPackageName(), 0);
//                } catch (PackageManager.NameNotFoundException e) {
//                    e.printStackTrace();
//                }
//                String cuversion = packInfo.versionName;
//                String versionstr = version.getVersion();
//                if (cuversion.equals(versionstr)) {
//
//                } else {
//                    showUpdateDialog();
//                }
//            }
//
//            @Override
//            public void netFail(VolleyError error) {
//
//            }
//        });
//
//    }


    /**版本更新对话框
     * TODO  1.更新的地址 2. 应用的图标
     * */
//    String description = "有新版本 是否更新？";
//    private static final int NOTIFICATION_ID = 0x12;
//    private Notification notification = null;
//    private NotificationManager manager = null;
//    private NotificationCompat.Builder mBuilder;
//    private void showUpdateDialog() {
//        isUpdate = true;
//        AlertDialog.Builder builder = new AlertDialog.Builder(this);
//        builder.setTitle("更新提醒");
//        builder.setMessage(description);
//        builder.setPositiveButton("立即更新",
//                new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        isUpdate = false;
//
//                        mBuilder = new NotificationCompat.Builder(
//                                MainActivity.this);
//                        mBuilder.setContentTitle("版本更新");//设置通知栏标题
//                        mBuilder.setSmallIcon(R.mipmap.logo_32);// 设置通知小ICON
//                        mBuilder.setProgress(100, 0, false);
//                        notification = mBuilder.build();
//                        notification.contentIntent = PendingIntent.getActivity(MainActivity.this, 0,
//                                new Intent(MainActivity.this, MainActivity.class), 0);
//                        // 获得一个NotificationManger 对象，此对象可以对notification做统一管理，只需要知道ID
//                        manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
//                        manager.notify(NOTIFICATION_ID, notification);
//                        ToastUtil.setSnackbarMessageTextColor(content_layout, "更开始下载...");
//                        if (Environment.getExternalStorageState().equals(
//                                Environment.MEDIA_MOUNTED)) {
//                            // 下载最新的APK apkurl
//                            FinalHttp http = new FinalHttp();
//                            //判断是否下载过文件  还要和服务端约定
////                            File file = new File(Environment
////                                    .getExternalStorageDirectory()
////                                    .getAbsolutePath()
////                                    + "/update.apk");
////
////                            if(file.exists())
//                            http.download(Xcontent.apkurl, Environment
//                                    .getExternalStorageDirectory()
//                                    .getAbsolutePath()
//                                    + "/update.apk", new AjaxCallBack<File>() {
//
//                                @Override
//                                public void onFailure(Throwable t, int errorNo,
//                                                      String strMsg) {
//                                    super.onFailure(t, errorNo, strMsg);
//                                    t.printStackTrace();
//                                    ToastUtil.setSnackbarMessageTextColor(content_layout,"更新失败，请检查网络连接");
//                                    manager.cancel(NOTIFICATION_ID);
//                                }
//
//                                @Override
//                                public void onLoading(long count, long current) {
//                                    super.onLoading(count, current);
//                                    int progress = (int) (current * 100 / count);
//                                    mBuilder.setProgress(100, progress, false);
//                                    manager.notify(NOTIFICATION_ID, mBuilder.build());
//
//                                }
//
//                                @Override
//                                public void onSuccess(File t) {
//                                    super.onSuccess(t);
//                                    installAPK(t);
//                                    manager.cancel(NOTIFICATION_ID);
//                                }
//
//                                /**
//                                 * 安装APK
//                                 *
//                                 * @param t
//                                 */
//                                private void installAPK(File t) {
//                                    Intent intent = new Intent();
//                                    intent.setAction("android.intent.action.VIEW");
//                                    intent.addCategory("android.intent.category.DEFAULT");
//                                    intent.setDataAndType(Uri.fromFile(t),
//                                            "application/vnd.android.package-archive");
//                                    startActivity(intent);
//                                }
//
//                            });
//
//                        } else {
//                            ToastUtil.toast(MainActivity.this, "没有sd卡", content_layout, 1);
//
//                            return;
//                        }
//
//                    }
//                });
//        builder.setNegativeButton("下次再说",
//                new DialogInterface.OnClickListener() {
//
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        isUpdate = false;
//                    }
//                });
//
//        builder.show();
//
//    }


}
