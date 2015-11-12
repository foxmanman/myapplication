package com.swntek.happyshop.Util;

import android.app.Activity;
import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.swntek.happyshop.R;

import de.keyboardsurfer.android.widget.crouton.Configuration;
import de.keyboardsurfer.android.widget.crouton.Crouton;
import de.keyboardsurfer.android.widget.crouton.Style;

/**
 * Created by wgyhello on 15/9/2.
 * toast工具类
 */
public class ToastUtil {

    public static void toast(Activity context,String message,ViewGroup content_layout,int status){

        Configuration configuration = new Configuration.Builder()
                .setDuration(Configuration.DURATION_SHORT)
                .build();

        Style style;
        if(status==0){
            style = new Style.Builder().setHeightDimensionResId(R.dimen.lay_hei_43).setBackgroundColorValue(Style.holoRedLight).build();

        }else if (status==1){
            style = new Style.Builder().setHeightDimensionResId(R.dimen.lay_hei_43).setBackgroundColorValue(Style.holoBlueLight).build();
        }else{
            style = new Style.Builder().setHeightDimensionResId(R.dimen.lay_hei_43).setBackgroundColorValue(Style.holoGreenLight).build();
        }

        Crouton.makeText(context, message, style, content_layout).setConfiguration(configuration).show();

    }

    public static void setSnackbarMessageTextColor(View container,String msg) {

        Snackbar snackbar =
                Snackbar.make(container, msg, Snackbar.LENGTH_LONG);
        View view = snackbar.getView();
        ((TextView) view.findViewById(R.id.snackbar_text)).setTextColor(Color.parseColor("#FFFFFF"));
        snackbar.show();


    }


}
