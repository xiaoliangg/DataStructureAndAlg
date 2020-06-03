package com.yl.sort.insert;

import java.util.Arrays;

/**
 * 插入排序
 */
public class Insert_Sort {
    public static void main(String[] args) {
        int[] arr ={8,2,0,9,4,3,5,6,1};
        Insert_Sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static int[] Insert_Sort(int[] arr) {

        int len  = arr.length;

        int preIdx,current;

        for(int i = 1;i < len;i++) {

            preIdx = i - 1;

            current = arr[i];

            while(preIdx >= 0 && arr[preIdx] > current) {

                arr[preIdx + 1] = arr[preIdx];

                preIdx--;

            }
            arr[preIdx + 1] = current;
        }

        return arr;

    }
}
