package com.example.ymeng.tomorrowhelper.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.ymeng.tomorrowhelper.R;
import com.example.ymeng.tomorrowhelper.model.bean.HorizontalChatBean;

import java.util.List;

/**
 * Author:YMeng
 * Time:2018/10/31  14:41
 * This is HorizontalChartRecyclerAdapter
 */
public class HorizontalChartRecyclerAdapter extends  RecyclerView.Adapter<HorizontalChartRecyclerAdapter.ItemViewHolder> {
    private Context mContext;
    private List<HorizontalChatBean> mList;

    public HorizontalChartRecyclerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setmList(List<HorizontalChatBean> mList) {
        this.mList = mList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_horizontal_chat, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.mTextView.setText(mList.get(position).getName());
        holder.mSeekBar.setProgress(mList.get(position).getCount());
        holder.mPb_ProBar.setProgress(mList.get(position).getCount());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

class ItemViewHolder extends RecyclerView.ViewHolder {
    //初始化
    private TextView mTextView;
    private SeekBar mSeekBar;
    private ProgressBar mPb_ProBar;

    public ItemViewHolder(View itemView) {
        super(itemView);
        mTextView = (TextView) itemView.findViewById(R.id.tv_barName);
        mSeekBar = (SeekBar) itemView.findViewById(R.id.sb_seekBar);
        mPb_ProBar = (ProgressBar) itemView.findViewById(R.id.pb_ProBar);

    }
}
}
