package com.example.ymeng.tomorrowhelper.view.activity;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.BottomSheetDialog;
import android.support.design.widget.BottomSheetDialogFragment;
import android.view.View;

import com.example.ymeng.tomorrowhelper.R;


/**
 * BottomSheetDialogFragment
 */
public class FullSheetDialogFragment extends BottomSheetDialogFragment {
    private BottomSheetBehavior mBehavior;
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState)
    {
        BottomSheetDialog dialog = (BottomSheetDialog) super.onCreateDialog(savedInstanceState);
        View view = View.inflate(getContext(), R.layout.dialog_bottom_sheet, null);
        dialog.setContentView(view);
        mBehavior = BottomSheetBehavior.from((View) view.getParent());
        return dialog;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        mBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);//全屏展开
        // mBehavior.setState(BottomSheetBehavior.STATE_SETTLING);//视图从脱离手指自由滑动到最终停下的这一小段时间
    }

    public void doclick(View v)
    {
        mBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }
}
