package com.zeen.zeendemo.answer_views.withexra;

/**
 * Created by Clark Chen. on 3/17/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public enum Stage {
    PS1("part1", 1), PS2("part2", 2), PS3("part3", 2), PS4("part4", 3);
    // 成员变量
    private String part;
    private int stage;
    // 构造方法
    private Stage(String part, int stage) {
        this.part = part;
        this.stage = stage;
    }
    // 普通方法
    public static int geStage(String part) {
        for (Stage c : Stage.values()) {
            if (c.getPart() == part) {
                return c.stage;
            }
        }
        return -1;
    }
    // get set 方法
    public String getPart() {
        return part;
    }
    public void setPart(String part) {
        this.part = part;
    }
    public int getStage() {
        return stage;
    }
    public void setStage(int stage) {
        this.stage = stage;
    }
}