package com.zeen.mvplibrary.bus

import android.util.Log
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe

import org.greenrobot.eventbus.ThreadMode

class AlBus  private constructor() {
    private var mBusEventSubscribers: MutableList<BusEventSubscriber<MessageEvent<Any>?>> = ArrayList()

    fun registe() {
        if (!EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().register(this)
        }
    }

    fun unregiste() {
        if (EventBus.getDefault().isRegistered(this)) {
            EventBus.getDefault().unregister(this)
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    fun onEvent(event: MessageEvent<Any>?) {
        synchronized(mBusEventSubscribers) {
            for (subscriber in mBusEventSubscribers) {
                // 捕获异常避免其他类无法接收通知
                try {
                    subscriber.onEvent(event)
                } catch (e: Exception) {
                    Log.e("event", "onEvent: " + e.message )
                }
            }
        }
    }

    fun register(obj: Any?) {
        EventBus.getDefault().register(obj)
    }

    /**
     * 订阅事件
     */
    fun addEventSubscriber(subscriber: BusEventSubscriber<MessageEvent<Any>?>) {
        synchronized(mBusEventSubscribers) {
            if (!mBusEventSubscribers.contains(subscriber)) {
                mBusEventSubscribers.add(subscriber)
            }
        }
    }

    /**
     * 取消订阅
     */
    fun removeSubscriber(subscriber: BusEventSubscriber<*>?) {
        synchronized(mBusEventSubscribers) { mBusEventSubscribers.remove(subscriber) }
    }

    fun isRegistered(obj: Any?): Boolean {
        return EventBus.getDefault().isRegistered(obj)
    }

    fun unregister(obj: Any?) {
        EventBus.getDefault().unregister(obj)
    }

    fun post(event: Any?) {
        EventBus.getDefault().post(event)
    }

    companion object {
        @Volatile
        var defaultInstance: AlBus? = null
        fun get(): AlBus? {
            if (defaultInstance == null) {
                synchronized(AlBus::class.java) {
                    if (defaultInstance == null) {
                        defaultInstance = AlBus()
                    }
                }
            }
            return defaultInstance
        }
    }

}