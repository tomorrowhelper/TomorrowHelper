package com.example.ymeng.tomorrowhelper.ui.activity;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.ui.activity.base.SimpleActivity;

/**
 * Author:YMeng
 * Time:2018/7/19  13:55
 * This is CropImageActivity
 */
public class CropImageActivity extends SimpleActivity {
    ImageView CropImageView;

    @Override
    protected int setLayout() {
        return R.layout.crop_image_activity;
    }

    @Override
    protected void initViews() {
        CropImageView = (ImageView) findViewById(R.id.Crop_image_view);
    }

    @Override
    protected void initDatas() {

    }

    public void loadImage(View view) {
        CropImageView.setImageBitmap(
                decodeSampledBitmapFromResource(getResources(), R.drawable.image_c, 100, 100));


       Log.e("TAG", "onCreate: width = " + CropImageView.getDrawable().getBounds().width() + "   height = " + CropImageView.getDrawable().getBounds().height());

    }

    /**
     * 注意：没有小数
     * 图片缩放比例规则为，我一张图片为300*300px的，reqWidth=100，reqHeight=150
     * 结果为 300/100=3,300/150=2
     * heightRatio < widthRatio ? heightRatio : widthRatio
     * 图片在300的基础上/2，得到的实际宽高就是150*150px
     */
    public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
                                                         int reqWidth, int reqHeight) {
        // 第一次解析将inJustDecodeBounds设置为true，来获取图片大小
        final BitmapFactory.Options options = new BitmapFactory.Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeResource(res, resId, options);
        // 调用上面定义的方法计算inSampleSize值
        options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);
        // 使用获取到的inSampleSize值再次解析图片
        options.inJustDecodeBounds = false;
        return BitmapFactory.decodeResource(res, resId, options);

    }


    public static int calculateInSampleSize(BitmapFactory.Options options,
                                            int reqWidth, int reqHeight) {
        // 源图片的高度和宽度
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;
        if (height > reqHeight || width > reqWidth) {
            // 计算出实际宽高和目标宽高的比率
            final int heightRatio = Math.round((float) height / (float) reqHeight);
            final int widthRatio = Math.round((float) width / (float) reqWidth);
            // 选择宽和高中最小的比率作为inSampleSize的值，这样可以保证最终图片的宽和高
            // 一定都会大于等于目标的宽和高。
            inSampleSize = heightRatio < widthRatio ? heightRatio : widthRatio;
            Log.e("TAG", inSampleSize+"---heightRatio: "+heightRatio+"-----widthRatio:"+widthRatio);
        }
        return inSampleSize;
    }


}
