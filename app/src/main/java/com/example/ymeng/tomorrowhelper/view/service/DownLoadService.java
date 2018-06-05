package com.example.ymeng.tomorrowhelper.view.service;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Environment;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import com.example.ymeng.tomorrowhelper.MainActivity;
import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.util.ToastUtil;
import com.example.ymeng.tomorrowhelper.view.activity.DownLoadTask;
import com.example.ymeng.tomorrowhelper.view.interfacefolder.DownloadListener;

import java.io.File;

/**
 * Author:YMeng
 * Time:2018/6/5  16:09
 * This is DownLoadService
 * 下载服务
 */
public class DownLoadService extends Service {
    private DownLoadTask mDownLoadTask;
    private String mDownloadUrl;

    private DownloadListener mDownloadListener = new DownloadListener() {
        @Override
        public void onProgress(int progress) {
            getNotificantionManager().notify(1, getNotificantion("Downloading...", progress));
        }

        @Override
        public void onSuccess() {
            mDownLoadTask = null;
            //下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificantionManager().notify(1, getNotificantion("Download Success", -1));
            Toast.makeText(DownLoadService.this, "Download Success", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailed() {
            mDownLoadTask = null;
            //下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            getNotificantionManager().notify(1, getNotificantion("Download Failed", -1));
            Toast.makeText(DownLoadService.this, "Download Failed", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onPaused() {
            mDownLoadTask = null;

            Toast.makeText(DownLoadService.this, "Paused", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCanceled() {
            mDownLoadTask = null;
            //下载成功时将前台服务通知关闭，并创建一个下载成功的通知
            stopForeground(true);
            ToastUtil.show("Canceled");
            Toast.makeText(DownLoadService.this, "Canceled", Toast.LENGTH_SHORT).show();
        }
    };

    private DownloadBinder mBinder = new DownloadBinder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

   public class DownloadBinder extends Binder {
        public void startDownload(String url) {
            if (mDownLoadTask == null) {
                mDownloadUrl = url;
                mDownLoadTask = new DownLoadTask(mDownloadListener);
                mDownLoadTask.execute(mDownloadUrl);
                startForeground(1, getNotificantion("Downloading...", 0));

                Toast.makeText(DownLoadService.this, "Downloading...", Toast.LENGTH_SHORT).show();
            }

        }

        /**
         * 暂停
         */
        public void pauseDownload() {
            if (mDownLoadTask != null)
                mDownLoadTask.puaseDownload();
        }

        /**
         * 取消
         */
        public void canceledDownload() {
            if (mDownLoadTask != null) {
                mDownLoadTask.canceledDownload();
            } else {
                if (mDownloadUrl != null) {
                    //取消下载时需要将文件删除，并将通知关闭
                    String fileName = mDownloadUrl.substring(mDownloadUrl.lastIndexOf("/"));
                    String directory = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS).getPath();
                    File file = new File(directory + fileName);
                    if (file.exists()) {
                        file.delete();
                    }
                    getNotificantionManager().cancel(1);
                    stopForeground(true);

                    Toast.makeText(DownLoadService.this, "canceled", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    private NotificationManager getNotificantionManager() {
        return (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    private Notification getNotificantion(String title, int progress) {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        if (progress > 0) {
            //当下载进度大于0时才显示下载进度
            builder.setContentText(progress + " %");
            builder.setProgress(100, progress, false);
        }

        return builder.build();
    }

}
