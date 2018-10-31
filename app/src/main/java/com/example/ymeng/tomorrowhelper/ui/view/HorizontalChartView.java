package com.example.ymeng.tomorrowhelper.ui.view;

/**
 * Author:YMeng
 * Time:2018/10/30  10:45
 * This is HorizontalChartView
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.ymeng.tomorrowhelper.util.SizeUtils;

/**横向柱状统计图
 * Created by Administrator on 2018/1/16 0016.
 */
public class HorizontalChartView extends View {
    private final static String TAG = "TAG";
    /**
     * 间隔线画笔
     */
    private Paint paint;

    /**
     * 线的颜色
     */
   // private int color_line = Color.rgb(230, 230, 230);
    private int color_line = Color.rgb(230, 230, 230);

    /**
     * 字的颜色
     */
    private int color_font = Color.rgb(51, 51, 51);

    /**
     * 比例图颜色
     */
    private int color_plan = Color.rgb(22, 85, 164);

    /**
     * 比例图画笔
     */
    private Paint paint_plan;

    /**
     * 比例图高度
     */
    private int plan_height;

    /**
     * 初始化比例
     */
    private Float[] ratio = {0f, 0f, 0f, 0f, 0f};

    /**
     * 最大数为几
     */
    private int count = 5;

    /**
     * 文字画笔1
     */
    private Paint paint_font;

    /**
     * 文字画笔2
     */
    private Paint paint_font2;

    /**
     * 线的条数
     */
    private int line_num = 5;

    /**
     * 比例数
     */
    private String ratio_num = "0";

    /**
     * 月份
     */
    private String month_num = "1月";


    public HorizontalChartView(Context context) {
        super(context);
    }

    public HorizontalChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public HorizontalChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public HorizontalChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    public void init(Context context, AttributeSet attrs) {

        paint = new Paint();
        paint.setColor(Color.BLACK);

        //ff9200
        paint_plan = new Paint();
        paint_plan.setColor(Color.parseColor("#ff9200"));

        paint_font = new Paint();
        paint_font.setColor(color_font);
        paint_font.setTextSize(SizeUtils.dp2px( 12));
        paint_font.setAntiAlias(true);
        paint_font.setTextAlign(Paint.Align.CENTER);

        paint_font2 = new Paint();
        paint_font2.setColor(color_font);
        paint_font2.setTextSize(SizeUtils.dp2px( 12));
        paint_font2.setAntiAlias(true);
        paint_font2.setTextAlign(Paint.Align.RIGHT);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        int lin_width = (int) (width * 0.95);
        int lin_width_Two = (int) (width * 0.90);
        //获取底部文字信息
        Paint.FontMetrics fm = paint_font.getFontMetrics();

       // int line_length = (int) (height - (fm.bottom - fm.top) - 4);
        int line_length = (int) (height - (fm.bottom - fm.top) - 20);

        Log.e(TAG, height+"----"+(fm.bottom - fm.top)+"----"+fm.bottom+"----"+fm.top);

        plan_height = (int) (line_length / 12 * 0.3);

        for (int i = 0; i <= count; i++) {
            canvas.save();

            if (i == 0) {
                ratio_num = "0";
            } else {
                ratio_num = i + "";
            }
            //底部百分比数字
            canvas.drawText(ratio_num, (lin_width / count*i)+10, height - 5, paint_font);

            //        (168)
            // 162+0*842/5=162
            //网络线
           canvas.drawLine((lin_width / count*i)+10, 0, (lin_width / count*i)+10, line_length+20, paint);
            /**
             *      //网络线
             canvas.drawLine(lift_width + i * line_width / 10, 0, lift_width + i * line_width / 10, line_length, paint);
             */
           //画最下面的横线
           if(i==count){
               canvas.drawLine(10, line_length+20, (lin_width / count*i)+10, line_length+20, paint);
           }
            canvas.restore();
        }
        //获取月份文字信息
        Paint.FontMetrics fm1 = paint_font2.getFontMetrics();
        for (int n = 0; n <ratio.length; n++) {
            canvas.save();
            //比例图
            canvas.drawRect(10,
                    (line_length / ratio.length * (n) - (line_length / 24 + plan_height / 2) + fm1.bottom)+40,
                    lin_width * (ratio[n] / count)+10 ,
                    (line_length / ratio.length * (n) - (line_length / 24 + plan_height / 2) + plan_height + fm1.bottom)+40, paint_plan);

            /**
             *      canvas.drawRect(lift_width,
             *      line_length / 12 * (13 - n) - (line_length / 24 + plan_height / 2) + fm1.bottom,
                    line_width * (ratio[n - 1] / 100) + lift_width,
                    line_length / 12 * (13 - n) - (line_length / 24 + plan_height / 2) + plan_height + fm1.bottom,
             paint_plan);
             */
            Log.e(TAG, line_length+"----"+plan_height+"----"+fm1.bottom);
        /*
            canvas.drawRect(10, (line_length / 5 * (n) - (line_length / 24 + plan_height / 2) + fm1.bottom)+40,
                    lin_width * (ratio[n] / 5)+10 ,
                    (line_length / 5 * (n) - (line_length / 24 + plan_height / 2) + plan_height + fm1.bottom)+40, paint_plan);*/
            canvas.restore();
        }
    }

    /**
     * 传入比例信息
     *
     * @param ratio
     */
    public void setRatio(Float[] ratio) {
        this.ratio = ratio;
    }
    /**
     * 传入比例信息
     *
     */
    public void setCount(int count) {
        this.count = count;
    }

}
