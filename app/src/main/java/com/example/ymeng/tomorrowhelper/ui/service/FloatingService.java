package com.example.ymeng.tomorrowhelper.ui.service;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.ymeng.tomorrowhelper.R;

import java.io.IOException;

/**
 * Author:YMeng
 * Time:2018/6/20  16:42
 * This is FloatingService
 * 悬浮窗口服务
 */
public class FloatingService extends Service {

    private WindowManager windowManager;
    private WindowManager.LayoutParams layoutParams;
    private View displayView;
    private int images[] = {R.drawable.image_a,R.drawable.image_b,R.drawable.image_c};
    private int imageIndex = 0;
    private Handler changeImageHandler;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        changeImageHandler = new Handler(this.getMainLooper(), changeImageCallback);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
       showFloatingWindow();
        return super.onStartCommand(intent, flags, startId);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void showFloatingWindow() {
        if (Settings.canDrawOverlays(this)) {
            // 获取WindowManager服务
            windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

            LayoutInflater layoutInflater = LayoutInflater.from(this);
            displayView = layoutInflater.inflate(R.layout.image_display, null);
            displayView.setOnTouchListener(new FloatingOnTouchListener());
            displayView.setBackgroundColor(getResources().getColor(R.color.black));
            ImageView imageView = displayView.findViewById(R.id.image_display_imageview);
            imageView.setImageResource(images[imageIndex]);
            displayView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    windowManager.removeView(displayView);
                }
            });



        /*    // 新建悬浮窗控件
            Button button = new Button(getApplicationContext());
            button.setText("Floating Window");
            button.setBackgroundColor(Color.BLUE);
            button.setTextColor(Color.WHITE);
            button.setOnTouchListener(new FloatingOnTouchListener());
*/
            // 设置LayoutParam
            layoutParams = new WindowManager.LayoutParams();
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                layoutParams.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
            } else {
                layoutParams.type = WindowManager.LayoutParams.TYPE_PHONE;
            }
            //不添加这个权限无法点击悬浮窗外
            layoutParams.flags = WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL
                    | WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE;

            layoutParams.gravity = Gravity.LEFT | Gravity.TOP;
            layoutParams.format = PixelFormat.RGBA_8888;
            layoutParams.width = 500;
            layoutParams.height = 300;
            layoutParams.x = 300;
            layoutParams.y = 300;
            //第一次
            // 将悬浮窗控件添加到WindowManager
            //  windowManager.addView(button, layoutParams);
            //第二次
           // windowManager.addView(displayView, layoutParams);
           // changeImageHandler.sendEmptyMessageDelayed(0, 2000);
            //--------------------------
            final MediaPlayer mediaPlayer = new MediaPlayer();
            mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            SurfaceView surfaceView = displayView.findViewById(R.id.video_display_surfaceview);
            final SurfaceHolder surfaceHolder = surfaceView.getHolder();
            surfaceHolder.addCallback(new SurfaceHolder.Callback(){
                @Override
                public void surfaceCreated(SurfaceHolder holder) {
                    mediaPlayer.setDisplay(surfaceHolder);
                }

                @Override
                public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

                }

                @Override
                public void surfaceDestroyed(SurfaceHolder holder) {

                }
            });
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            try {
                mediaPlayer.setDataSource(this, Uri.parse("https://raw.githubusercontent.com/dongzhong/ImageAndVideoStore/master/Bruno%20Mars%20-%20Treasure.mp4"));
                mediaPlayer.prepareAsync();
            }
            catch (IOException e) {
                Toast.makeText(this, "无法打开视频源", Toast.LENGTH_LONG).show();
            }
            windowManager.addView(displayView, layoutParams);

            //-----------------------
        }
    }
    private Handler.Callback changeImageCallback = new Handler.Callback() {
        @Override
        public boolean handleMessage(Message msg) {
            if (msg.what == 0) {
                imageIndex++;
                if (imageIndex >= 3) {
                    imageIndex = 0;
                }
                if (displayView != null) {
                    ((ImageView) displayView.findViewById(R.id.image_display_imageview)).setImageResource(images[imageIndex]);
                }

                changeImageHandler.sendEmptyMessageDelayed(0, 2000);
            }
            return false;
        }
    };

    private class FloatingOnTouchListener implements View.OnTouchListener {
        private int x;
        private int y;

        @Override
        public boolean onTouch(View view, MotionEvent event) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x = (int) event.getRawX();
                    y = (int) event.getRawY();
                    break;
                case MotionEvent.ACTION_MOVE:
                    int nowX = (int) event.getRawX();
                    int nowY = (int) event.getRawY();
                    int movedX = nowX - x;
                    int movedY = nowY - y;
                    x = nowX;
                    y = nowY;
                    layoutParams.x = layoutParams.x + movedX;
                    layoutParams.y = layoutParams.y + movedY;

                    // 更新悬浮窗控件布局
                    windowManager.updateViewLayout(view, layoutParams);
                    break;
                default:
                    break;
            }
            return false;
        }
    }
}
