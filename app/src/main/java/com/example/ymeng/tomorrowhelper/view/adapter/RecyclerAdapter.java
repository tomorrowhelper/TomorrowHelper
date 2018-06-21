package com.example.ymeng.tomorrowhelper.view.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ymeng.tomorrowhelper.R;

import java.util.List;

/**
 * Author:YMeng
 * Time:2018/6/21  13:48
 * This is RecyclerAdapter
 */
public class RecyclerAdapter extends BaseQuickAdapter<String,BaseViewHolder> {

    public RecyclerAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, String item) {
      // helper.setText(R.id.RecyclerItemText1,item.get(helper.getLayoutPosition()));
       helper.setText(R.id.RecyclerItemText1,mData.get(helper.getLayoutPosition()));
    }
}
