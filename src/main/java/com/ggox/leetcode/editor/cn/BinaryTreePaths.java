//给定一个二叉树，返回所有从根节点到叶子节点的路径。 
//
// 说明: 叶子节点是指没有子节点的节点。 
//
// 示例: 
//
// 输入:
//
//   1
// /   \
//2     3
// \
//  5
//
//输出: ["1->2->5", "1->3"]
//
//解释: 所有根节点到叶子节点的路径为: 1->2->5, 1->3 
// Related Topics 树 深度优先搜索 
// 👍 350 👎 0


package com.ggox.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class BinaryTreePaths{
  public static void main(String[] args) {
       Solution solution = new BinaryTreePaths().new Solution();
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

    private List<String> solutions = new ArrayList<>();

    public List<String> binaryTreePaths(TreeNode root) {
        dfs(root, "");
        return solutions;
    }

    private void dfs(TreeNode node,String path){
        if (node == null) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(path).append(node.val);
        if (node.left == null && node.right == null) {
            solutions.add(sb.toString());
            return;
        }
        sb.append("->");
        dfs(node.left, sb.toString());
        dfs(node.right, sb.toString());
    }

}
//leetcode submit region end(Prohibit modification and deletion)

    static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x){
            this.val = x;
        }
    }

}
