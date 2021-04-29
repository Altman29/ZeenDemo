package com.zeen.zeendemo.bottomnav;

import android.os.Bundle;

import com.zeen.mvplibrary.bus.AlBus;
import com.zeen.mvplibrary.bus.BusEventSubscriber;
import com.zeen.mvplibrary.bus.MessageEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Copyright©  2021
 * 正岸健康
 * author: csy
 * created on: 4/29/21 4:36 PM
 * description:
 */
public class BaseActivity extends AppCompatActivity {
    private final EventReceiver mEventReceiver = new EventReceiver();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AlBus.Companion.get().addEventSubscriber(mEventReceiver);
    }

    /**
     * 处理事件
     */
    protected void onEventReceived(MessageEvent event) {
    }

    public class EventReceiver implements BusEventSubscriber {
        public void onEvent(MessageEvent event) {
            onEventReceived(event);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AlBus.Companion.get().removeSubscriber(mEventReceiver);
    }
}
