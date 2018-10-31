package com.example.ymeng.tomorrowhelper.ui.activity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.ui.activity.base.SimpleActivity;

/**
 * Author:YMeng
 * Time:2018/8/14  12:00
 * This is LottieNetworkActivity
 * Lottie - 网络请求
 */
public class LottieNetworkActivity extends SimpleActivity {
   private LottieAnimationView animation_view_network;

    @Override
    protected int setLayout() {
        return R.layout.lottie_network_activity;
    }

    @Override
    protected void initViews() {
        animation_view_network = (LottieAnimationView) findViewById(R.id.animation_view_network);

    }

    @Override
    protected void initDatas() {

    }
}
