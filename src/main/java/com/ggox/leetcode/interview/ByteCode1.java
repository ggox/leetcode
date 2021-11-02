package com.ggox.leetcode.interview;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: ggox
 * @date: 2021/3/13 下午7:44
 * @description:
 */
public class ByteCode1 {

	static int left;

	static int right;

	static int len;

	static char[] charArr;

	/**
	 * 题目描述：
	 * <p>
	 * 查找字符串中给定的连续不重复序列
	 * <p>
	 * 给定如下不重复序列和一个字符串，请返回不重复序列连续出现在字符串中第一个字母的位置。
	 * 例如
	 * 给定不重复序列[a，b，c，d]
	 * 字符串 adcebadcb
	 * 则返回4
	 */
	public static void main(String[] args) {
		char[] distinct = new char[] { 'a', 'b', 'b', 'c', 'd', 'd' };
		String target = "adcexaddcbb";
		Map<Character, AtomicInteger> map = new HashMap<>(distinct.length);
		int sum = 0;
		for (char c : distinct) {
			map.computeIfAbsent(c, cc -> new AtomicInteger(0)).incrementAndGet();
			sum++;
		}
		if (sum == 0) {
			System.out.println("-1");
			return;
		}
		charArr = target.toCharArray();
		len = charArr.length;
		left = -1;
		right = -1;
		for (right = 0; right < len; right++) {
			if (map.containsKey(charArr[right])) {
				AtomicInteger count = map.get(charArr[right]);
				while (count.get() == 0) { // 不够了
					left++;
					if (map.containsKey(charArr[left])) {
						map.get(charArr[left]).incrementAndGet();
						sum++;
					}
				}
				count.decrementAndGet();
				sum--;
				if (sum == 0) {
					System.out.println(left + 1);
					return;
				}
			} else {
				while (left < right) {
					left++;
					if (map.containsKey(charArr[left])) {
						map.get(charArr[left]).incrementAndGet();
						sum++;
					}
				}
			}
		}
	}
}
