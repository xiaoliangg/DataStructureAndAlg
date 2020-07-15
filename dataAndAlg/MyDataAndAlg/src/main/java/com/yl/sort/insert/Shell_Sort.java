package com.yl.sort.insert;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class Shell_Sort {
    public static void main(String[] args) {
        int[] arr ={8,2,0,9,4,3,5,6,1};
        Shell_Sort(arr);
        System.out.println(Arrays.toString(arr));
    }
    static int[] Shell_Sort(int[] arr) {

        int len  = arr.length;

        int i,j,gap;

        for(gap = len/2; gap > 0; gap /= 2) {
            for(i = gap; i < len; i++) {
                int num = arr[i];
                for(j = i-gap; j>=0 && arr[j]>num; j-=gap)
                    arr[j+gap] = arr[j];
                arr[j+gap] = num;
            }
        }
        return arr;
    }
}
