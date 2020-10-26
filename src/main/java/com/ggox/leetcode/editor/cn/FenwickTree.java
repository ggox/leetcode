package com.ggox.leetcode.editor.cn;

/**
 * @Author: ggox
 * @Date: 2020/7/12 00:35
 */
public class FenwickTree {

	private int[] tree;
	private int len;

	public FenwickTree(int n) {
		this.len = n;
		this.tree = new int[n + 1];
	}

	public FenwickTree(int[] nums) {
		this.len = nums.length;
		this.tree = new int[this.len + 1];
		for (int i = 0; i < this.len; i++) {
			this.update(i, nums[i]);
		}
	}

	/**
	 * 在index这个下表加上value值
	 */
	private void update(int index, int value) {
		while (index <= this.len) {
			this.tree[index] += value;
			index += this.lowBit(index);
		}
	}

	/**
	 * 查询前缀和 [1,index] 从1开始
	 */
	private int query(int index) {
		int sum = 0;
		while (index > 0) {
			sum += this.tree[index];
			index -= this.lowBit(index);
		}
		return sum;
	}

	/**
	 * 二进制补码最后一个1表示的值
	 */
	private int lowBit(int index) {
		return index & (-index);
	}

}
