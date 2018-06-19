package com.example.ymeng.tomorrowhelper.presenter.base;

import com.example.ymeng.tomorrowhelper.model.net.LifeCycleEvent;

import io.reactivex.subjects.PublishSubject;

/**
 * Author:YMeng
 * Time:2018/6/7  11:14
 * This is IBaseView
 * 里面定义一些View层总是需要实现的抽象方法
 */
public interface IBaseView {
    //用于获取view层的lifecycleSubject来控制网络请求生命周期
    //Activity和Fragment的基类实现该方法，这样就不用每次都将lifecycleSubject作为参数传到Presenter层。
    PublishSubject<LifeCycleEvent> getLifeSubject();
}
