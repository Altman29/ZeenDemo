package com.zeen.zeendemo;

import android.app.Application;

import com.zeen.mvplibrary.bus.AlBus;

/**
 * Copyright©  2021
 * 正岸健康
 * author: csy
 * created on: 4/29/21 4:39 PM
 * description:
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        AlBus.Companion.get().registe();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        // 全局事件取消订阅
        AlBus.Companion.get().unregiste();
    }
}
