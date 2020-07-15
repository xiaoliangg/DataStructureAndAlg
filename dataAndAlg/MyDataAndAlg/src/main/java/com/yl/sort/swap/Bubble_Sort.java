package com.yl.sort.swap;

import java.util.Arrays;

/**
 * 冒泡
 */
public class Bubble_Sort {

    public static void main(String[] args) {
        int[] arr ={8,2,0,9,4,3,5,6,1};
        int[] result = Bubble_Sort(arr);
        System.out.println(Arrays.toString(result));
    }
    static int[] Bubble_Sort(int[] arr) {

        int len  = arr.length;
        boolean flag = false;
        for(int i = 0;i < len;i++) {
            flag = false;
            for(int j = 0;j < len - 1 - i;j++) {

                if(arr[j] > arr[j + 1]) {
                    int tmp = arr[j + 1];
                    arr[j + 1] = arr[j];
                    arr[j] = tmp;
                    flag = true;
                }
            }
            if(!flag){ //上一个循环比较结束而没有发生交换，但是每两个相邻元素都比较过了，说明已经有序
                break;
            }
        }

        return arr;

    }
}
