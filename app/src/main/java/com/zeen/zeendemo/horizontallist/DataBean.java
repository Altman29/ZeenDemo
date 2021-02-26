package com.zeen.zeendemo.horizontallist;

/**
 * Created by Csy on 2/24/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class DataBean {
    private int resId;
    private String text1;
    private String text2;
    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public DataBean() {
    }

    public DataBean(int resId, String text1, String text2, int color) {
        this.resId = resId;
        this.text1 = text1;
        this.text2 = text2;
        this.color = color;
    }

    public String getText1() {
        return text1;
    }

    public void setText1(String text1) {
        this.text1 = text1;
    }

    public String getText2() {
        return text2;
    }

    public void setText2(String text2) {
        this.text2 = text2;
    }

    public int getResId() {
        return resId;
    }

    public void setResId(int resId) {
        this.resId = resId;
    }
}
