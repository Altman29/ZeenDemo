package com.example.javalib.convertList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class Test {

    public static void main(String[] args) {

        List<Item> itemList = new ArrayList<>();

        itemList.add(new Item(14, "14-11 desc"));
        itemList.add(new Item(14, "14-10 desc"));
        itemList.add(new Item(14, "14-9 desc"));
        itemList.add(new Item(13, "13-11 desc"));
        itemList.add(new Item(12, "12-11 desc"));
        itemList.add(new Item(12, "12-8 desc"));
        itemList.add(new Item(12, "12-7 desc"));
        itemList.add(new Item(12, "12-6 desc"));
        itemList.add(new Item(11, "11-6 desc"));
        itemList.add(new Item(10, "10-11 desc"));
        itemList.add(new Item(10, "10-2 desc"));
        itemList.add(new Item(10, "10-1 desc"));

        System.out.println(itemList.size());


        Callable<Object> callable = new Callable<Object>() {
            public Object call() throws Exception {
                return null;

            }
        };

        if (callable != null) {

        }

        if (callable instanceof Object) {
            Object o = (Object) callable;

        }


        new ArrayList<>();


        for (Item item : itemList) {
            System.out.println(item.toString());
        }

        System.out.println("==========================================");

        List<Item> result = convertItem(itemList);
        System.out.println(result.size());
        for (Item item : result) {
            System.out.println(item.toString());
        }



    }

    public static List<Item> convertItem(List<Item> itemList) {
        List<Item> result = new ArrayList<>();

        int tempA = 0, tempB = -1;
        for (Item item : itemList) {
            tempA = item.getMonth();
            if (tempA != tempB) {
                result.add(new Item(tempA));
            }
            result.add(item);
            tempB = tempA;
        }
        return result;
    }
}
