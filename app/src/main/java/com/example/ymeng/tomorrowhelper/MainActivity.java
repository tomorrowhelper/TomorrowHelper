package com.example.ymeng.tomorrowhelper;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.ymeng.tomorrowhelper.view.service.DownLoadService;
import com.zhy.base.fileprovider.FileProvider7;

import java.io.File;

/**
 * 我是MianActivity
 * 在家里修改2018/5/29/ 14:47
 * 在公司修改
 */

public class MainActivity extends AppCompatActivity {
    private DownLoadService.DownloadBinder mDownloadBinder;

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mDownloadBinder = (DownLoadService.DownloadBinder) service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Intent intent = new Intent(this,DownLoadService.class);
       startActivity(intent);
       bindService(intent,mConnection,BIND_AUTO_CREATE);
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
           // ActivityCompat,requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
        }

    }

    /**
     * 安装apk
     *
     * @param view
     */
    public void OperApkBtn(View view) {
        File dirFirstFolder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DeYiJinFu");
        File file = new File(dirFirstFolder, "member.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //2. 设置 category
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        FileProvider7.setIntentDataAndType(MainActivity.this, intent, "application/vnd.android.package-archive", file, true);
        // MainActivity.this.startActivityForResult(intent, 125);// 如果用户取消安装的话,会返回结果,回调方法onActivityResult
        MainActivity.this.startActivity(intent);
    }

    /**
     * 下载
     *
     * @param view
     */
    public void DownloadBtn(View view) {

    }

    /**
     * 暂停
     *
     * @param view
     */
    public void PausedBtn(View view) {

    }

    /**
     * 取消
     *
     * @param view
     */
    public void CanceledBtn(View view) {

    }


}
