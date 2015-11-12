package com.swntek.happyshop.Util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import com.swntek.happyshop.R;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * 作者：wgyhello on 15/10/18 21:06
 * 邮箱：429883793@qq.com
 * 乱七八糟的工具类
 *
 */
public class WgyneedUtil {



    //nodata tost
    public static TextView addNoDataText(Context context, RelativeLayout root,String str) {
        TextView tv = new TextView(context);
        tv.setText(str);
        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
                LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        root.addView(tv, params);
        return tv;
    }
    /** swipe ref*/
    public static void setSwipeColor(SwipeRefreshLayout swipeContainer){
        swipeContainer.setColorSchemeResources(
                android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light);
    }



    //设置右侧icon
    public static void setRightIcon(Context context, ImageView iv_right, int res, int wid_dp, int hei_wid) {
        LayoutParams p = (LayoutParams) iv_right.getLayoutParams();
        p.width = dip2px(context, wid_dp);
        p.width = dip2px(context, hei_wid);
        iv_right.setLayoutParams(p);
        iv_right.setBackgroundResource(res);
    }

    //应用是否在前台
    public static boolean isApplicationFround(final Context context) {
        ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> tasks = am.getRunningTasks(1);
        if (!tasks.isEmpty()) {
            ComponentName topActivity = tasks.get(0).topActivity;
            if (topActivity.getPackageName().equals(context.getPackageName())) {
                return true;
            }
        }
        return false;

    }


    public static int getType(String type) {

        if ("Txt".equals(type)) {
            return 0;
        } else if ("Picture".equals(type)) {
            return 1;
        } else if ("TxtPicture".equals(type)) {
            return 2;
        } else if ("Tip".equals(type)) {

            return 3;
        }
        return -1;

    }

    // 加载更多的提示
//    public static <T> boolean setLoadMoreText(List<T> data, CustomListView lv,
//                                              int cnt) {
//        if (data.size() == cnt) {
//            lv.setEndloadmore("点击加载更多");
//            return false;
//        } else {
//            lv.setEndloadmore("没有更多数据");
//            return true;
//        }
//
//    }

//    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
//    public static AlertDialog showDeleteAlert(Context context) {
//        AlertDialog.Builder builder = new AlertDialog.Builder(context, AlertDialog.THEME_HOLO_LIGHT);
//        NoMoveLinear ll = (NoMoveLinear) LayoutInflater.from(context).inflate(R.fgm_goods_intro.long_delete, null);
//        TextView delete = (TextView) ll.findViewById(R.id.delete);
//        delete.setOnClickListener((OnClickListener) context);
//        builder.setView(ll);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        return dialog;
//
//    }


//    public static String getH5UserOneUrl(Context context, String userid) {
//
//        return "http://m.xingyouquan.cn/user/" + userid + "?" + "token="
//                + CacheUtils.getString(context, "token", "") + "&dd=0" + "&ww="
//                + CacheUtils.getString(context, "ww", "") + "&hh="
//                + CacheUtils.getString(context, "hh", "") + "&bb="
//                + CacheUtils.getString(context, "bb", "") + "&pp="
//                + CacheUtils.getString(context, "pp", "");
//    }


    public static void saveImageToGallery(Context context, Bitmap bmp) {
        // 首先保存图片
        File appDir = new File(Environment.getExternalStorageDirectory(),
                "zhongtukeimg");
        if (!appDir.exists()) {
            appDir.mkdir();
        }
        String fileName = System.currentTimeMillis() + ".png";
        File file = new File(appDir, fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(CompressFormat.JPEG, 100, fos);
            fos.flush();
            fos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 其次把文件插入到系统图库
        try {
            MediaStore.Images.Media.insertImage(context.getContentResolver(),
                    file.getAbsolutePath(), fileName, null);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        // 最后通知图库更新
        context.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                Uri.parse("file://" + file.getAbsolutePath())));
    }

    public static int getscreemWidth(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度

        return screenW;
    }

