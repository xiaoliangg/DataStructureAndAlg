package com.yl.sort.merge;

import java.util.Arrays;

/**
 * 归并排序-2路归并
 */
public class Merge_Sort {
    public static void main(String[] args) {
        int[] arr ={8,2,0,9,4,3,5,6,1};
        merge_sort(arr,0,arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    static void merge_sort(int[] seq, int left, int right) {
        if (left < right) {
            int middle = (left + right) / 2;
            merge_sort(seq, left, middle);
            merge_sort(seq, middle + 1, right);
            merge(seq, left, right);
        }
    }
    static void merge(int[] seq, int l, int r) {
        int mid = (l + r) / 2;
        int i = l;
        int j = mid + 1;
        int count = 0;
        int temp[] = new int[r - l + 1];
        while (i <= mid && j <= r) {
            if (seq[i] < seq[j])
                temp[count++] = seq[i++];
            else
                temp[count++] = seq[j++];
        }
        while (i <= mid)
            temp[count++] = seq[i++];
        while (j <= r)
            temp[count++] = seq[j++];
        count = 0;
        while (l <= r)
            seq[l++] = temp[count++];
    }
}
