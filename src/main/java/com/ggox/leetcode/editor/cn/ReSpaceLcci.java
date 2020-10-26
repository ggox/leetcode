//哦，不！你不小心把一个长篇文章中的空格、标点都删掉了，并且大写也弄成了小写。像句子"I reset the computer. It still didn’
//t boot!"已经变成了"iresetthecomputeritstilldidntboot"。在处理标点符号和大小写之前，你得先把它断成词语。当然了，你有一
//本厚厚的词典dictionary，不过，有些词没在词典里。假设文章用sentence表示，设计一个算法，把文章断开，要求未识别的字符最少，返回未识别的字符数。 
//
//
// 注意：本题相对原题稍作改动，只需返回未识别的字符数 
//
// 
//
// 示例： 
//
// 输入：
//dictionary = ["looked","just","like","her","brother"]
//sentence = "jesslookedjustliketimherbrother"
//输出： 7
//解释： 断句后为"jess looked just like tim her brother"，共7个未识别字符。
// 
//
// 提示： 
//
// 
// 0 <= len(sentence) <= 1000 
// dictionary中总字符数不超过 150000。 
// 你可以认为dictionary和sentence中只包含小写字母。 
// 
// Related Topics 记忆化 字符串


package com.ggox.leetcode.editor.cn;

import java.util.*;

public class ReSpaceLcci{
  public static void main(String[] args) {
      Solution solution = new ReSpaceLcci().new Solution();
      String[] dictionary = { "qowhxw", "gobaagbtt", "boh", "eaqr", "ghahqhqungoafnahwwhav", "w", "nvqx", "obqhaxha", "aew" };
      String sentence = "nvqxonnvqx";
      int respace = solution.respace(dictionary, sentence);
      System.out.println(respace);
  }
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {

      public int respace(String[] dictionary, String sentence) {
          if (dictionary.length == 0) {
              return sentence.length();
          }
          // 初始化trie树
          Trie root = new Trie();
          for (String s : dictionary) {
              root.insert(s);
          }
          // dp[i]表示前i个字符的结果
          int[] dp = new int[sentence.length() + 1];
          Arrays.fill(dp, Integer.MAX_VALUE);
          dp[0] = 0;
          for (int i = 1; i <= sentence.length(); i++) {
              // 最坏情况
              dp[i] = dp[i - 1] + 1;
              // 遍历其他可能的情况
              for (int j = i; j >= 1; j--) {
                  String reverseWord = sentence.substring(j - 1, i);
                  int exists = root.exists(reverseWord);
                  if (exists == -1) {
                      break;
                  }
                  if (exists == 1) {
                      dp[i] = Math.min(dp[i], dp[j - 1]);
                  }
                  // 0最小 直接返回
                  if (dp[i] == 0) {
                      break;
                  }
              }
          }
          return dp[sentence.length()];
      }

      class Trie {
          Map<Character,Trie> trieMap;
          boolean isEnd;

          public Trie() {
              this.trieMap = new HashMap<>();
              this.isEnd = false;
          }

          public void insert(String s) {
              Trie _trie = this;
              for (int i = s.length() - 1; i >= 0; i--) {
                  _trie = _trie.trieMap.computeIfAbsent(s.charAt(i), key -> new Trie());
              }
              _trie.isEnd = true;
          }

          // -1 不存在 0 有前缀但不是单词 1 单词
          public int exists(String reverseWord) {
              int len = reverseWord.length();
              Trie trie = this;
              for (int i = len - 1; i >= 0; i--) {
                  trie = trie.trieMap.get(reverseWord.charAt(i));
                  if (trie == null) {
                      return -1;
                  }
              }
              return trie.isEnd ? 1 : 0;
          }

      }
}
//leetcode submit region end(Prohibit modification and deletion)

}
