package com.zeen.zeendemo.trace;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clark C. on 2/26/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class TestListChange {
    public static void main(String[] args) {
        List<String> timeStamp = new ArrayList<>();
        timeStamp.add("11月");
        timeStamp.add("11月");
        timeStamp.add("11月");
        timeStamp.add("10月");
        timeStamp.add("10月");
        timeStamp.add("10月");
        timeStamp.add("10月");
        timeStamp.add("9月");
        timeStamp.add("9月");
        timeStamp.add("8月");
        timeStamp.add("7月");
        timeStamp.add("7月");
        timeStamp.add("6月");
        timeStamp.add("6月");
        timeStamp.add("5月");

        for (String it : timeStamp)
            System.out.println(it);


    }
}
