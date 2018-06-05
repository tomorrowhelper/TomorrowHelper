package com.example.ymeng.tomorrowhelper.view.activity.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Author:YMeng
 * Time:2018/6/5  14:54
 * This is SimpleActivity
 */
public abstract class SimpleActivity extends AppCompatActivity {
    protected Activity mActivity;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayout());
        mActivity = this;
        initViews();
        initDatas();

    }

    protected abstract int setLayout();
    protected abstract void initViews();
    protected abstract void initDatas();

}
