package com.example.ymeng.tomorrowhelper.ui.interfacefolder;

/**
 * Author:YMeng
 * Time:2018/6/5  15:06
 * This is DownloadListener
 * 下载接口
 */
public interface DownloadListener {
    /**
     * 下载进度
     * @param progress
     */
    void onProgress(int progress);

    /**
     * 下载成功
     */
    void onSuccess();

    /**
     * 下载失败
     */
    void onFailed();

    /**
     * 下载暂停
     */
    void onPaused();

    /**
     * 下载取消
     */
    void onCanceled();


}
