//给定一个整数 n ，返回 可表示为两个 n 位整数乘积的 最大回文整数 。因为答案可能非常大，所以返回它对 1337 取余 。 
//
// 
//
// 示例 1: 
//
// 
//输入：n = 2
//输出：987
//解释：99 x 91 = 9009, 9009 % 1337 = 987
// 
//
// 示例 2: 
//
// 
//输入： n = 1
//输出： 9
// 
//
// 
//
// 提示: 
//
// 
// 1 <= n <= 8 
// 
// Related Topics 数学 👍 51 👎 0


package com.ggox.leetcode.editor.cn;

public class LargestPalindromeProduct {
    public static void main(String[] args) {
        Solution solution = new LargestPalindromeProduct().new Solution();
        System.out.println(solution.largestPalindrome(3));
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int largestPalindrome(int n) {
            if (n == 1) {
                return 9;
            }

            // 87 -> 8778
            int max = (int) Math.pow(10, n) - 1;
            int min = (int) Math.pow(10, n - 1);
            for (int i = max; i >= min; i--) {
                long p = i;
                for (int x = i; x > 0; x /= 10) {
                    p = p * 10 + x % 10;
                }
                for (long x = max; x * x >= p; x--) {
                    if (p % x == 0) {
                        return (int) (p % 1337);
                    }
                }
            }
            return -1;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution2 {
        private boolean isPalindrome(int product) {
            String s = String.valueOf(product);
            int length = s.length();
            for (int i = 0; i < length / 2; i++) {
                if (s.charAt(i) != s.charAt(length - i - 1)) {
                    return false;
                }
            }
            return true;
        }

        public int largestPalindrome(int n) {
            if (n == 1) {
                return 9;
            }
            int max = (int) Math.pow(10, n) - 1;
            int min = (int) Math.pow(10, n - 1);
            long maxProduct = 0;
            for (int i = max; i >= min; i--) {
                for (int j = i; j >= min; j--) {
                    int product = i * j;
                    if (isPalindrome(product)) {
                        maxProduct = Math.max(maxProduct, product);
                    }
                }
            }
            if (maxProduct != 0) {
                return (int) (maxProduct % 1337);
            }
            return -1;
        }
    }
}