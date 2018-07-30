package com.example.ymeng.tomorrowhelper.view.activity;

/**
 * Author:YMeng
 * Time:2018/7/30  14:14
 * This is JsBridge
 * Webview的js回调到Webview_Activity里（因为在ImoocJsInterface类里不是主线程）
 */
public interface JsBridge {
    void setTextViewValue(String value);
}
