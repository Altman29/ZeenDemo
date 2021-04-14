package com.example.javalib.convertList;

public class Item {

    @Override
    public String toString() {
        return "Item{" +
                "month=" + month +
                ", desc='" + desc + '\'' +
                '}';
    }

    private Integer month;

    private String desc;


    public Item(Integer month, String desc) {
        this.month = month;
        this.desc = desc;
    }

    public Item(Integer month) {
        this.month = month;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