    public static int getscreemHei(Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay()
                .getMetrics(dm);
        int screenH = dm.heightPixels;// 获取分辨率宽度

        return screenH;
    }

//    public static LinearLayout addProgress(Context context, RelativeLayout root) {
//        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context)
//                .inflate(R.fgm_goods_intro.layout_progress, null);
//
//        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT,
//                LayoutParams.WRAP_CONTENT);
//        params.addRule(RelativeLayout.CENTER_IN_PARENT);
//        root.addView(linearLayout, params);
//        return linearLayout;
//    }


    @SuppressLint("NewApi")
    public static Dialog createDialog(Context context, String msg) {

        RelativeLayout rl = (RelativeLayout) LayoutInflater.from(context)
                .inflate(R.layout.relative_progress, null);
        TextView textView = (TextView) rl.findViewById(R.id.text_toast);
        textView.setText(msg);
        AlertDialog.Builder builder = new AlertDialog.Builder(context,
                AlertDialog.THEME_HOLO_LIGHT);
        builder.setView(rl);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        return dialog;

    }

    public static void closeInputMethod(Activity context, View v) {

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen) {
//            imm.hideSoftInputFromWindow(v.getWindowToken(),
//                    InputMethodManager.HIDE_NOT_ALWAYS);
            imm.hideSoftInputFromWindow( v.getApplicationWindowToken( ) , 0 );

        }

    }

    public static void openInputMethod(Activity context, View v) {

        InputMethodManager imm = (InputMethodManager) context
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        boolean isOpen = imm.isActive();
        if (isOpen) {
            imm.showSoftInput(v,InputMethodManager.SHOW_FORCED);

        }

    }



    public static void addLine(LinearLayout thelayout, Context context) {

        LinearLayout tv = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.line_left10, null);
        thelayout.addView(tv);
    }

    public static void addEmpty10(LinearLayout theLayout, Context context) {

        LinearLayout tv = (LinearLayout) LayoutInflater.from(context).inflate(
                R.layout.empty_10_e, null);
        theLayout.addView(tv);
    }

//    public static TextView addSubmit(LinearLayout thelayout, Context context,
//                                     String text) {
//
//        LinearLayout lay = (LinearLayout) LayoutInflater.from(context).inflate(
//                R.fgm_goods_intro.tv_submit, null);
//        TextView tv = (TextView) lay.getChildAt(0);
//        tv.setText(text);
//        tv.setOnClickListener((OnClickListener) context);
//        thelayout.addView(lay);
//        return tv;
//    }

//    public static TextView addSubmitWithReturn(LinearLayout thelayout, Context context,
//                                               String text) {
//
//        LinearLayout lay = (LinearLayout) LayoutInflater.from(context).inflate(
//                R.fgm_goods_intro.tv_submit, null);
//        TextView tv = (TextView) lay.getChildAt(0);
//        tv.setText(text);
//        thelayout.addView(lay);
//        return tv;
//    }


    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

//    public static PopupWindow showFacePop(final Activity context, View thelayout) {
//        final PopupWindow popupWindow;
//        DisplayMetrics dm = new DisplayMetrics();
//        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screenW = dm.widthPixels;// 获取分辨率宽度
//        int screenH = dm.heightPixels;// 获取分辨率宽度
//
//        popupWindow = new PopupWindow(thelayout, screenW, WgyneedUtil.dip2px(
//                context, 131));
//
//        LinearLayout contentView = (LinearLayout) LayoutInflater.from(context)
//                .inflate(R.fgm_goods_intro.pop_face, null);
//
//        TextView t1 = (TextView) contentView.getChildAt(0);
//        t1.setTag("choose");
//        t1.setOnClickListener((OnClickListener) context);
//        TextView t2 = (TextView) contentView.getChildAt(2);
//        t2.setTag("take");
//        t2.setOnClickListener((OnClickListener) context);
//        TextView t3 = (TextView) contentView.getChildAt(4);
//        t3.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                popupWindow.dismiss();
//            }
//        });
//        popupWindow.setContentView(contentView);
//        popupWindow.setFocusable(true);
//
//        // popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        //
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow.setBackgroundDrawable(cd);
//
//        //
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setAnimationStyle(R.style.PopupAnimation);
//        popupWindow.showAtLocation(thelayout, Gravity.BOTTOM, 0, 0);
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = 0.4f;
//        context.getWindow().setAttributes(lp);
//        popupWindow.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = context.getWindow()
//                        .getAttributes();
//                lp.alpha = 1f;
//                context.getWindow().setAttributes(lp);
//            }
//        });
//
//        // LinearLayout topic_modify = (LinearLayout) contentView
//        // .findViewById(R.id.topic_modify);
//        // LinearLayout topic_broadcast = (LinearLayout) contentView
//        // .findViewById(R.id.topic_broadcast);
//        // topic_modify.setOnClickListener(this);
//        // topic_broadcast.setOnClickListener(this);
//        return popupWindow;
//    }

