package com.ggox.leetcode.algorithm;

/**
 * @Author: ggox
 * @Date: 2020/10/26 21:33
 * @Description: 插入排序
 * @Tips: 通常情况插入排序比冒泡排序好，因为交换位置时插入排序需要的赋值操作更少
 */
public class InsertionSort {

	// 插入排序，a表示数组，n表示数组大小
	public static void insertionSort(int[] a, int n) {
		if (n <= 1) {
			return;
		}
		for (int i = 1; i < n; ++i) {
			int value = a[i];
			int j = i - 1;
			// 查找插入的位置
			for (; j >= 0; --j) {
				if (a[j] > value) {
					a[j + 1] = a[j];  // 数据移动
				} else {
					break;
				}
			}
			a[j + 1] = value; // 插入数据
		}
	}

}
