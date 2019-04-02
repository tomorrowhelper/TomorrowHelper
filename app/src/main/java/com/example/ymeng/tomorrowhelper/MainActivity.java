package com.example.ymeng.tomorrowhelper;

import android.Manifest;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.ymeng.tomorrowhelper.model.bean.Bean;
import com.example.ymeng.tomorrowhelper.ui.activity.BottomSheet_Activity;
import com.example.ymeng.tomorrowhelper.ui.activity.CharacterActivity;
import com.example.ymeng.tomorrowhelper.ui.activity.CoordLayoutTwoActivity;
import com.example.ymeng.tomorrowhelper.ui.activity.CropImageActivity;
import com.example.ymeng.tomorrowhelper.ui.activity.Dagger_Activity;
import com.example.ymeng.tomorrowhelper.ui.activity.DetailsActivity;
import com.example.ymeng.tomorrowhelper.ui.activity.FingerLoginActivity;
import com.example.ymeng.tomorrowhelper.ui.activity.FloatingWindowActivty;
import com.example.ymeng.tomorrowhelper.ui.activity.Glide_Activity;
import com.example.ymeng.tomorrowhelper.ui.activity.HorizontalChartActivity;
import com.example.ymeng.tomorrowhelper.ui.activity.HorizontalChartRecyclerActivity;
import com.example.ymeng.tomorrowhelper.ui.activity.LambdaBtmActivity;
import com.example.ymeng.tomorrowhelper.ui.activity.Lottie_Activity;
import com.example.ymeng.tomorrowhelper.ui.activity.PhotoWallActivity;
import com.example.ymeng.tomorrowhelper.ui.activity.RecyclerActivty;
import com.example.ymeng.tomorrowhelper.ui.activity.Webview_Activity;
import com.example.ymeng.tomorrowhelper.ui.service.DownLoadService;
import com.example.ymeng.tomorrowhelper.util.Code;
import com.example.ymeng.tomorrowhelper.util.ToastUtil;
import com.google.gson.Gson;

/**
 * 我是MianActivity
 * 在家里修改2018/5/29/ 14:47
 * 在公司修改
 * 在dev分支修改
 * 我在主分支2018/6/29 11:02
 */

