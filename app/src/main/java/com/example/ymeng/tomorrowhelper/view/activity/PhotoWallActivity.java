package com.example.ymeng.tomorrowhelper.view.activity;

import android.widget.GridView;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.app.Images;
import com.example.ymeng.tomorrowhelper.view.activity.base.SimpleActivity;
import com.example.ymeng.tomorrowhelper.view.adapter.PhotoWallAdapter;

public class PhotoWallActivity extends SimpleActivity {
    /**
     *用于展示照片墙的GridView
     */
    private GridView mPhotoWallGV;
    /**
     * GridView的适配器
     */
    private PhotoWallAdapter adapter;

    @Override
    protected int setLayout() {
        return R.layout.photo_wall_activity;
    }

    @Override
    protected void initViews() {
        mPhotoWallGV = findViewById(R.id.photo_wall_GV);

    }

    @Override
    protected void initDatas() {
        adapter = new PhotoWallAdapter(this, 0, Images.imageThumbUrls, mPhotoWallGV);
        mPhotoWallGV.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        adapter.cancelAllTasks();
    }
}
