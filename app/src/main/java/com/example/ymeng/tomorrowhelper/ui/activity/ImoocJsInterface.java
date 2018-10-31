package com.example.ymeng.tomorrowhelper.ui.activity;

import android.util.Log;
import android.webkit.JavascriptInterface;

/**
 * Author:YMeng
 * Time:2018/7/30  10:03
 * This is ImoocJsInterface
 * WebView 的接口类
 */
public class ImoocJsInterface {
    private JsBridge jsBridge;
    private static final String TAG= "ImoocJsInterface";

    public ImoocJsInterface(JsBridge jsBridge) {
        this.jsBridge = jsBridge;
    }

    /**
     * 这个方法不在主线程执行
     * @param value
     */
    @JavascriptInterface
    public void setValue(String value){
        Log.d(TAG, "value: ="+value);
        jsBridge.setTextViewValue(value);
        throw new RuntimeException("RuntimeException");
    }
}
