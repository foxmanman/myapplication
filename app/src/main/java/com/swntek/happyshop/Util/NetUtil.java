package com.swntek.happyshop.Util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by wgyhello on 15/9/2.
 * 网络判断
 */
public class NetUtil {


    /** 0 无网络
     *  1 wifi
     *  2 手机网络
     *  */
    public static int getNetStatus(Context context){

        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo mobileInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        NetworkInfo wifiInfo = manager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        NetworkInfo activeInfo = manager.getActiveNetworkInfo();
        if(!mobileInfo.isConnected()&&!wifiInfo.isConnected()){
            Xcontent.netstatus = 0;
        }else{
            if(wifiInfo.isConnected()){
                Xcontent.netstatus = 1;
            }else{
                Xcontent.netstatus = 2;
            }

        }
        return  Xcontent.netstatus ;

    }
}
