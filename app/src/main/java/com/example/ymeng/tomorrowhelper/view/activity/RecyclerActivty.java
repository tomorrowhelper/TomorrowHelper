package com.example.ymeng.tomorrowhelper.view.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import com.cjj.MaterialRefreshLayout;
import com.cjj.MaterialRefreshListener;
import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.view.activity.base.SimpleActivity;
import com.example.ymeng.tomorrowhelper.view.adapter.RecyclerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author:YMeng
 * Time:2018/6/21  13:29
 * This is RecyclerActivty
 */
public class RecyclerActivty extends SimpleActivity {
    private MaterialRefreshLayout mMaterialRefreshLayout;
    private RecyclerView mRecycler;
    private List<String> mList;

    @Override
    protected int setLayout() {
        return R.layout.recycler_activity;
    }

    @Override
    protected void initViews() {
        mMaterialRefreshLayout = findViewById(R.id.refresh_recycler);
        View view = LayoutInflater.from(this).inflate(R.layout.include_drawer_header,null);
        mMaterialRefreshLayout.setHeaderView(view);

        mRecycler = findViewById(R.id.Recycler);
        mList = new ArrayList<>();
        for (int i = 0; i < 15; i++) {
            mList.add("iten " + i);
        }


    }

    @Override
    protected void initDatas() {
        //   mRecycler.setLayoutManager(new GridLayoutManager(this,3));
        mRecycler.setLayoutManager(new LinearLayoutManager(this));
        //设置颜色分割线
        //  mRecycler.addItemDecoration(new GridDivider(this, 5, this.getResources().getColor(R.color.colorAccent)));

        RecyclerAdapter mAdapter = new RecyclerAdapter(R.layout.recycler_item, mList);
        mRecycler.setAdapter(mAdapter);


        //刷新加载
        mMaterialRefreshLayout.setMaterialRefreshListener(new MaterialRefreshListener() {
            @Override
            public void onRefresh(final MaterialRefreshLayout materialRefreshLayout) {
                //refreshing...
                Toast.makeText(mActivity, "onRefresh", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onRefreshLoadMore(MaterialRefreshLayout materialRefreshLayout) {
                //load more refreshing...
                Toast.makeText(mActivity, "onRefreshLoadMore", Toast.LENGTH_SHORT).show();
            }
        });

        // refresh complete
       // mMaterialRefreshLayout.finishRefresh();

    }
}