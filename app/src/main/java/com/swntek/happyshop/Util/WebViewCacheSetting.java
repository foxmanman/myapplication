package com.swntek.happyshop.Util;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebResourceResponse;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import com.swntek.happyshop.application.MyApplication;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by wgyhello on 15/8/31.
 * webview离线缓存工具类
 */
public class WebViewCacheSetting {

    private static final String APP_CACHE_DIRNAME = "/webcache"; // web缓存目录
    private static final String WEBCACHEDIR = "/data/data/com.fcqx.fcdoctor/app_webview/Cache";

    public interface H5FinishCallBack {
        void onloadBegin();

        void onFinish();

        void onFail();
    }

    static int time1;
    static int time2;


    /**
     * cookie
     */
    public static void synCookies(Context context, String url) {
        CookieSyncManager.createInstance(context);
        CookieManager cookieManager = CookieManager.getInstance();
        cookieManager.setAcceptCookie(true);
        cookieManager.removeSessionCookie();//移除
        cookieManager.setCookie(url, "token=" + CacheUtils.getString(MyApplication.mcontext, "token", ""));//cookies是在HttpClient中获得的cookie
        CookieSyncManager.getInstance().sync();
    }

    public static void initWebView(final Context context, final WebView mWebView, final H5FinishCallBack listener) {

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        //渲染优先级
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT,无网络时，使用LOAD_CACHE_ELSE_NETWORK
        NetUtil.getNetStatus(context);
        Xlog.d("status == = == net == =  =" + Xcontent.netstatus);
        //TODO
        if (Xcontent.netstatus == 0) {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存模式
        } else {
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); // 设置缓存模式
        }
        // 开启DOM storage API 功能
        mWebView.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        mWebView.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = context.getFilesDir().getAbsolutePath()
                + APP_CACHE_DIRNAME;
        Log.i("cachePath", cacheDirPath);
        // 设置数据库缓存路径
        mWebView.getSettings().setDatabasePath(cacheDirPath); // API 19 deprecated
        // 设置Application caches缓存目录
        mWebView.getSettings().setAppCachePath(cacheDirPath);
        // 开启Application Cache功能
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);

        mWebView.setWebChromeClient(new WebChromeClient() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), AlertDialog.THEME_HOLO_LIGHT);
                builder.setTitle("提示信息")
                        .setMessage(message)
                        .setPositiveButton("确定", null);
                // 不需要绑定按键事件
                // 屏蔽keycode等于84之类的按键
                builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        return true;
                    }
                });
                // 禁止响应按back键的事件
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
                result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
                return true;
            }
        });

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                //TODO
                time1 = (int) System.currentTimeMillis();

                Xlog.d("onPageStarted  url =  " + url);
                super.onPageStarted(view, url, favicon);
                if (url.contains("tasksold") || url.contains("tasks") || url.contains("taskadd") || url.contains("pipes") || url.contains("answersheets") || url.contains("chart") || url.contains("error")) {
                    Xcontent.CANBREAK = true;
                } else {
                    Xcontent.CANBREAK = false;
                }
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                Xlog.d(url);
                /**cookie*/
                synCookies(context, url);
                listener.onloadBegin();
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {

                //TODO
                time2 = (int) System.currentTimeMillis();

                Xlog.d("-----time-----=" + (time2 - time1));
//				super.onPageFinished(view, url);
                if ("找不到网页".equals(view.getTitle())) {
                    view.stopLoading();
                    view.loadUrl("file:///android_asset/error.html");
                    Xcontent.CANBREAK = true;
//                    listener.onFail();
                } else {
                    Xlog.d("load ====   finish  ====" + view.getTitle());
                    listener.onFinish();
                }
                Xlog.d("finish  url =  " + url);
                CookieManager cookieManager = CookieManager.getInstance();
                String CookieStr = cookieManager.getCookie(url);
                Log.e("sunzn", "Cookies = " + CookieStr);

            }

            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
