package com.xu.viewmodeltest

import android.util.Log
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent

class MyObserver : LifecycleObserver {

    /**
     * 自定义监听类实现LifecycleObserver空接口，通过使用注解可以感知到Activity的生命周期
     * 在被感知的activity中使用lifecycle,addObserve()方法传入此对象即可
     * */
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    fun activityStart() {
        Log.d("MyObserver", "activityStart")
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    fun activityStop() {
        Log.d("MyObserver", "activityStop")
    }
}