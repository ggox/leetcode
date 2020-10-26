package com.ggox.leetcode.algorithm;

import java.util.Arrays;

/**
 * @Author: ggox
 * @Date: 2020/10/26 20:44
 * @Description: 快速排序
 */
public class QuickSort {

	// 快速排序，a是数组，n表示数组的大小
	public static void quickSort(int[] a, int n) {
		quickSortInternally(a, 0, n - 1);
	}

	// 快速排序递归函数，p,r为下标
	private static void quickSortInternally(int[] a, int p, int r) {
		if (p >= r)
			return;

		int q = partition(a, p, r); // 获取分区点
		quickSortInternally(a, p, q - 1);
		quickSortInternally(a, q + 1, r);
	}

	private static int partition(int[] a, int p, int r) {
		int pivot = a[r];
		int i = p;
		for (int j = p; j < r; ++j) {
			if (a[j] < pivot) {
				if (i != j) {
					int tmp = a[i];
					a[i++] = a[j];
					a[j] = tmp;
				}
				i++;
			}
		}

		int tmp = a[i];
		a[i] = a[r];
		a[r] = tmp;

		System.out.println("i=" + i);
		return i;
	}

	private static int partition2(int[] a, int p, int r) {
		int pivot = a[p];
		// [p,i) 小于 pivot， [i,r] 大于等于 pivot
		int i = p + 1;
		for (int j = i; j <= r; j++) {
			// 小于 pivot，将 a[j] 移动到已处理区,方式 直接交换 i 和 j 的元素
			if (a[j] < pivot) {
				// 如果 j=i 不需要交换
				if (i != j) {
					int tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
				}
				i++;
			}
		}

		int tmp = a[i - 1];
		a[i - 1] = pivot;
		a[p] = tmp;
		return i - 1;
	}

	public static void main(String[] args) {
		int[] arr = new int[] { 3, 2, 1, 5, 87, 12, 32, 41, 23, 63, 12 };
		quickSort(arr, arr.length);
		System.out.println(Arrays.toString(arr));
	}

}