//    public static PopupWindow showSexPop(final Activity context, View thelayout) {
//        final PopupWindow popupWindow;
//        DisplayMetrics dm = new DisplayMetrics();
//        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screenW = dm.widthPixels;// 获取分辨率宽度
//        int screenH = dm.heightPixels;// 获取分辨率宽度
//
//        popupWindow = new PopupWindow(thelayout, screenW, WgyneedUtil.dip2px(
//                context, 131));
//
//        LinearLayout contentView = (LinearLayout) LayoutInflater.from(context)
//                .inflate(R.fgm_goods_intro.pop_sex, null);
//
//        TextView t1 = (TextView) contentView.getChildAt(0);
//        t1.setTag("boy");
//        t1.setOnClickListener((OnClickListener) context);
//        TextView t2 = (TextView) contentView.getChildAt(2);
//        t2.setTag("girl");
//        t2.setOnClickListener((OnClickListener) context);
//        TextView t3 = (TextView) contentView.getChildAt(4);
//        t3.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View arg0) {
//                popupWindow.dismiss();
//            }
//        });
//        popupWindow.setContentView(contentView);
//        popupWindow.setFocusable(true);
//
//        // popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        //
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow.setBackgroundDrawable(cd);
//
//        //
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setAnimationStyle(R.style.PopupAnimation);
//        popupWindow.showAtLocation(thelayout, Gravity.BOTTOM, 0, 0);
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = 0.4f;
//        context.getWindow().setAttributes(lp);
//        popupWindow.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = context.getWindow()
//                        .getAttributes();
//                lp.alpha = 1f;
//                context.getWindow().setAttributes(lp);
//            }
//        });
//
//        // LinearLayout topic_modify = (LinearLayout) contentView
//        // .findViewById(R.id.topic_modify);
//        // LinearLayout topic_broadcast = (LinearLayout) contentView
//        // .findViewById(R.id.topic_broadcast);
//        // topic_modify.setOnClickListener(this);
//        // topic_broadcast.setOnClickListener(this);
//        return popupWindow;
//    }

    // movietype
//    public static PopupWindow showMovietypePop(final Activity context,
//                                               View thelayout, TextView tv, Map<String, String> map) {
//        final PopupWindow popupWindow;
//        DisplayMetrics dm = new DisplayMetrics();
//        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screenW = dm.widthPixels;// 获取分辨率宽度
//        int screenH = dm.heightPixels;// 获取分辨率宽度
//
//        popupWindow = new PopupWindow(thelayout, screenW, screenW / 2);
//
//        LinearLayout contentView = (LinearLayout) LayoutInflater.from(context)
//                .inflate(R.fgm_goods_intro.pop_caree, null);
//
//        ListView listView = (ListView) contentView.getChildAt(0);
//
//        MovietypeAdapter adapter = new MovietypeAdapter(context, XConst.movietypestr, tv, popupWindow, map);
//        listView.setAdapter(adapter);
//
//        popupWindow.setContentView(contentView);
//        popupWindow.setFocusable(true);
//
//        // popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        //
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow.setBackgroundDrawable(cd);
//
//        //
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setAnimationStyle(R.style.PopupAnimation);
//        popupWindow.showAtLocation(thelayout, Gravity.BOTTOM, 0, 0);
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = 0.4f;
//        context.getWindow().setAttributes(lp);
//        popupWindow.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = context.getWindow()
//                        .getAttributes();
//                lp.alpha = 1f;
//                context.getWindow().setAttributes(lp);
//            }
//        });
//
//        // LinearLayout topic_modify = (LinearLayout) contentView
//        // .findViewById(R.id.topic_modify);
//        // LinearLayout topic_broadcast = (LinearLayout) contentView
//        // .findViewById(R.id.topic_broadcast);
//        // topic_modify.setOnClickListener(this);
//        // topic_broadcast.setOnClickListener(this);
//        return popupWindow;
//    }


    // Career
