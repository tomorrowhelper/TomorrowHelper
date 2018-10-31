package com.example.ymeng.tomorrowhelper.ui.activity;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.ui.activity.base.SimpleActivity;

import butterknife.BindView;

/**
 * Author:YMeng
 * Time:2018/7/19  9:56
 * This is Glide_Activity
 * 使用Glide版本3.7.0
 */
public class Glide_Activity extends SimpleActivity {
    @BindView(R.id.image_view)
    ImageView imageView;

    @Override
    protected int setLayout() {
        return R.layout.glide_activity;
    }

    @Override
    protected void initViews() {
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB");
    }

    @Override
    protected void initDatas() {

    }

    public void loadImage(View view){
        String path = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
        String urlGif = "http://p1.pstatp.com/large/166200019850062839d3";
        Glide.with(this)
                .load(path)
                //.asBitmap()//只加载静态图片
                //.asGif()//只加载Gif图片，如果强制静态图片加载，就会错误，显示error图片
                //        .placeholder(R.drawable.loading)
                //        .error(R.drawable.error)
                //        .crossFade()
                 //     .diskCacheStrategy(DiskCacheStrategy.NONE)//禁止缓存
                .into(imageView);
        Glide.with(this).load(path).into(imageView);

    }

}
