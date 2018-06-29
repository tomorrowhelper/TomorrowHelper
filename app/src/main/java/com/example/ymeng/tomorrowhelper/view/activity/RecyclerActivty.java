package com.example.ymeng.tomorrowhelper.view.activity;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.view.activity.base.SimpleActivity;
import com.example.ymeng.tomorrowhelper.view.adapter.GridDivider;
import com.example.ymeng.tomorrowhelper.view.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:YMeng
 * Time:2018/6/21  13:29
 * This is RecyclerActivty
 */
public class RecyclerActivty extends SimpleActivity {
    private RecyclerView mRecycler;
    private List<String> mList;

    @Override
    protected int setLayout() {
        return R.layout.recycler_activity;
    }

    @Override
    protected void initViews() {
        mList = new ArrayList<>();
        for(int i = 0;i<50;i++){
            mList.add("iten "+i);
        }
        mRecycler = findViewById(R.id.Recycler);

    }

    @Override
    protected void initDatas() {
     //   mRecycler.setLayoutManager(new GridLayoutManager(this,3));
        mRecycler.setLayoutManager(new GridLayoutManager(this,4));
        //设置颜色分割线
        mRecycler.addItemDecoration(new GridDivider(this, 5, this.getResources().getColor(R.color.colorAccent)));

        RecyclerAdapter mAdapter = new RecyclerAdapter(R.layout.recycler_item,mList);
        mRecycler.setAdapter(mAdapter);
    }
}