//    public static PopupWindow showCareerPop(final Activity context,
//                                            View thelayout, List<Career> data, TextView tv) {
//        final PopupWindow popupWindow;
//        DisplayMetrics dm = new DisplayMetrics();
//        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screenW = dm.widthPixels;// 获取分辨率宽度
//        int screenH = dm.heightPixels;// 获取分辨率宽度
//
//        popupWindow = new PopupWindow(thelayout, screenW, screenW / 2);
//
//        LinearLayout contentView = (LinearLayout) LayoutInflater.from(context)
//                .inflate(R.fgm_goods_intro.pop_caree, null);
//
//        ListView listView = (ListView) contentView.getChildAt(0);
//
//        CaeerAdapter adapter = new CaeerAdapter(context, data, tv, popupWindow);
//        listView.setAdapter(adapter);
//
//        popupWindow.setContentView(contentView);
//        popupWindow.setFocusable(true);
//
//        // popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        //
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow.setBackgroundDrawable(cd);
//
//        //
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setAnimationStyle(R.style.PopupAnimation);
//        popupWindow.showAtLocation(thelayout, Gravity.BOTTOM, 0, 0);
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = 0.4f;
//        context.getWindow().setAttributes(lp);
//        popupWindow.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = context.getWindow()
//                        .getAttributes();
//                lp.alpha = 1f;
//                context.getWindow().setAttributes(lp);
//            }
//        });
//
//        // LinearLayout topic_modify = (LinearLayout) contentView
//        // .findViewById(R.id.topic_modify);
//        // LinearLayout topic_broadcast = (LinearLayout) contentView
//        // .findViewById(R.id.topic_broadcast);
//        // topic_modify.setOnClickListener(this);
//        // topic_broadcast.setOnClickListener(this);
//        return popupWindow;
//    }

    // Career
//    public static PopupWindow addCareerPop(final Activity context,
//                                           View thelayout, List<Career> data, TextView tv, TextView tv_title, Map<String, String> map) {
//        final PopupWindow popupWindow;
//        DisplayMetrics dm = new DisplayMetrics();
//        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screenW = dm.widthPixels;// 获取分辨率宽度
//        int screenH = dm.heightPixels;// 获取分辨率宽度
//
//        popupWindow = new PopupWindow(thelayout, screenW, screenW / 2);
//
//        LinearLayout contentView = (LinearLayout) LayoutInflater.from(context)
//                .inflate(R.fgm_goods_intro.pop_caree, null);
//
//        ListView listView = (ListView) contentView.getChildAt(0);
//
//        AddCareeAdapter adapter = new AddCareeAdapter(context, data, tv, tv_title, popupWindow, map);
//        listView.setAdapter(adapter);
//
//        popupWindow.setContentView(contentView);
//        popupWindow.setFocusable(true);
//
//        // popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        //
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow.setBackgroundDrawable(cd);
//
//        //
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setAnimationStyle(R.style.PopupAnimation);
//        popupWindow.showAtLocation(thelayout, Gravity.BOTTOM, 0, 0);
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = 0.4f;
//        context.getWindow().setAttributes(lp);
//        popupWindow.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = context.getWindow()
//                        .getAttributes();
//                lp.alpha = 1f;
//                context.getWindow().setAttributes(lp);
//            }
//        });
//
//        // LinearLayout topic_modify = (LinearLayout) contentView
//        // .findViewById(R.id.topic_modify);
//        // LinearLayout topic_broadcast = (LinearLayout) contentView
//        // .findViewById(R.id.topic_broadcast);
//        // topic_modify.setOnClickListener(this);
//        // topic_broadcast.setOnClickListener(this);
//        return popupWindow;
//    }


    // NationsPop
