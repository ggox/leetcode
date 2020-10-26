//给你一个未排序的整数数组，请你找出其中没有出现的最小的正整数。 
//
// 
//
// 示例 1: 
//
// 输入: [1,2,0]
//输出: 3
// 
//
// 示例 2: 
//
// 输入: [3,4,-1,1]
//输出: 2
// 
//
// 示例 3: 
//
// 输入: [7,8,9,11,12]
//输出: 1
// 
//
// 
//
// 提示： 
//
// 你的算法的时间复杂度应为O(n)，并且只能使用常数级别的额外空间。 
// Related Topics 数组


package com.ggox.leetcode.editor.cn;
public class FirstMissingPositive{
  public static void main(String[] args) {
       Solution solution = new FirstMissingPositive().new Solution();
      System.out.println(solution.firstMissingPositive(new int[] { 1, 4, 3 }));
  }
  //leetcode submit region begin(Prohibit modification and deletion)
class Solution {
    public int firstMissingPositive(int[] nums) {
        int length = nums.length;
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num <= 0) {
                nums[i] = length + 1;
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int num = Math.abs(nums[i]);
            if (num >= 1 && num <= length && nums[num-1] >0) {
                nums[num-1] = -nums[num-1];
            }
        }
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (num > 0) {
                return i + 1;
            }
        }
        return length + 1;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
