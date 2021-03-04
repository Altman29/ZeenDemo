package com.zeen.zeendemo.checkdata;

/**
 * Created by Clark Chen. on 3/4/21.
 * e-mail:altman29@foxmail.com
 * Desc:
 */
public class PrettyGirl {
    /**
     * 芳龄几何
     */
    int age;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static void main(String[] args) {

        // 引用类型
        PrettyGirl prettyGirl = new PrettyGirl();
        prettyGirl.setAge(28);
        // 基本类型
        int num = 50;
        // 数组arrs也引用类型
        int[] arrs = new int[]{2,0,1,9};

        System.out.println("mian 中 num  = " + num);
        System.out.println("mian 中 arrs[3] = " + arrs[3]);
        System.out.println("mian 中 prettyGirl.getAge() = " + prettyGirl.getAge());
        System.out.println("-----------------------------------------");

        // 调用 change方法
        prettyGirl.change(num, arrs, prettyGirl);

        System.out.println("调用change 后 mian 中 num  = " + num);
        System.out.println("调用change 后 mian 中 arrs[3] = " + arrs[3]);
        System.out.println("调用change 后 mian 中 prettyGirl.getAge() = " + prettyGirl.getAge());

    }

    public void change(int pnum, int[] parrs, PrettyGirl ppg) {
        //在change中 改变值类型pnum的值
        pnum = pnum + 50;
        //在change中 改变引用类型 parrs,ppg 的值
        parrs[3] = 8;
        // 从28变18
        ppg.setAge(18);

        System.out.println("change 中 pnum  = " + pnum);
        System.out.println("change 中 parrs[3] = " + parrs[3]);
        System.out.println("change 中 ppg.getAge() = " + ppg.getAge());
        System.out.println("-----------------------------------------");
    }

}
