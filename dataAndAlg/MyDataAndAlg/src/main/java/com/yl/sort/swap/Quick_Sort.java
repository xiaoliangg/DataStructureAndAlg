package com.yl.sort.swap;

import java.util.Arrays;

/**
 * 快速排序
 */
public class Quick_Sort {
    public static void main(String[] args) {
        int[] arr ={8,2,0,9,4,3,5,6,1};
        quick_sort(arr);
        System.out.println(Arrays.toString(arr));
    }
   static void quick_sort(int[] a) {
       if(a.length>0)
           quick_sort(a, 0 , a.length-1);
   }
   static void quick_sort(int[] a, int low, int high) {
       if(low > high)
           return;
       int i = low;
       int j = high;
       int key = a[low];
       while(i < j) {
           while(i < j && a[j] > key)
               j--;
           while(i < j && a[i] <= key)
               i++;
           if(i < j) {
               int p = a[i];
               a[i] = a[j];
               a[j] = p;
           }
       }
       int p = a[i];
       a[i] = a[low];
       a[low] = p;
       quick_sort(a,low,i - 1 );
       quick_sort(a,i + 1,high);
   }
}
