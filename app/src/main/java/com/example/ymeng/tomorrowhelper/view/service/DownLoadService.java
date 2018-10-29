package com.example.ymeng.tomorrowhelper.view.service;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Binder;
import android.os.Build;
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
            //下载失败时将前台服务通知关闭，并创建一个下载成功的通知
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

    @TargetApi(Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    private NotificationManager getNotificantionManager() {
        NotificationManager  notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "1";
            String channelName = "下载";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);

            channelId = "2";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);
        }
        return notificationManager;
    }

    private Notification getNotificantion(String title, int progress) {

 /*       NotificationCompat.Builder notifyBuilder =
                new NotificationCompat.Builder( this ).setContentTitle( title )
                        .setContentText( "显示" )
                        .setSmallIcon( R.mipmap.ic_launcher )
                        // 点击消失
                        .setAutoCancel( true )
                        // 设置该通知优先级
                        .setPriority( Notification.PRIORITY_MAX )
                        .setLargeIcon( BitmapFactory.decodeResource( getResources(), R.mipmap.ic_launcher ) )
                        .setTicker( "mTicker" )
                        // 通知首次出现在通知栏，带上升动画效果的
                        .setWhen( System.currentTimeMillis() )
                        // 通知产生的时间，会在通知信息里显示
                        // 向通知添加声音、闪灯和振动效果的最简单、最一致的方式是使用当前的用户默认设置，使用defaults属性，可以组合：
                        .setDefaults( Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND );
        PendingIntent resultPendingIntent =
                PendingIntent.getActivity( this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT );
        notifyBuilder.setContentIntent( resultPendingIntent );
        getNotificantionManager().notify( 1, notifyBuilder.build() );*/
//-------------------------------------------------------------------------------------
     /*   String id = "my_channel_01";
        String name="我是渠道名字";
        NotificationManager  notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Notification notification = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel mChannel = new NotificationChannel(id, name, NotificationManager.IMPORTANCE_LOW);
            Toast.makeText(this, mChannel.toString(), Toast.LENGTH_SHORT).show();
            Log.i("TAG", mChannel.toString());
            notificationManager.createNotificationChannel(mChannel);
            notification = new Notification.Builder(this)
                    .setChannelId(id)
                    .setContentTitle("5 new messages")
                    .setContentText("hahaha")
                    .setSmallIcon(R.mipmap.ic_launcher).build();
        } else {
            NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                    .setContentTitle("5 new messages")
                    .setContentText("hahaha")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setOngoing(true);
                  //  .setChannel(id)//无效
            notification = notificationBuilder.build();
        }
        notificationManager.notify(111123, notification);*/
//-------------------------------------------------------------------------------------


       Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);//如果在栈顶则无需创建新的实例
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
      //  Notification builder = new NotificationCompat.Builder(this,"1");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setContentIntent(pi);
        builder.setContentTitle(title);
        builder.setPriority(1000) ;
        builder.setAutoCancel(true) ;
       // builder.setVibrate(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400}) ;
        //  builder.setDefaults(Notification.DEFAULT_SOUND | Notification.DEFAULT_VIBRATE) ;
        // builder.setNumber(3) ;//设置显示角标的数量
        //  builder.setBadgeIconType(BADGE_ICON_SMALL);//设置显示角标的样式
     //     builder.setOngoing(false);//设置这是否为正在进行的通知。
      // builder.setTimeoutAfter(5000);//设置通知被创建多长时间之后自动取消通知栏的通知。
       // builder.setDefaults(Notification.DEFAULT_ALL);//取消震动无效
        // builder.setDefaults(Notification.DEFAULT_SOUND);//取消震动
        //  builder.setVibrate(new long[]{0l}); //取消震动
        if (progress > 0) {
            //当下载进度大于0时才显示下载进度
            builder.setContentText(progress + " %");
            builder.setProgress(100, progress, false);
        }
        return builder.build();
    }

}
