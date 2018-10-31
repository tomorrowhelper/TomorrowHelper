package com.example.ymeng.tomorrowhelper.ui.fragment.base;

import android.app.Fragment;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v4.content.res.ResourcesCompat;

import com.example.ymeng.tomorrowhelper.presenter.base.BasePresenter;
import com.example.ymeng.tomorrowhelper.ui.activity.base.BaseActivity;

/**
 * Author:YMeng
 * Time:2018/6/7  11:21
 * This is BaseFragment
 *
 * 继承后该类后，不需要再绑定ButterKnife。当fragment可见时才会进行初始化工作
 * 实现setContentLayout来设置并返回布局ID，
 * 实现initView来做视图相关的初始化，
 * 实现obtainData来做数据的初始化
 * 实现initEvent来做事件监听的初始化
 */
public class BaseFragment<T extends BasePresenter> extends Fragment implements IBaseFragment {
    private static final String SAVED_STATE = "saved_state_BaseFragment";
    protected BaseActivity mActivity;
    //加载布局，可用于设置各种加载状态，也是根布局视图
   // private LoadLayout mLoadLayout;








    @Override
    public int getResourceColor(int colorId, Resources.Theme theme) {
        return isAdded() ? ResourcesCompat.getColor(getResources(), colorId, null) : 0;
    }

    @Override
    public String getResourceString(int stringId) {
        return isAdded() ? getResources().getString(stringId) : null;
    }

    @Override
    public Drawable getResourceDrawable(int id) {
        return isAdded() ? ResourcesCompat.getDrawable(getResources(), id, null) : null;
    }

}
