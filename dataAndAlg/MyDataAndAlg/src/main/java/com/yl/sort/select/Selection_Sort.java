package com.yl.sort.select;

import java.util.Arrays;

public class Selection_Sort {
    public static void main(String[] args) {
        int[] arr ={8,2,0,9,4,3,5,6,1};
        Selection_Sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static int[] Selection_Sort(int[] arr) {

        int len  = arr.length;

        int minIdx,tmp;

        for(int i = 0;i < len - 1;i++) {

            minIdx = i;

            for(int j = i + 1;j < len;j++) {

                if(arr[j] < arr[minIdx])

                    minIdx = j;

            }
            tmp = arr[i];
            arr[i] = arr[minIdx];
            arr[minIdx] = tmp;
        }

        return arr;

    }
}
