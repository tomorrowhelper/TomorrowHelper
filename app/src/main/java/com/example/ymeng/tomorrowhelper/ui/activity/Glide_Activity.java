package com.example.ymeng.tomorrowhelper.ui.activity;

import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
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

    WebView wvGlide;
  //  String str = "<div style='text-align:center'>致歉信</div><br/>尊敬的出借人：<br/>&nbsp;&nbsp;&nbsp;&nbsp;您好，鉴于“易企融—2018057”于2018年12月6日出现逾期，得易金服第一时间联系借款方，因借款方银行限额问题，未能及时到账，未完成还款日当天还款，给您带来不便，我司已启用相关流程并于目前已全部完成，请您尽快登录得易金服账户查询确认，如有问题请尽快联系我们。<br/>&nbsp;&nbsp;&nbsp;&nbsp;得易金服致力于为出借人及借款人提供一个安全、专业、透明、高效的智能服务平台。在出现问题时，我们将第一时间进行相应处置，确保出借人、借款人利益得以保障，对于给您带来的困扰我们万分抱歉，也感谢您对得易金服的支持与信任。<br/>&nbsp;&nbsp;&nbsp;&nbsp;我们一直保持客户至上的工作态度，为您提供更为优质的服务。<div style='text-align:right; margin-top:100px'>得易金服总经理：侯耀东</div><br/><div style='text-align:right'>得易金服团队</div><br/><div style='text-align:right'>2018年12月7日</div>";
    String str ="尊敬的平台出借人：您好！内容为您所投标的用款需求方的基本情况/还款中标的追踪信息，请您及时查看，祝您生活愉快";

    @Override
    protected int setLayout() {
        return R.layout.glide_activity;
    }

    @Override
    protected void initViews() {
        wvGlide = (WebView) findViewById(R.id.wv_glide);
        int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);
        Log.d("TAG", "Max memory is " + maxMemory + "KB");
    }

    @Override
    protected void initDatas() {

    }

    public void loadImage(View view) {
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
        wvGlide.loadDataWithBaseURL(null,str,"text/html","UTF-8",null);
        wvGlide.getSettings().setJavaScriptEnabled(true);
        wvGlide.getSettings().setLoadsImagesAutomatically(true);
        wvGlide.getSettings().setJavaScriptEnabled(true);
        wvGlide.requestFocus();
        wvGlide.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
    }
}
