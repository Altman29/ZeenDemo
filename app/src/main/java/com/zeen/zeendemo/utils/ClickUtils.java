package com.zeen.zeendemo.utils;

/**
 * Created by csy. on 4/18/21.
 * e-mail:altman29@foxmail.com
 * Desc: 过滤重复点击
 */
public class ClickUtils {
    // 两次点击按钮之间的点击间隔不能少于2000毫秒
    private static final int MIN_CLICK_DELAY_TIME = 1000;
    private static long lastClickTime;

    public static boolean isFastClick() {
        boolean flag = true;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = false;
        }
        lastClickTime = curClickTime;
        return flag;
    }
}