//    public static PopupWindow showNationsPop(final Activity context,
//                                             View thelayout, List<Nations> data, TextView tv) {
//        final PopupWindow popupWindow;
//        DisplayMetrics dm = new DisplayMetrics();
//        context.getWindowManager().getDefaultDisplay().getMetrics(dm);
//        int screenW = dm.widthPixels;// 获取分辨率宽度
//        int screenH = dm.heightPixels;// 获取分辨率宽度
//
//        popupWindow = new PopupWindow(thelayout, screenW, screenW / 2);
//
//        LinearLayout contentView = (LinearLayout) LayoutInflater.from(context)
//                .inflate(R.fgm_goods_intro.pop_caree, null);
//
//        ListView listView = (ListView) contentView.getChildAt(0);
//
//        NationsAdapter adapter = new NationsAdapter(context, data, tv,
//                popupWindow);
//        listView.setAdapter(adapter);
//
//        popupWindow.setContentView(contentView);
//        popupWindow.setFocusable(true);
//
//        // popupWindow.setBackgroundDrawable(new BitmapDrawable());
//        //
//        ColorDrawable cd = new ColorDrawable(0x000000);
//        popupWindow.setBackgroundDrawable(cd);
//
//        //
//        popupWindow.setOutsideTouchable(true);
//        popupWindow.setAnimationStyle(R.style.PopupAnimation);
//        popupWindow.showAtLocation(thelayout, Gravity.BOTTOM, 0, 0);
//        WindowManager.LayoutParams lp = context.getWindow().getAttributes();
//        lp.alpha = 0.4f;
//        context.getWindow().setAttributes(lp);
//        popupWindow.setOnDismissListener(new OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                WindowManager.LayoutParams lp = context.getWindow()
//                        .getAttributes();
//                lp.alpha = 1f;
//                context.getWindow().setAttributes(lp);
//            }
//        });
//
//        // LinearLayout topic_modify = (LinearLayout) contentView
//        // .findViewById(R.id.topic_modify);
//        // LinearLayout topic_broadcast = (LinearLayout) contentView
//        // .findViewById(R.id.topic_broadcast);
//        // topic_modify.setOnClickListener(this);
//        // topic_broadcast.setOnClickListener(this);
//        return popupWindow;
//    }

    // 图片存储位置
    public static File setOutPutImage() {
        File outputimage = new File(Environment.getExternalStorageDirectory(),
                "faceimg.jpg");
        if (outputimage.exists()) {
            outputimage.delete();
        }
        try {
            outputimage.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return outputimage;
    }

    // 图片压缩
    public static Bitmap decodeSampledBitmapFromResource(String path,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;

        // BitmapFactory.decodeStream(is,null, options);
        BitmapFactory.decodeFile(path, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth,
                reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeFile(path, options);
    }

    // 压缩
    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height
                    / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
        }
        return inSampleSize;
    }

    //Bitmap压缩
    public static Bitmap zoomImage(Bitmap bgimage, double newWidth,
                                   double newHeight) {
        // 获取这个图片的宽和高
        float width = bgimage.getWidth();
        float height = bgimage.getHeight();
        // 创建操作图片用的matrix对象
        Matrix matrix = new Matrix();
        // 计算宽高缩放率
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 缩放图片动作
        matrix.postScale(scaleWidth, scaleHeight);
        Bitmap bitmap = Bitmap.createBitmap(bgimage, 0, 0, (int) width,
                (int) height, matrix, true);
        return bitmap;
    }

    // 异步或同步加载图片
