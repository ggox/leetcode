//给定三个字符串 s1, s2, s3, 验证 s3 是否是由 s1 和 s2 交错组成的。 
//
// 示例 1: 
//
// 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
//输出: true
// 
//
// 示例 2: 
//
// 输入: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
//输出: false 
// Related Topics 字符串 动态规划

package com.ggox.leetcode.editor.cn;

import jdk.nashorn.internal.ir.ReturnNode;

public class InterleavingString {

	public static void main(String[] args) {
		//      解答失败: 测试用例:"aabcc" "dbbca" "aadbbbaccc" 测试结果:true 期望结果:false stdout:
		Solution solution = new InterleavingString().new Solution();
        boolean interleave = solution.isInterleave("a", "", "c");
        System.out.println(interleave);

    }

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {

		public boolean isInterleave(String s1, String s2, String s3) {
			// 动态规划
			// dp(i,j) 指的是 s1的前i个元素和s2的前j个元素是否可以组成 s3的前（i+j）个元素
			// dp(i,j) = dp(i-1,j)
			if ((s1.length() + s2.length()) != s3.length()) {
				return false;
			}
			int len1 = s1.length();
			int len2 = s2.length();
            boolean[] f = new boolean[len2 + 1];
            f[0] = true;
//			boolean[][] dp = new boolean[len1 + 1][len2 + 1];
//			dp[0][0] = true;
			for (int i = 0; i <= len1; i++) {
				for (int j = 0; j <= len2; j++) {
				    if(i == 0 && j == 0){
                        continue;
                    }
                    boolean res = false;
					if (i > 0 && s1.charAt(i - 1) == s3.charAt(i + j - 1)) {
//						dp[i][j] |= dp[i - 1][j];
                        res = f[j];
					}
					if (j > 0 && s2.charAt(j - 1) == s3.charAt(i + j - 1)) {
                        res = res || f[j-1];
//						dp[i][j] |= dp[i][j - 1];
					}
                    f[j] = res;
				}
			}
            return f[len2];
		}
	}
	//leetcode submit region end(Prohibit modification and deletion)

}
