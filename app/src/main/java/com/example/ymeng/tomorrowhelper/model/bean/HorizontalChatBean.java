package com.example.ymeng.tomorrowhelper.model.bean;

import java.io.Serializable;

/**
 * Author:YMeng
 * Time:2018/10/31  14:47
 * This is HorizontalChatBean
 * 横向柱状图Bean
 */
public class HorizontalChatBean implements Serializable {
    private String name;
    private int count;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
