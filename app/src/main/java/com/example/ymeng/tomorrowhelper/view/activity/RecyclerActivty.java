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



/*
--------------------
public class GridFragment extends Fragment implements View.OnClickListener{

    private RecyclerView mRecycleViewDrawable;
    private RecyclerView mRecycleViewColor;
    private LinearLayoutManager mManagerColor;
    private LinearLayoutManager mManagerDrawable;
    private List<String> mData;
    private Button mDrawable;
    private Button mColor;
    private MyRecycleViewAdapter mRecycleViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_grid_layout, container, false);
        mRecycleViewDrawable = (RecyclerView) view.findViewById(R.id.recycleview_drawable);
        mRecycleViewColor = (RecyclerView) view.findViewById(R.id.recycleview_color);

        mDrawable = (Button) view.findViewById(R.id.btn_drawable);
        mDrawable.setOnClickListener(this);
        mColor = (Button) view.findViewById(R.id.btn_color);
        mColor.setOnClickListener(this);

        //设置颜色分割线
        mManagerColor = new GridLayoutManager(getActivity(), 3);
        mRecycleViewColor.setLayoutManager(mManagerColor);
        mRecycleViewColor.addItemDecoration(new GridDivider(getActivity(), 20, this.getResources().getColor(R.color.colorAccent)));

        //设置图片分割线
        mManagerDrawable = new GridLayoutManager(getActivity(), 3);
        mRecycleViewDrawable.setLayoutManager(mManagerDrawable);
        Drawable drawable = ContextCompat.getDrawable(getActivity(), R.mipmap.divider);
        mRecycleViewDrawable.addItemDecoration(new GridDivider(getActivity(), 20, drawable));

        //初始化数据
        mData = new ArrayList<String>();
        initData(mData);
        mRecycleViewAdapter = new MyRecycleViewAdapter(getActivity(), R.layout.item_grid_recycleview, mData);

        mRecycleViewColor.setAdapter(mRecycleViewAdapter);
        mRecycleViewDrawable.setAdapter(mRecycleViewAdapter);

        return view;
    }

    private void initData(List<String> dataList) {
        for (int i = 0; i < 16; i++) {
            dataList.add("item" + i);
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id){
            case R.id.btn_drawable:
                mRecycleViewColor.setVisibility(View.INVISIBLE);
                mRecycleViewDrawable.setVisibility(View.VISIBLE);
                break;

            case R.id.btn_color:
                mRecycleViewColor.setVisibility(View.VISIBLE);
                mRecycleViewDrawable.setVisibility(View.INVISIBLE);
                break;
        }
    }
}*/
