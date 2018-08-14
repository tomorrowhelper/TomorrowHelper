package com.example.ymeng.tomorrowhelper.view.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieComposition;
import com.airbnb.lottie.OnCompositionLoadedListener;
import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.view.activity.base.SimpleActivity;
/**
 * Author:YMeng
 * Time:2018/8/14  11:36
 * This is LottieClickActivity
 * Lottie-让图片动起来
 */
public class LottieClickActivity extends SimpleActivity {

    private Button button1, button2;
    private TextView tv_seek;
    LottieAnimationView animation_view_click;

    @Override
    protected int setLayout() {
        return R.layout.lottie_click_activity;
    }

    @Override
    protected void initViews() {
        animation_view_click = (LottieAnimationView) findViewById(R.id.lottie_click_animation_view);
        tv_seek = (TextView) findViewById(R.id.lottie_click_tv_seek);

    }

    @Override
    protected void initDatas() {
     //  animation_view_click.setAnimation("LottieLogo1.json", LottieAnimationView.CacheStrategy.Strong);
        LottieComposition.Factory.fromAssetFileName(this, "LottieLogo1.json",
                new OnCompositionLoadedListener() {
                    @Override public void onCompositionLoaded(@Nullable LottieComposition composition) {
                        animation_view_click.setComposition(composition);
                    }
                });

       //监听Lottie生命周期
        animation_view_click.addAnimatorListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {
                Toast.makeText(mActivity, "Start", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationEnd(Animator animator) {
                Toast.makeText(mActivity, "End", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }


            @Override
            public void onAnimationRepeat(Animator animator) {

            }

        });

     /*   LottieComposition.fromAssetFileName(this, "LottieLogo1.json", new LottieComposition.OnCompositionLoadedListener() {

            @Override
            public void onCompositionLoaded(LottieComposition composition) {
                animation_view_click.setComposition(composition);
                animation_view_click.setProgress(0.333f);

                animation_view_click.playAnimation();
            }
        });*/


        animation_view_click.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                tv_seek.setText(" 动画进度" +(int) (animation.getAnimatedFraction()*100) +"%");
            }
        });
    }

    public void clickBtn(View view) {
        switch (view.getId()) {
            case R.id.lottie_click_button1:
                startAnima();
                break;
            case R.id.lottie_click_button2:
                stopAnima();
                break;
        }
    }



    /*
     * 开始动画
     */
    private  void startAnima(){

        boolean inPlaying = animation_view_click.isAnimating();
        if (!inPlaying) {
            animation_view_click.setProgress(0f);
            animation_view_click.playAnimation();
        }
    }
    /*
     * 停止动画
     */
    private  void stopAnima(){
        boolean inPlaying = animation_view_click.isAnimating();
        if (inPlaying) {
            animation_view_click.cancelAnimation();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        animation_view_click.cancelAnimation();
    }
}
