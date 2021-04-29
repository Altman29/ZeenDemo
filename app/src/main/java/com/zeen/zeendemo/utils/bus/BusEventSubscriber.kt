package com.zeen.mvplibrary.bus

interface  BusEventSubscriber<T : MessageEvent<Any>?>  {
    fun onEvent(event : T)
}