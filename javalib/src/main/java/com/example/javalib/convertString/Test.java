package com.example.javalib.convertString;

/**
 * Created by Clark Chen. on 4/18/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class Test {

    public static void main(String[] args) {
        String source = "[\\\"斗志昂扬\\\",\\\"精神焕发\\\",\\\"如释重负\\\",\\\"充实\\\",\\\"惊喜\\\",\\\"满足\\\",\\\"开心\\\",\\\"幸福\\\",\\\"感恩\\\"]";
        String s1 = source.replaceAll("\\\\|\\\"|\\[|\\]", "");//去除特殊符号
        String[] split = s1.split(",");//取以","分隔数组
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < split.length; i++) {//去前3
            if (i > 2) result.append("");
            else {
                if (i == 2) result.append(split[i]);
                else result.append(split[i] + ",");
            }
        }
        System.out.println(result.toString());


    }
}
