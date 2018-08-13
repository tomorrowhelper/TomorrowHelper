package com.example.ymeng.tomorrowhelper.view.activity;

import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.view.View;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.view.activity.base.SimpleActivity;

/**
 * Author:YMeng
 * Time:2018/8/13  14:08
 * This is BottomSheet_Activity
 * BottomSheet
 */
public class BottomSheet_Activity extends SimpleActivity {
    private BottomSheetBehavior mBehavior;
    @Override
    protected int setLayout() {
        return R.layout.bottom_sheet_activity;
    }

    @Override
    protected void initViews() {
        View bottomSheet = findViewById(R.id.sheet_bottom_scroll);
        mBehavior = BottomSheetBehavior.from(bottomSheet);
        mBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {
                //这里是bottomSheet 状态的改变
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                //这里是拖拽中的回调，根据slideOffset可以做一些动画
            }
        });
    }

    @Override
    protected void initDatas() {

    }

    public void doclick(View v)
    {
        switch (v.getId()) {
            case R.id.sheet_button0:
                if(mBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
                    mBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }else {
                    mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                }
                break;
            case R.id.sheet_button1:
                //BottomSheetDialog直接把视图给他
                BottomSheetDialog mBottomSheetDialog = new BottomSheetDialog(this);
                View view = getLayoutInflater().inflate(R.layout.dialog_bottom_sheet, null);
                mBottomSheetDialog.setContentView(view);
                mBottomSheetDialog.show();
                break;
            case R.id.sheet_button2:
                new FullSheetDialogFragment().show(getSupportFragmentManager(), "dialog");
                break;
        }
    }
}
