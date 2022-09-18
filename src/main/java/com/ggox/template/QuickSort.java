package com.ggox.template;

import java.util.Arrays;

/**
 * 快速排序算法模板
 *
 * @author: ggox
 * @date: 2022/9/19 00:14
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] q = {3, 12, 2, 1, 2, 5, 8, 2, 3, 9, 3, 4, 7, 5, 3, 123, 3, 4, 6};
        quickSort(q, 0, q.length - 1);
        System.out.println(Arrays.toString(q));
    }

    static void quickSort(int[] q, int l, int r) {
        if (l >= r) {
            return;
        }
        int i = l - 1, j = r + 1, x = q[l + r >> 1];
        while (i < j) {
            do {
                i++;
            } while (q[i] < x);
            do {
                j--;
            } while (q[j] > x);
            if (i < j) {
                swap(q, i, j);
            }
        }
        quickSort(q, l, j);
        quickSort(q, j + 1, r);
    }

    static void swap(int[] q, int i, int j) {
        int t = q[i];
        q[i] = q[j];
        q[j] = t;
    }
}
