package com.swntek.happyshop.application;


import android.app.Application;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.swntek.happyshop.Util.CacheUtils;
import com.swntek.happyshop.Util.Xlog;

import java.util.Map;


/**
 * Created by wgy on 2015/7/17.
 */
public class MyApplication extends Application {

    public static RequestQueue requestQueue;
    public static MyApplication mcontext;
    public static boolean isApplicationFround;

    public interface VolleyCallBack {
        void netSuccess(String response);
        void netFail(VolleyError error);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        requestQueue = Volley.newRequestQueue(getApplicationContext());
        mcontext = this;
    }


    /**
     * volley的封装
     * @param url  请求地址
     * @param map  参数
     * @param listener 回调接口
     */
    public static void volley(String url, final Map<String, String> map, final VolleyCallBack listener) {
        map.put("token", CacheUtils.getString(mcontext, "token", ""));
        final Map<String, String> finalMap = map;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Xlog.d(response);
                        listener.netSuccess(response);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                listener.netFail(error);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return map;
            }
        };

        stringRequest.setRetryPolicy(new DefaultRetryPolicy(60000, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        requestQueue.add(stringRequest);
    }
}
