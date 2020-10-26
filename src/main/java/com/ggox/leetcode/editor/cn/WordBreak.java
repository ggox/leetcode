//给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。 
//
// 说明： 
//
// 
// 拆分时可以重复使用字典中的单词。 
// 你可以假设字典中没有重复的单词。 
// 
//
// 示例 1： 
//
// 输入: s = "leetcode", wordDict = ["leet", "code"]
//输出: true
//解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
// 
//
// 示例 2： 
//
// 输入: s = "applepenapple", wordDict = ["apple", "pen"]
//输出: true
//解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
//     注意你可以重复使用字典中的单词。
// 
//
// 示例 3： 
//
// 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
//输出: false
// 
// Related Topics 动态规划


package com.ggox.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordBreak{
  public static void main(String[] args) {
      Solution solution = new WordBreak().new Solution();
      List<String> list = new ArrayList<>();
      list.add("apple");
      list.add("pen");
      boolean leetcode = solution.wordBreak("applepenapple", list);
      System.out.println(leetcode);
  }
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        // 转成hashset 搜索复杂度 O(1)
        Set<String> set = new HashSet<>(wordDict.size());
        int maxLen = Integer.MIN_VALUE;
        int minLen = Integer.MAX_VALUE;
        for (String str : wordDict) {
            maxLen = Math.max(maxLen, str.length());
            minLen = Math.min(minLen, str.length());
            set.add(str);
        }
        // 字符串的长度
        int length = s.length();
        // 定义状态集合 dp[i] 表示 s[0，i-1]是否符合要求 空字符串为true 即dp[0] = true
        boolean[] dp = new boolean[length + 1];
        dp[0] = true;
        for (int i = minLen - 1; i < length; i++) {
            int maxJ = Math.max(i - maxLen+1, 0);
            for (int j = i; j >= maxJ; j--) {
                String substring = s.substring(j, i + 1);
                dp[i + 1] = dp[j] && set.contains(substring);
                if (dp[i + 1]) {
                    break;
                }
            }
        }
        return dp[length];
    }

}
//leetcode submit region end(Prohibit modification and deletion)

}
