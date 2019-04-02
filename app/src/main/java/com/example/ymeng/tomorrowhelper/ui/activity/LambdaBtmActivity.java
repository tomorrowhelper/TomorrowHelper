package com.example.ymeng.tomorrowhelper.ui.activity;

import android.util.Log;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.ui.activity.base.SimpleActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:YMeng
 * Time:2019/3/5  11:15
 * This is LambdaBtmActivity
 */
public class LambdaBtmActivity extends SimpleActivity {
    List<Apple> mAppleList = new ArrayList<>();

    @Override
    protected int setLayout() {
        return R.layout.activity_lambda;
    }

    @Override
    protected void initViews() {
        Apple a1 = new Apple(1, 1, 10f, "中国");
        Apple a2 = new Apple(2, 2, 3f, "日本");
        Apple a3 = new Apple(3, 3, 6f, "俄罗斯");
        Apple a4 = new Apple(4, 4, 7f, "澳大利亚");
        mAppleList.add(a1);
        mAppleList.add(a2);
        mAppleList.add(a3);
        mAppleList.add(a4);
 /*  List<Apple> appleList = filterGreenApples(mAppleList, new AppleFilter() {
            @Override
            public boolean accept(Apple apple) {
                return apple.getColor()==1&&apple.getWeight()>6;
            }
        });*/
        List<Apple> appleList = filterGreenApples(mAppleList, (Apple apple) -> apple.getColor() == 1 && apple.getWeight() > 6);
        for (Apple apple : appleList) {
            Log.e("TAG", apple.toString());
        }
    }

    @Override
    protected void initDatas() {

    }

    public static List<Apple> filterGreenApples(List<Apple> apples, AppleFilter filter) {
        List<Apple> filterApple = new ArrayList<>();
        for (Apple apple : apples) {
            if (filter.accept(apple)) {
                filterApple.add(apple);
            }
        }
        return filterApple;
    }

    public interface AppleFilter {
        boolean accept(Apple apple);
    }

    public class Apple {
        private int id;
        private int color;
        private Float weight;
        private String origin;

        public Apple(int id, int color, Float weight, String origin) {
            this.id = id;
            this.color = color;
            this.weight = weight;
            this.origin = origin;
        }

        public int getId() {
            return id;
        }

        public int getColor() {
            return color;
        }

        public Float getWeight() {
            return weight;
        }

        public String getOrigin() {
            return origin;
        }

        @Override
        public String toString() {
            return "Apple{" +
                    "id=" + id +
                    ", color=" + color +
                    ", weight=" + weight +
                    ", origin='" + origin + '\'' +
                    '}';
        }
    }
}
