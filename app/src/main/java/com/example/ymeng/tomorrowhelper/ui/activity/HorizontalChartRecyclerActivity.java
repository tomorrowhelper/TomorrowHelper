package com.example.ymeng.tomorrowhelper.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.model.bean.HorizontalChatBean;
import com.example.ymeng.tomorrowhelper.ui.activity.base.SimpleActivity;
import com.example.ymeng.tomorrowhelper.ui.adapter.HorizontalChartRecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:YMeng
 * Time:2018/10/31  14:38
 * This is HorizontalChartRecyclerActivity
 */
public class HorizontalChartRecyclerActivity extends SimpleActivity {
    private RecyclerView mRv_seekbar;
    private HorizontalChartRecyclerAdapter mAdapter;
    private List<HorizontalChatBean>  mList = new ArrayList();

    @Override
    protected int setLayout() {
        return R.layout.horizontal_chart_recycler_activity;
    }

    @Override
    protected void initViews() {
        mRv_seekbar = (RecyclerView) findViewById(R.id.rv_seekbar);
        mRv_seekbar.setLayoutManager(new LinearLayoutManager(this));


        HorizontalChatBean bean1 = new HorizontalChatBean();
        bean1.setCount(10);
        bean1.setName("张一");
        mList.add(bean1);

        HorizontalChatBean bean2 = new HorizontalChatBean();
        bean2.setCount(20);
        bean2.setName("张二");
        mList.add(bean2);

        HorizontalChatBean bean3 = new HorizontalChatBean();
        bean3.setCount(30);
        bean3.setName("张三");
        mList.add(bean3);

        HorizontalChatBean bean4 = new HorizontalChatBean();
        bean4.setCount(40);
        bean4.setName("张四");
        mList.add(bean4);

        HorizontalChatBean bean5 = new HorizontalChatBean();
        bean5.setCount(50);
        bean5.setName("张wu");
        mList.add(bean5);

        HorizontalChatBean bean6 = new HorizontalChatBean();
        bean6.setCount(10);
        bean6.setName("张一");
        mList.add(bean6);

        HorizontalChatBean bean7 = new HorizontalChatBean();
        bean7.setCount(20);
        bean7.setName("张二");
        mList.add(bean7);

        HorizontalChatBean bean8 = new HorizontalChatBean();
        bean8.setCount(30);
        bean8.setName("张三");
        mList.add(bean8);

        HorizontalChatBean bean9 = new HorizontalChatBean();
        bean9.setCount(40);
        bean9.setName("张四");
        mList.add(bean9);

        HorizontalChatBean bean10 = new HorizontalChatBean();
        bean10.setCount(50);
        bean10.setName("张wu");
        mList.add(bean10);
    }

    @Override
    protected void initDatas() {
        mAdapter = new HorizontalChartRecyclerAdapter(this);
        mAdapter.setmList(mList);
        mRv_seekbar.setAdapter(mAdapter);

    }
}