//加载头像
//    public static Bitmap loadFaceRounded(Context mContext, ImageDownLoader mImageDownLoader, final RoundedImageView _imageView, final String url) {
//        if (mImageDownLoader == null) {
//            mImageDownLoader = new ImageDownLoader(mContext);
//        }
//        Bitmap bitmap = mImageDownLoader.downloadImage(url,
//                new ImageDownLoader.onImageLoaderListener() {
//
//                    @Override
//                    public void onImageLoader(Bitmap bitmap, String url) {
//                        if (_imageView != null && bitmap != null) {
//                            _imageView.setImageBitmap(bitmap);
//
//                            ObjectAnimator.ofFloat(_imageView, "alpha", 0, 1)
//                                    .setDuration(500).start();
//                            ObjectAnimator.ofFloat(_imageView, "scaleX", 0, 1)
//                                    .setDuration(500).start();
//                            ObjectAnimator.ofFloat(_imageView, "scaleY", 0, 1)
//                                    .setDuration(500).start();
//                        }
//                    }
//                });
//
//        if (bitmap != null) {
//            _imageView.setImageBitmap(bitmap);
//
//            ObjectAnimator.ofFloat(_imageView, "alpha", 0, 1).setDuration(500)
//                    .start();
//            ObjectAnimator.ofFloat(_imageView, "scaleX", 0, 1).setDuration(500)
//                    .start();
//            ObjectAnimator.ofFloat(_imageView, "scaleY", 0, 1).setDuration(500)
//                    .start();
//        }
//        return bitmap;
//    }

    /**    加载图片 */
    public static void loadSdvFromUrl(final SimpleDraweeView sdv, final String url) {
        Uri uri = Uri.parse(url);
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(sdv.getController())
                .build();

        sdv.setController(controller);

    }

    /**    加载图片 */
    public static void loadSdvFromRes(final SimpleDraweeView sdv, int resId) {
        Uri uri = Uri.parse("res://包名(实际可以是任何字符串甚至留空)/" + resId);
        ImageRequest request = ImageRequestBuilder
                .newBuilderWithSource(uri)
                .setProgressiveRenderingEnabled(true)
                .build();
        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
                .setImageRequest(request)
                .setOldController(sdv.getController())
                .build();

        sdv.setController(controller);

    }


//    public static Bitmap loadAndroidImg(Context context, ImageDownLoader mImageDownLoader, final ImageView _imageView, final String url) {
//        if (mImageDownLoader == null) {
//            mImageDownLoader = new ImageDownLoader(context);
//        }
//        Bitmap bitmap = mImageDownLoader.downloadImage(url,
//                new ImageDownLoader.onImageLoaderListener() {
//
//                    @Override
//                    public void onImageLoader(Bitmap bitmap, String url) {
//                        if (_imageView != null && bitmap != null) {
//                            _imageView.setImageBitmap(bitmap);
//                        }
//                    }
//                });
//
//        if (bitmap != null) {
//            _imageView.setImageBitmap(bitmap);
//        }
//        return bitmap;
//    }


    //加载图片
