//给你一棵所有节点为非负值的二叉搜索树，请你计算树中任意两节点的差的绝对值的最小值。 
//
// 
//
// 示例： 
//
// 输入：
//
//   1
//    \
//     3
//    /
//   2
//
//输出：
//1
//
//解释：
//最小绝对差为 1，其中 2 和 1 的差的绝对值为 1（或者 2 和 3）。
// 
//
// 
//
// 提示： 
//
// 
// 树中至少有 2 个节点。 
// 本题与 783 https://leetcode-cn.com/problems/minimum-distance-between-bst-nodes/ 
//相同 
// 
// Related Topics 树 
// 👍 142 👎 0


package com.ggox.leetcode.editor.cn;

import com.ggox.leetcode.editor.cn.common.TreeNode;

public class MinimumAbsoluteDifferenceInBst{
  public static void main(String[] args) {
       Solution solution = new MinimumAbsoluteDifferenceInBst().new Solution();
  }
  //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
class Solution {

    private int minDif = Integer.MAX_VALUE;
    private int preVal = -1;

    public int getMinimumDifference(TreeNode root) {
        dfs(root);
        return minDif;
    }

    private void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        dfs(root.left);
        if (preVal != -1) {
            minDif = Math.min(minDif, root.val - preVal);
        }
        preVal = root.val;
        dfs(root.right);
    }
}
//leetcode submit region end(Prohibit modification and deletion)

}
