package com.zeen.zeendemo.utils.bus

import android.app.Activity
import android.app.ActivityManager
import android.content.Context
import android.util.Log
import java.util.*
import kotlin.system.exitProcess


/**
 * Copyright (c) 2020
 * author: whs
 * created on: 2020/5/26 14:03
 * description:
 */
class AppManager {
    private var context: Context? = null

    private constructor(context: Context) {
        this.context = context
    }

    private constructor()

    /**
     * 获取当前Activity（栈顶Activity） 没有找到则返回null
     */
    fun findActivity(cls: Class<*>): Activity? {
        var activity: Activity? = null
        for (aty in activityStack!!) {
            if (aty != null){
                if (aty.javaClass == cls) {
                    activity = aty
                    break
                }
            }

        }
        return activity
    }

    /**
     * 获取当前Activity栈中元素个数
     */
    val count: Int
        get() = activityStack!!.size

    /**
     * 添加Activity到堆栈
     */
    fun addActivity(activity: Activity) {
        Log.d("activity", activity.toString())
        if (activityStack == null) {
            activityStack = Stack()
        }
        activityStack!!.add(activity)
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    fun currentActivity(): Activity? {
        return activityStack!!.lastElement()
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    fun finishActivity() {
        val activity = activityStack!!.lastElement()
        finishActivity(activity)
    }

    /**
     * 移除最后一个Activity
     */
    fun removeActivity(activity: Activity?) {
        if (activity != null) {
            activityStack!!.remove(activity)
        }
    }

    /**
     * 结束指定的Activity
     */
    fun finishActivity(activity: Activity?) {
        var activity = activity
        if (activity != null) {
            activityStack!!.remove(activity)
            activity.finish()
            activity = null
        }
    }

    /**
     * 结束指定类名的Activity
     */
    fun finishActivity(cls: Class<*>) {
        for (activity in activityStack!!) {
            if (activity != null){
                if (activity.javaClass == cls) {
                    finishActivity(activity)
                }
            }
        }
    }

    /**
     * 结束所有Activity
     */
    fun finishAllActivity() {
        var i = 0
        val size = activityStack!!.size
        while (i < size) {
            if (null != activityStack!![i]) {
                activityStack!![i]!!.finish()
            }
            i++
        }
        activityStack!!.clear()
    }

    /**
     * 退出应用程序
     */
    fun appExit(context: Context) {
        try {
            finishAllActivity()
            val activityMgr = context.getSystemService(Context.ACTIVITY_SERVICE) as ActivityManager
            activityMgr.restartPackage(context.packageName)
            exitProcess(0)
        } catch (e: Exception) {
        }
    }

    companion object {
        private var activityStack: Stack<Activity?>? = null
        private var instance: AppManager? = null

        /**
         * 单一实例
         */
        fun getAppManager(context: Context): AppManager? {
            if (instance == null) {
                instance = AppManager(context)
            }
            return instance
        }

        /**
         * 单一实例
         */
        val appManager: AppManager?
            get() {
                if (instance == null) {
                    instance = AppManager()
                }
                return instance
            }

        /**
         * 获取当前Activity（栈顶Activity）
         */
        fun topActivity(): Activity? {
            if (activityStack == null) {
                throw NullPointerException(
                    "Activity stack is Null,your Activity must extend KJActivity"
                )
            }
            return if (activityStack!!.isEmpty()) {
                null
            } else activityStack!!.lastElement()
        }
    }
}