//                super.onReceivedHttpError(view, request, errorResponse);
                Xlog.d("onReceivedHttpError   fail");
                view.stopLoading();
                view.clearView();
                listener.onFail();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
                Xlog.d("loadresource  url =  " + url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                Xlog.d("onReceivedSslError   fail");
                view.stopLoading();
                view.clearView();
                listener.onFail();
            }

            /**加载失败的处理 */
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
                Xlog.d("onReceivedError   fail");
                view.stopLoading();
                view.clearView();
                listener.onFail();
            }

            @Override
            public void onReceivedLoginRequest(WebView view, String realm, String account, String args) {
                super.onReceivedLoginRequest(view, realm, account, args);
            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, WebResourceRequest request) {

                Xlog.d("shouldInterceptRequest>21--url=" + view.getUrl());
                Xlog.d("shouldInterceptRequest>21--request=" + request);
                return super.shouldInterceptRequest(view, request);


            }

            @Override
            public WebResourceResponse shouldInterceptRequest(WebView view, String url) {
                Xlog.d("shouldInterceptRequest>11--url=" + url);
                if (url.contains("http://echarts.baidu.com/build/dist/echarts.js")) {

                    Xlog.d("echarts.js");

                    WebResourceResponse res = null;
                    try {
                        InputStream instream = context.getResources().getAssets().open("echarts.js");
                        res = new WebResourceResponse("http://echarts.baidu.com/build/dist/echarts.js",
                                "UTF-8", instream);
                    } catch (IOException e) {
                        e.printStackTrace();
                        Xlog.d("error echarts.js");

                    }
                    Xlog.d("替换 echarts.js");


                    return res;
                } else if (url.contains("http://echarts.baidu.com/build/dist/chart/bar.js")) {

                    Xlog.d("chart/bar");
                    WebResourceResponse res = null;
                    try {
                        InputStream instream = context.getResources().getAssets().open("chart.js");
                        res = new WebResourceResponse("http://echarts.baidu.com/build/dist/chart/bar.js",
                                "UTF-8", instream);
                    } catch (IOException e) {
                        e.printStackTrace();

                    }

                    Xlog.d("替换 chart/bar");
                    return res;
                } else if (url.contains("http://echarts.baidu.com/build/dist/chart/line.js")) {

                    Xlog.d("chart/bar");
                    WebResourceResponse res = null;
                    try {
                        InputStream instream = context.getResources().getAssets().open("line.js");
                        res = new WebResourceResponse("http://echarts.baidu.com/build/dist/chart/line.js",
                                "UTF-8", instream);
                    } catch (IOException e) {
                        e.printStackTrace();

                    }

                    Xlog.d("替换 chart/bar");
                    return res;
                }


//                else{
                return super.shouldInterceptRequest(view, url);
//                }


            }
        });
    }


    public interface LoadH5Progress {
        void setLoadh5Progress();
    }

    //webview for load H5
    public static void setLoadH5WebView(Context context, final WebView mWebView, final LoadH5Progress listener) {

        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        mWebView.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        // 建议缓存策略为，判断是否有网络，有的话，使用LOAD_DEFAULT,无网络时，使用LOAD_CACHE_ELSE_NETWORK
//        mWebView.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); // 设置缓存模式
        mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT); // 设置缓存模式
        // 开启DOM storage API 功能
        mWebView.getSettings().setDomStorageEnabled(true);
        // 开启database storage API功能
        mWebView.getSettings().setDatabaseEnabled(true);
        String cacheDirPath = context.getFilesDir().getAbsolutePath()
                + APP_CACHE_DIRNAME;
        Log.i("cachePath", cacheDirPath);
        // 设置数据库缓存路径
        mWebView.getSettings().setDatabasePath(cacheDirPath); // API 19 deprecated
        // 设置Application caches缓存目录
        mWebView.getSettings().setAppCachePath(cacheDirPath);
        // 开启Application Cache功能
        mWebView.getSettings().setAppCacheEnabled(true);
        mWebView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        mWebView.setWebChromeClient(new WebChromeClient() {

            @TargetApi(Build.VERSION_CODES.HONEYCOMB)
            @Override
            public boolean onJsAlert(WebView view, String url, String message, JsResult result) {

                final AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), AlertDialog.THEME_HOLO_LIGHT);
                builder.setTitle("提示信息")
                        .setMessage(message)
                        .setPositiveButton("确定", null);
                // 不需要绑定按键事件
                // 屏蔽keycode等于84之类的按键
                builder.setOnKeyListener(new DialogInterface.OnKeyListener() {
                    public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                        return true;
                    }
                });
                // 禁止响应按back键的事件
                builder.setCancelable(false);
                AlertDialog dialog = builder.create();
                dialog.show();
                result.confirm();// 因为没有绑定事件，需要强行confirm,否则页面会变黑显示不了内容。
                return true;

            }
        });


        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                Xlog.d("=======onPageFinished=====");
                Xlog.d(url);
                listener.setLoadh5Progress();
//				super.onPageFinished(view, url);
//                progress.setVisibility(View.GONE);
//                mWebView.setVisibility(View.VISIBLE);
            }


            @Override
            public void onReceivedHttpError(WebView view, WebResourceRequest request, WebResourceResponse errorResponse) {
//                super.onReceivedHttpError(view, request, errorResponse);
                Xlog.d("onReceivedHttpError   fail");
                view.stopLoading();
                view.clearView();
            }

            @Override
            public void onLoadResource(WebView view, String url) {
                super.onLoadResource(view, url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
//                super.onReceivedSslError(view, handler, error);
                Xlog.d("onReceivedSslError   fail");
                view.stopLoading();
                view.clearView();
            }

            /**加载失败的处理 */
            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
//                super.onReceivedError(view, request, error);
                Xlog.d("onReceivedError   fail");
                view.stopLoading();
                view.clearView();
            }
        });

    }

    /**
     * 缓存清理
     */
    public static void clearWebViewCache() {
        // 清理WebView缓存数据库
        try {
            MyApplication.mcontext.deleteDatabase("webview.db");
            MyApplication.mcontext.deleteDatabase("webviewCache.db");
        } catch (Exception e) {
            e.printStackTrace();
        }

        // WebView缓存文件
        File appCacheDir = new File(MyApplication.mcontext.getFilesDir().getAbsolutePath()
                + APP_CACHE_DIRNAME);
        Xlog.d("appCacheDir path=" + appCacheDir.getAbsolutePath());
        Xlog.d("myfi;es path=" + MyApplication.mcontext.getFilesDir().getAbsolutePath());


        File webviewCacheDir = new File(WEBCACHEDIR);
        Xlog.d("appCacheDir path=" + webviewCacheDir.getAbsolutePath());

        // 删除webView缓存目录
        if (webviewCacheDir.exists()) {
            deleteFile(webviewCacheDir);
        }
        // 删除webView缓存，缓存目录
        if (appCacheDir.exists()) {
            deleteFile(appCacheDir);
        }
    }

    public static void deleteFile(File file) {
        Xlog.d("clearing");
        if (file.exists()) {
            if (file.isFile()) {
                file.delete();
            } else if (file.isDirectory()) {
                File files[] = file.listFiles();
                for (int i = 0; i < files.length; i++) {
                    deleteFile(files[i]);
                }
            }
            file.delete();
        } else {

        }
    }


}
