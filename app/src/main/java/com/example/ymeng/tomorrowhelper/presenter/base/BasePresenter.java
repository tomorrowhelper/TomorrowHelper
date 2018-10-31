package com.example.ymeng.tomorrowhelper.presenter.base;

import com.example.ymeng.tomorrowhelper.ui.activity.base.BaseActivity;

import java.lang.ref.WeakReference;

/**
 * Author:YMeng
 * Time:2018/5/30  15:15
 * This is BasePresenter
 *
 */
public class BasePresenter<T extends IBaseView> {
    private T mIView;

    protected WeakReference<BaseActivity> mBaseViewActivity;

    public BasePresenter(T t, BaseActivity activity) {
        mIView = t;
        mBaseViewActivity = new WeakReference<>(activity);
    }

    public BasePresenter(BaseActivity activity) {
        mBaseViewActivity = new WeakReference<>(activity);
    }
    public BasePresenter(T t) {
        mIView = t;
    }

    public BasePresenter() {
    }

    /**
     * 释放引用，防止内存泄露
     */
    public void destroy() {
        mIView = null;
    }

}
