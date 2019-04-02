package com.example.ymeng.tomorrowhelper.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.model.bean.DetailsBean;

import java.util.List;

/**
 * Author:YMeng
 * Time:2018/6/21  13:48
 * This is RecyclerAdapter
 */
public class DetailsAdapter extends BaseQuickAdapter<DetailsBean.DataBean,BaseViewHolder> {
    onClickListoner mListoner;
    public DetailsAdapter(int layoutResId, @Nullable List data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(final BaseViewHolder helper, DetailsBean.DataBean item) {
       helper.setText(R.id.tv_name,item.getStyle().getGoods_name());
       helper.setText(R.id.tv_id,item.getStyle().getStyleid());

       helper.setOnClickListener(R.id.tv_name,new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if(mListoner!=null){
                   mListoner.onClick(helper.getLayoutPosition());
               }

           }
       });
    }

    public interface onClickListoner{
        void onClick(int position);
    }

    public void setonClickListoner(onClickListoner listoner){
        this.mListoner = listoner;
    }
}
