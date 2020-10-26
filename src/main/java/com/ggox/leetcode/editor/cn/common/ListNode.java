package com.ggox.leetcode.editor.cn.common;

/**
 * @Author: ggox
 * @Date: 2020/10/10 21:32
 * @Description: TODO
 */
public class ListNode {

	public int val;

	public ListNode next;

	public ListNode(int val) {
		this.val = val;
		this.next = null;
	}

	public ListNode(int val, ListNode next) {
		this.val = val;
		this.next = next;
	}
}
