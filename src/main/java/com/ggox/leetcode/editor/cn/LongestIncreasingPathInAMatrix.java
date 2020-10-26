//给定一个整数矩阵，找出最长递增路径的长度。 
//
// 对于每个单元格，你可以往上，下，左，右四个方向移动。 你不能在对角线方向上移动或移动到边界外（即不允许环绕）。 
//
// 示例 1: 
//
// 输入: nums = 
//[
//  [9,9,4],
//  [6,6,8],
//  [2,1,1]
//] 
//输出: 4 
//解释: 最长递增路径为 [1, 2, 6, 9]。 
//
// 示例 2: 
//
// 输入: nums = 
//[
//  [3,4,5],
//  [3,2,6],
//  [2,2,1]
//] 
//输出: 4 
//解释: 最长递增路径是 [3, 4, 5, 6]。注意不允许在对角线方向上移动。
// 
// Related Topics 深度优先搜索 拓扑排序 记忆化

package com.ggox.leetcode.editor.cn;

public class LongestIncreasingPathInAMatrix {

	public static void main(String[] args) {
		Solution solution = new LongestIncreasingPathInAMatrix().new Solution();
	}

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {

		int rows;
		int cols;

		public int longestIncreasingPath(int[][] matrix) {
			// 特殊判定
			if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
				return 0;
			}
			rows = matrix.length;
			cols = matrix[0].length;
			// 缓存结果
			int[][] resCache = new int[rows][cols];
			//开始查找
			int result = 0;
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < cols; j++) {
					result = Math.max(result, dfs(matrix, i, j, resCache));
				}
			}
			return result;
		}

		private int dfs(int[][] matrix, int i, int j, int[][] resCache) {
			if (resCache[i][j] != 0) {
				return resCache[i][j];
			}
			resCache[i][j]++;
			int[][] dirs = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
			for (int[] dir : dirs) {
				int row = i + dir[0];
				int col = j + dir[1];
				// 不能越界
				if (row >= 0 && row < rows && col >= 0 && col < cols) {
					// 递增要求
					if (matrix[row][col] > matrix[i][j]) {
						resCache[i][j] = Math.max(resCache[i][j], dfs(matrix, row, col, resCache) + 1);
					}
				}
			}
			return resCache[i][j];
		}
	}
	//leetcode submit region end(Prohibit modification and deletion)

}