//    public static void loadImage(Context mContext, ImageDownLoader mImageDownLoader, final SimpleDraweeView iv_true, final ImageView iv_false, final String url, final int wid, final int hei) {
//        Uri uri = Uri.parse(url);
//        ImageRequest request = ImageRequestBuilder
//                .newBuilderWithSource(uri)
//                .setProgressiveRenderingEnabled(true)
//                .build();
//        PipelineDraweeController controller = (PipelineDraweeController) Fresco.newDraweeControllerBuilder()
//                .setImageRequest(request)
//                .setOldController(iv_true.getController())
//                .build();
//
//        iv_true.setController(controller);
//
//    }


    public static Bitmap doBlur(Bitmap sentBitmap, int radius,
                                boolean canReuseInBitmap) {

        // Stack Blur v1.0 from
        // http://www.quasimondo.com/StackBlurForCanvas/StackBlurDemo.html
        //
        // Java Author: Mario Klingemann <mario at quasimondo.com>
        // http://incubator.quasimondo.com
        // created Feburary 29, 2004
        // Android port : Yahel Bouaziz <yahel at kayenko.com>
        // http://www.kayenko.com
        // ported april 5th, 2012

        // This is a compromise between Gaussian Blur and Box blur
        // It creates much better looking blurs than Box Blur, but is
        // 7x faster than my Gaussian Blur implementation.
        //
        // I called it Stack Blur because this describes best how this
        // filter works internally: it creates a kind of moving stack
        // of colors whilst scanning through the image. Thereby it
        // just has to add one new block of color to the right side
        // of the stack and remove the leftmost color. The remaining
        // colors on the topmost layer of the stack are either added on
        // or reduced by one, depending on if they are on the right or
        // on the left side of the stack.
        //
        // If you are using this algorithm in your code please add
        // the following line:
        //
        // Stack Blur Algorithm by Mario Klingemann <mario@quasimondo.com>

        Bitmap bitmap;
        if (canReuseInBitmap) {
            bitmap = sentBitmap;
        } else {
            bitmap = sentBitmap.copy(sentBitmap.getConfig(), true);
        }

        if (radius < 1) {
            return (null);
        }

        int w = bitmap.getWidth();
        int h = bitmap.getHeight();

        int[] pix = new int[w * h];
        bitmap.getPixels(pix, 0, w, 0, 0, w, h);

        int wm = w - 1;
        int hm = h - 1;
        int wh = w * h;
        int div = radius + radius + 1;

        int r[] = new int[wh];
        int g[] = new int[wh];
        int b[] = new int[wh];
        int rsum, gsum, bsum, x, y, i, p, yp, yi, yw;
        int vmin[] = new int[Math.max(w, h)];

        int divsum = (div + 1) >> 1;
        divsum *= divsum;
        int dv[] = new int[256 * divsum];
        for (i = 0; i < 256 * divsum; i++) {
            dv[i] = (i / divsum);
        }

        yw = yi = 0;

        int[][] stack = new int[div][3];
        int stackpointer;
        int stackstart;
        int[] sir;
        int rbs;
        int r1 = radius + 1;
        int routsum, goutsum, boutsum;
        int rinsum, ginsum, binsum;

        for (y = 0; y < h; y++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            for (i = -radius; i <= radius; i++) {
                p = pix[yi + Math.min(wm, Math.max(i, 0))];
                sir = stack[i + radius];
                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);
                rbs = r1 - Math.abs(i);
                rsum += sir[0] * rbs;
                gsum += sir[1] * rbs;
                bsum += sir[2] * rbs;
                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }
            }
            stackpointer = radius;

            for (x = 0; x < w; x++) {

                r[yi] = dv[rsum];
                g[yi] = dv[gsum];
                b[yi] = dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (y == 0) {
                    vmin[x] = Math.min(x + radius + 1, wm);
                }
                p = pix[yw + vmin[x]];

                sir[0] = (p & 0xff0000) >> 16;
                sir[1] = (p & 0x00ff00) >> 8;
                sir[2] = (p & 0x0000ff);

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[(stackpointer) % div];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi++;
            }
            yw += w;
        }
        for (x = 0; x < w; x++) {
            rinsum = ginsum = binsum = routsum = goutsum = boutsum = rsum = gsum = bsum = 0;
            yp = -radius * w;
            for (i = -radius; i <= radius; i++) {
                yi = Math.max(0, yp) + x;

                sir = stack[i + radius];

                sir[0] = r[yi];
                sir[1] = g[yi];
                sir[2] = b[yi];

                rbs = r1 - Math.abs(i);

                rsum += r[yi] * rbs;
                gsum += g[yi] * rbs;
                bsum += b[yi] * rbs;

                if (i > 0) {
                    rinsum += sir[0];
                    ginsum += sir[1];
                    binsum += sir[2];
                } else {
                    routsum += sir[0];
                    goutsum += sir[1];
                    boutsum += sir[2];
                }

                if (i < hm) {
                    yp += w;
                }
            }
            yi = x;
            stackpointer = radius;
            for (y = 0; y < h; y++) {
                // Preserve alpha channel: ( 0xff000000 & pix[yi] )
                pix[yi] = (0xff000000 & pix[yi]) | (dv[rsum] << 16)
                        | (dv[gsum] << 8) | dv[bsum];

                rsum -= routsum;
                gsum -= goutsum;
                bsum -= boutsum;

                stackstart = stackpointer - radius + div;
                sir = stack[stackstart % div];

                routsum -= sir[0];
                goutsum -= sir[1];
                boutsum -= sir[2];

                if (x == 0) {
                    vmin[y] = Math.min(y + r1, hm) * w;
                }
                p = x + vmin[y];

                sir[0] = r[p];
                sir[1] = g[p];
                sir[2] = b[p];

                rinsum += sir[0];
                ginsum += sir[1];
                binsum += sir[2];

                rsum += rinsum;
                gsum += ginsum;
                bsum += binsum;

                stackpointer = (stackpointer + 1) % div;
                sir = stack[stackpointer];

                routsum += sir[0];
                goutsum += sir[1];
                boutsum += sir[2];

                rinsum -= sir[0];
                ginsum -= sir[1];
                binsum -= sir[2];

                yi += w;
            }
        }

        bitmap.setPixels(pix, 0, w, 0, 0, w, h);

        return (bitmap);
    }

