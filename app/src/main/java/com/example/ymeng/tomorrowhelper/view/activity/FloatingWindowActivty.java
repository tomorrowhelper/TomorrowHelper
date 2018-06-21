package com.example.ymeng.tomorrowhelper.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.webkit.WebView;
import android.widget.Toast;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.view.activity.base.SimpleActivity;
import com.example.ymeng.tomorrowhelper.view.service.FloatingService;

/**
 * Author:YMeng
 * Time:2018/6/20  15:51
 * This is FloatingWindowActivty
 * 悬浮窗
 */
public class FloatingWindowActivty extends SimpleActivity {

    @Override
    protected int setLayout() {
        return R.layout.floating_window_activity;
    }

    @Override
    protected void initViews() {

    }

    @Override
    protected void initDatas() {
        //   startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), REQUEST_CODE);

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onWindowBtn1(View view) {
        //获取权限
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
        } else {
            Intent mIntentService = new Intent(this, FloatingService.class);
            mIntentService.putExtra("count", 1);
            startService(mIntentService);

        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onWindowBtn2(View view) {
        //获取权限
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
        } else {
            Intent mIntentService = new Intent(this, FloatingService.class);
            mIntentService.putExtra("count", 2);
            startService(mIntentService);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onWindowBtn3(View view) {
        //获取权限
        if (!Settings.canDrawOverlays(this)) {
            Toast.makeText(this, "当前无权限，请授权", Toast.LENGTH_SHORT);
            startActivityForResult(new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION, Uri.parse("package:" + getPackageName())), 0);
        } else {
            Intent mIntentService = new Intent(this, FloatingService.class);
            mIntentService.putExtra("count", 3);
            startService(mIntentService);
        }
    }

    public void onWindowBtn4(View view) {
       /* ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.SYSTEM_ALERT_WINDOW,}, 1);*/
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0) {
            if (!Settings.canDrawOverlays(this)) {
                Toast.makeText(this, "授权失败", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "授权成功", Toast.LENGTH_SHORT).show();
                startService(new Intent(this, FloatingService.class));
            }
        }
    }

    /*@SystemService(Context.WINDOW_SERVICE)
    public interface WindowManager extends ViewManager {
    }*/
}
