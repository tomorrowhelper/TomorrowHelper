package com.example.ymeng.tomorrowhelper.view.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.view.activity.base.SimpleActivity;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.entity.LocalMedia;

import java.util.List;

import butterknife.BindView;

/**
 * Author:YMeng
 * Time:2018/9/4  9:42
 * This is CharacterActivity
 * 抖音字符画
 */
public class CharacterActivity extends SimpleActivity {

    @BindView(R.id.btn_pick)
    Button btnPick;
    @BindView(R.id.btn_save)
    Button btnSave;
    ImageView image;

    private Bitmap bitmap;
    private String filepath;

    @Override
    protected int setLayout() {
        return R.layout.character_activity;
    }

    @Override
    protected void initViews() {
        image = (ImageView) findViewById(R.id.image);
        bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.ddd);
    }

    @Override
    protected void initDatas() {

    }
    public void doPick(View view) {
        CharacterCommonUtil.choosePhoto(this, PictureConfig.CHOOSE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            if (requestCode == PictureConfig.CHOOSE_REQUEST) {
                List<LocalMedia> selectList = PictureSelector.obtainMultipleResult(data);
                String path = "";
                if (selectList != null && selectList.size() > 0) {
                    LocalMedia localMedia = selectList.get(0);
                    if (localMedia.isCompressed()) {
                        path = localMedia.getCompressPath();
                    } else if (localMedia.isCut()) {
                        path = localMedia.getCutPath();
                    } else {
                        path = localMedia.getPath();
                    }
                }
                filepath = CharacterCommonUtil.amendRotatePhoto(path, CharacterActivity.this);
//                imageView.setImageBitmap(BitmapFactory.decodeFile(filepath));
                bitmap = CharacterCommonUtil.createAsciiPic(filepath, CharacterActivity.this);
                image.setImageBitmap(bitmap);
            }
        }
    }

    public void doSave(View view) {
        CharacterCommonUtil.saveBitmap2file(bitmap, System.currentTimeMillis() + "", CharacterActivity.this);
    }

}
