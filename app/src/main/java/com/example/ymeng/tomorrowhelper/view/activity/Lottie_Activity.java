package com.example.ymeng.tomorrowhelper.view.activity;

import android.content.Intent;
import android.view.View;

import com.airbnb.lottie.LottieAnimationView;
import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.view.activity.base.SimpleActivity;

/**
 * Author:YMeng
 * Time:2018/8/13  16:03
 * This is Lottie_Activity
 * 让图片动起来
 */
public class Lottie_Activity extends SimpleActivity {
    private boolean mIsLottie = true;
    private LottieAnimationView mLottieAnimationView;

    @Override
    protected int setLayout() {
        return R.layout.lottie_activity;
    }

    @Override
    protected void initViews() {
        mLottieAnimationView = (LottieAnimationView) findViewById(R.id.lottie_animation_view);
        mLottieAnimationView.playAnimation();//开始动画
    }

    @Override
    protected void initDatas() {

    }

    public void doclick(View v) {
        switch (v.getId()) {
            case R.id.lottie_button0:
                if(mIsLottie){
                    mIsLottie = false;
                    //暂停动画
                    if (mLottieAnimationView.isAnimating()){
                        mLottieAnimationView.pauseAnimation();
                    }
                }else{
                    mIsLottie = true;
                    //继续动画
                    if (!mLottieAnimationView.isAnimating()) {
                        mLottieAnimationView.resumeAnimation();
                    }
                }


                break;
            case R.id.lottie_button1:
                //通过代码实现动画和回调等
                  startActivity(new Intent(this,LottieClickActivity.class));
                break;
            case R.id.lottie_button2:
                //从网络上获取动画效果
                 startActivity(new Intent(this,LottieNetworkActivity.class));
                break;
        }
    }
}
