package com.example.ymeng.tomorrowhelper.view.activity;

import android.widget.GridView;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.view.activity.base.SimpleActivity;


public class PhotoWallActivity extends SimpleActivity {
    GridView photoWallGV;

    @Override
    protected int setLayout() {
        return R.layout.photo_wall_activity;
    }

    @Override
    protected void initViews() {
        photoWallGV = findViewById(R.id.photo_wall_GV);
    }

    @Override
    protected void initDatas() {

    }

}
