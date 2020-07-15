package com.yl.javaUtil;

import com.sun.deploy.util.ArrayUtil;

/**
 * 测试i++和++i的区别。
 * strArr[i++]，赋的值是进行i++之前的值
 * strArr[++j]，赋的值是进行++j之后的值
 * Java中i++和++i的区别:https://blog.csdn.net/qq_34471736/article/details/54599901
 */

public class PlusPlusTest {
    public static void main(String[] args) {
        String[] strArr = {"11","22","33"};
        int i =0;
        strArr[i++] = "100";
        System.out.println(ArrayUtil.arrayToString(strArr));

        String[] strArr2 = {"11","22","33"};
        int j =0;
        strArr2[++j] = "100";
        System.out.println(ArrayUtil.arrayToString(strArr2));
    }
}