public class MainActivity extends AppCompatActivity {
    private ImageView mImage;
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
        mImage = (ImageView) findViewById(R.id.Get_Image);
        Intent intent = new Intent(this, DownLoadService.class);
        // startActivity(intent);
        startService(intent);
        bindService(intent, mConnection, BIND_AUTO_CREATE);
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);

        }

        mImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //时间戳
                long timeStampSec = System.currentTimeMillis() / 1000;
                String timestamp = String.format("%010d", timeStampSec);
                //4.0以上的glide版本
             /*   RequestOptions options = new RequestOptions()
                        .centerCrop()
                        .signature(new ObjKey(""+timestamp))
                        .skipMemoryCache(true);*/

                Toast.makeText(MainActivity.this, "mImage", Toast.LENGTH_SHORT).show();
                Glide.with(MainActivity.this)
                        // .load("http://192.168.30.134:9999/login/registVerfyCode?randkey="+uuid+timestamp)
                        .load("http://192.168.30.134:9999/login/registVerfyCode?randkey=e65b5443-928a-4058-8ce9-466e569a4fb41531368234")
                        //4.0以上的glide版本
                        // .apply(options)

                        .into(mImage);
            }
        });

    }

    /**
     * 安装apk
     *
     * @param view
     */
    public void OperApkBtn(View view) {
   /*     File dirFirstFolder = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + "/DeYiJinFu");
        File file = new File(dirFirstFolder, "member.apk");
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        //2. 设置 category
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        FileProvider7.setIntentDataAndType(MainActivity.this, intent, "application/vnd.android.package-archive", file, true);
        // MainActivity.this.startActivityForResult(intent, 125);// 如果用户取消安装的话,会返回结果,回调方法onActivityResult
        MainActivity.this.startActivity(intent);*/
       // NotificationHelper notificationUtils = new NotificationHelper(this);
       // notificationUtils.sendNotification("测试标题", "测试内容");

    }

    /**
     * 下载
     *
     * @param view
     */
    public void DownloadBtn(View view) {
        String url = "https://raw.githubusercontent.com/guolindev/eclipse/master/eclipse-inst-win64.exe";
        mDownloadBinder.startDownload(url);

    }

    /**
     * 暂停
     *
     * @param view
     */
    public void PausedBtn(View view) {
        mDownloadBinder.pauseDownload();
    }

    /**
     * 取消
     *
     * @param view
     */
    public void CanceledBtn(View view) {
        mDownloadBinder.canceledDownload();
    }

    /**
     * 悬浮窗界面
     *
     * @param view
     */
    public void WindowBtn(View view) {
        startActivity(new Intent(this, FloatingWindowActivty.class));
    }

    /**
     * Recycleview
     *
     * @param view
     */
    public void RecyclerBtn(View view) {
        startActivity(new Intent(this, RecyclerActivty.class));
    }


    public void GsonBtn(View view) {
      /*  //uuid
        String uuid = UUID.randomUUID().toString();
        //时间戳
        long timeStampSec = System.currentTimeMillis() / 1000;
        String timestamp = String.format("%010d", timeStampSec);

        Toast.makeText(this, "" + uuid, Toast.LENGTH_SHORT).show();

        Glide.with(this)
                // .load("http://192.168.30.134:9999/login/registVerfyCode?randkey="+uuid+timestamp)
                .load("http://192.168.30.134:9999/login/registVerfyCode?randkey=e65b5443-928a-4058-8ce9-466e569a4fb41531368234")
                //.apply(options)
                .into(mImage);
        Log.e("TAG", "http://192.168.30.134:9999/login/registVerfyCode?randkey=" + uuid + timestamp);*/

        Bean bean = new Gson().fromJson("{ \"success\": false}", Bean.class);
        if(bean.getMessage()==null|bean.getMessage().getEm()==null){
            Toast.makeText(this, "为空", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, bean.getMessage().getEm()+"", Toast.LENGTH_SHORT).show();
        }


    }

    public void ImageBtn(View view) {
        Code.getInstance().setCode("q1wa", 4);
        mImage.setImageBitmap(Code.getInstance().createBitmap());
        String realCode = Code.getInstance().getCode().toLowerCase();

        Toast.makeText(this, "" + realCode, Toast.LENGTH_SHORT).show();
    }


    /**
     * Glide相关
     */
    public void GlideBtn(View view) {
        startActivity(new Intent(this, Glide_Activity.class));

    }

    /**
     * 裁剪Image
     */
    public void CropImageBtm(View view) {
        startActivity(new Intent(this, CropImageActivity.class));
    }

    /**
     * 缓存照片墙
     */
    public void LruCacheBtm(View view) {
        startActivity(new Intent(this, PhotoWallActivity.class));
    }

    /**
     * Webview
     */
    public void WvBtm(View view) {
        startActivity(new Intent(this, Webview_Activity.class));
    }

    /**
     * BottomSheet
     */
    public void BottomSheet(View view) {
        startActivity(new Intent(this, BottomSheet_Activity.class));
    }

    /**
     * 让图片动起来
     */
    public void LottieBtn(View view) {
        startActivity(new Intent(this, Lottie_Activity.class));
    }

    /**
     * dagger
     */
    public void daggerBtn(View view) {
        startActivity(new Intent(this, Dagger_Activity.class));
    }

    /**
     * 指纹识别
     */
    public void fingerBtn(View view) {
        startActivity(new Intent(this, FingerLoginActivity.class));
    }

    /**
     * 抖音字符画
     */
    public void characterBtn(View view) {
        startActivity(new Intent(this, CharacterActivity.class));
    }

    /**
     * 仿电商 商品详情
     */
    public void detailsBtn(View view) {
        startActivity(new Intent(this, DetailsActivity.class));
    }
    /**
     * 横向柱状图
     */
    public void horizontalChartViewBtm(View view) {
        startActivity(new Intent(this, HorizontalChartActivity.class));
    }

    /**
     * 横向柱状图
     */
    public void horizontalChartRecyclerViewBtm(View view) {
        startActivity(new Intent(this, HorizontalChartRecyclerActivity.class));
    }

    /**
     * lambda表达式
     */
    public void lambdaBtm(View view) {
        startActivity(new Intent(this, LambdaBtmActivity.class));

    }

    /**
     * 饼状图
     */
    public void pieChartBtm(View view) {
    }

    /**
     * CoordLayout联动
     */
    public void CoordLayoutBtn(View view) {
       // startActivity(new Intent(this, CoordLayoutActivity.class));
        startActivity(new Intent(this, CoordLayoutTwoActivity.class));
    }





    /**
     * 4984513019362056
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case 1:
                if (grantResults.length > 0 && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    ToastUtil.show("权限拒绝");
                    finish();
                }
                break;
            default:
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(mConnection);
    }
}
