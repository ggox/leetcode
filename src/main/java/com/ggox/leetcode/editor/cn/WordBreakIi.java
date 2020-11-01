//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，在字符串中增加空格来构建一个句子，使得句子中所有的单词都在词典中。返回所有这些可能的
//句子。 
//
// 说明： 
//
// 
// 分隔时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入:
//s = "catsanddog"
//wordDict = ["cat", "cats", "and", "sand", "dog"]
//输出:
//[
//  "cats and dog",
//  "cat sand dog"
//]
// 
//
// 示例 2： 
//
// 输入:
//s = "pineapplepenapple"
//wordDict = ["apple", "pen", "applepen", "pine", "pineapple"]
//输出:
//[
//  "pine apple pen apple",
//  "pineapple pen apple",
//  "pine applepen apple"
//]
//解释: 注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入:
//s = "catsandog"
//wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出:
//[]
// 
// Related Topics 动态规划 回溯算法 
// 👍 310 👎 0

package com.ggox.leetcode.editor.cn;

import java.util.*;

public class WordBreakIi {

	public static void main(String[] args) {
		Solution solution = new WordBreakIi().new Solution();
	}

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {

		public List<String> wordBreak(String s, List<String> wordDict) {
			Map<Integer, List<List<String>>> map = new HashMap<>();
			List<List<String>> wordBreaks = backtrack(s, s.length(), new HashSet<>(wordDict), 0, map);
			List<String> breakList = new LinkedList<>();
			for (List<String> wordBreak : wordBreaks) {
				breakList.add(String.join(" ", wordBreak));
			}
			return breakList;
		}

        /**
         * 回溯算法
         *
         * @param s 字符串
         * @param length 字符串长度
         * @param wordSet 单词集合，使用 HashSet 减少时间复杂度
         * @param index 开始索引
         * @param map 缓存结果
         * @return
         */
		public List<List<String>> backtrack(String s, int length, Set<String> wordSet, int index,
				Map<Integer, List<List<String>>> map) {
		    // 判断是否命中缓存，命中则直接返回
			if (!map.containsKey(index)) {
				List<List<String>> wordBreaks = new LinkedList<>();
				if (index == length) {
					wordBreaks.add(new LinkedList<>());
				}
				for (int i = index + 1; i <= length; i++) {
				    // 截取单词
					String word = s.substring(index, i);
					// 判断是否属于满足条件的单词
					if (wordSet.contains(word)) {
						List<List<String>> nextWordBreaks = backtrack(s, length, wordSet, i, map);
						// 将剩余的记过拼上开头的单词
						for (List<String> nextWordBreak : nextWordBreaks) {
							LinkedList<String> wordBreak = new LinkedList<>(nextWordBreak);
							wordBreak.offerFirst(word);
							wordBreaks.add(wordBreak);
						}
					}
				}
				map.put(index, wordBreaks);
			}
			return map.get(index);
		}
	}
	//leetcode submit region end(Prohibit modification and deletion)

}