//    public static void isLogin(Activity context) {
//        String token = CacheUtils.getString(context, "token", "");
//        if (token.equals("")) {
//            RegOrLoginActivity.launch(context);
//
//        } else {
//            MainActivity.launch(context);
//            context.finish();
//        }
//
//    }

//    public static void logOut(final Activity context) {
//        HttpUtils http = new HttpUtils();
//        RequestParams params = new RequestParams("UTF-8");
//        params.addBodyParameter("token", MyUser.getToken(context));
//        http.send(HttpMethod.POST, XConst.my_logout, params,
//                new RequestCallBack<String>() {
//
//                    @Override
//                    public void onSuccess(ResponseInfo<String> responseInfo) {
//                        Xlog.d(responseInfo.result);
//                        CacheUtils.putString(context, "token", "");
//                        CacheUtils.putString(context, "myuser", "");
//                        CacheUtils.putString(context, "careerid", "");
//                        CacheUtils.putString(context, "careername", "");
//
//                        DbUtil.getInstance(context).cleanClans();
//                        // 退出就全关了
//                        ActivityCollector.finishAll();
//                    }
//
//                    @Override
//                    public void onFailure(HttpException error, String msg) {
//                        Xlog.d("onFailure:" + msg);
//                        toast(context, "网络问题导致登录失败,请重试", 0);
//                    }
//                });
//
//    }

    public static String getActionFromUrl(String url) {

        String str1 = url.substring(9);

        return str1;

    }

//    public static void getwwHH(Context context) {
//        int ww = WgyneedUtil.getscreemWidth(context) / 2;
//
//        int bb = WgyneedUtil.dip2px(context, 16) / 2;
//
//        int hh = WgyneedUtil.getscreemHei(context) / 2;
//        int pp = context.getResources().getDimensionPixelOffset(
//                R.dimen.blogpicmargin) / 2;
//
//        CacheUtils.putString(context, "ww", ww + "");
//        CacheUtils.putString(context, "bb", bb + "");
//        CacheUtils.putString(context, "hh", hh + "");
//        CacheUtils.putString(context, "pp", pp + "");
//
//    }

//    public interface CustomHttpListener {
//
//        void onCustomHttpSuccess(JSONObject jsonRet);
//
//        void onCustomHttpFaile(HttpException error, String msg);
//    }

//    public static void customHttp(final Context context, HttpUtils http,
//                                  Map<String, String> p, String url, final CustomHttpListener listener) {
//        if (http == null) {
//            http = new HttpUtils();
//        }
//        RequestParams params = new RequestParams("UTF-8");
//        for (String key : p.keySet()) {
//            params.addBodyParameter(key, p.get(key));
//        }
//        params.addBodyParameter("devicetype", "2");
//        params.addBodyParameter("token",
//                CacheUtils.getString(context, "token", ""));
//        if ("".equals(CacheUtils.getString(context, "ww", ""))) {
//            getwwHH(context);
//        }
//
//        params.addBodyParameter("ww", CacheUtils.getString(context, "ww", ""));
//        params.addBodyParameter("bb", CacheUtils.getString(context, "bb", ""));
//        params.addBodyParameter("hh", CacheUtils.getString(context, "hh", ""));
//        params.addBodyParameter("pp", CacheUtils.getString(context, "pp", ""));
//
//        http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
//
//            @Override
//            public void onSuccess(ResponseInfo<String> responseInfo) {
//                try {
//                    JSONObject jsonRet = new JSONObject(responseInfo.result);
//                    Xlog.d(jsonRet.toString());
//                    listener.onCustomHttpSuccess(jsonRet);
//                } catch (JSONException e) {
//                    e.printStackTrace();
//
//                    WgyneedUtil.jsonErrorToast(context);
//                }
//
//            }
//
//            @Override
//            public void onFailure(HttpException error, String msg) {
//                WgyneedUtil.requstFailToast(context);
//                listener.onCustomHttpFaile(error, msg);
//            }
//        });
//
//    }


    // 连
    private static long lastClickTime;

    public static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        long timeD = time - lastClickTime;
        if (0 < timeD && timeD < 500) {
            return true;
        }
        lastClickTime = time;
        return false;
    }


}
