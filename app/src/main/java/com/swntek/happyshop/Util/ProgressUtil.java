package com.swntek.happyshop.Util;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.swntek.happyshop.R;


/**
 * Created by wgyhello on 15/8/31.
 * 进度条相关
 */
public class ProgressUtil {


    public interface Progresstv{
        void getProgressTv(TextView tv);
    }

    //progress dialog
    public static Dialog createProgressDialog(Context context, String msg,Progresstv listener) {
        RelativeLayout rl = (RelativeLayout) LayoutInflater.from(context)
                .inflate(R.layout.relative_progress, null);
        TextView textView = (TextView) rl.findViewById(R.id.text_toast);
        textView.setText(msg);
        listener.getProgressTv(textView);
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(rl);
        AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);
        return dialog;
    }

    public static LinearLayout addProgress(Context context, RelativeLayout root) {
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(context)
                .inflate(R.layout.layout_progress, null);

        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT);
        params.addRule(RelativeLayout.CENTER_IN_PARENT);
        root.addView(linearLayout, params);
        return linearLayout;
    }



}
