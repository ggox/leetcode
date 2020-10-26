//给定二叉搜索树（BST）的根节点和要插入树中的值，将值插入二叉搜索树。 返回插入后二叉搜索树的根节点。 输入数据保证，新值和原始二叉搜索树中的任意节点值都不
//同。 
//
// 注意，可能存在多种有效的插入方式，只要树在插入后仍保持为二叉搜索树即可。 你可以返回任意有效的结果。 
//
// 
//
// 例如, 
//
// 给定二叉搜索树:
//
//        4
//       / \
//      2   7
//     / \
//    1   3
//
//和 插入的值: 5
// 
//
// 你可以返回这个二叉搜索树: 
//
//          4
//       /   \
//      2     7
//     / \   /
//    1   3 5
// 
//
// 或者这个树也是有效的: 
//
//          5
//       /   \
//      2     7
//     / \   
//    1   3
//         \
//          4
// 
//
// 
//
// 提示： 
//
// 
// 给定的树上的节点数介于 0 和 10^4 之间 
// 每个节点都有一个唯一整数值，取值范围从 0 到 10^8 
// -10^8 <= val <= 10^8 
// 新值和原始二叉搜索树中的任意节点值都不同 
// 
// Related Topics 树 
// 👍 126 👎 0

package com.ggox.leetcode.editor.cn;

public class InsertIntoABinarySearchTree {

	public static void main(String[] args) {
		Solution solution = new InsertIntoABinarySearchTree().new Solution();
	}
	//leetcode submit region begin(Prohibit modification and deletion)

	/**
	 * Definition for a binary tree node.
	 * public class TreeNode {
	 * int val;
	 * TreeNode left;
	 * TreeNode right;
	 * TreeNode() {}
	 * TreeNode(int val) { this.val = val; }
	 * TreeNode(int val, TreeNode left, TreeNode right) {
	 * this.val = val;
	 * this.left = left;
	 * this.right = right;
	 * }
	 * }
	 */
	class Solution {

		public TreeNode insertIntoBST(TreeNode root, int val) {
			if (root == null) {
				return new TreeNode(val);
			}
			TreeNode pos = root;
			while (pos != null) {
				if (val < pos.val) {
					if (pos.left == null) {
						pos.left = new TreeNode(val);
						break;
					} else {
						pos = pos.left;
					}
				} else {
					if (pos.right == null) {
						pos.right = new TreeNode(val);
						break;
					} else {
						pos = pos.right;
					}
				}
			}
			return root;
		}
	}

	//leetcode submit region end(Prohibit modification and deletion)
	static class TreeNode {

		int val;
		TreeNode left;
		TreeNode right;

		TreeNode() {
		}

		TreeNode(int val) {
			this.val = val;
		}

		TreeNode(int val, TreeNode left, TreeNode right) {
			this.val = val;
			this.left = left;
			this.right = right;
		}
	}

}
