package com.example.ymeng.tomorrowhelper.view.anim;

import android.graphics.drawable.AnimationDrawable;
import android.widget.ImageView;

/**
 * Author:YMeng
 * Time:2018/6/7  11:46
 * This is FrameAnimation
 * 帧动画控制类
 */
public class FrameAnimation {
    private ImageView mIvAnimation;
    private AnimationDrawable mAnimationDrawable;

    public FrameAnimation(ImageView ivLoading) {
        this.mIvAnimation = ivLoading;
    }

    /**
     * 显示动画
     */
    public void showAnim() {
        if (mIvAnimation != null && (mAnimationDrawable = (AnimationDrawable) mIvAnimation.getBackground()) != null) {
            mAnimationDrawable.start();
        }
    }


    /**
     * 关闭动画
     */
    public void closeAnim() {
        if (mIvAnimation != null && mAnimationDrawable != null) mAnimationDrawable.stop();
    }

}
