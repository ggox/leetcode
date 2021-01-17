//假设有打乱顺序的一群人站成一个队列。 每个人由一个整数对(h, k)表示，其中h是这个人的身高，k是排在这个人前面且身高大于或等于h的人数。 编写一个算法来
//重建这个队列。 
//
// 注意： 
//总人数少于1100人。 
//
// 示例 
//
// 
//输入:
//[[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
//
//输出:
//[[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
// 
// Related Topics 贪心算法 
// 👍 545 👎 0

package com.ggox.leetcode.editor.cn;

import java.util.Arrays;

public class QueueReconstructionByHeight {

	public static void main(String[] args) {
		Solution solution = new QueueReconstructionByHeight().new Solution();
        int[][] people = new int[][] { { 7, 0 }, { 4, 4 }, { 7, 1 }, { 5, 0 }, { 6, 1 }, { 5, 2 } };
        int[][] ints = solution.reconstructQueue(people);
		String string = Arrays.deepToString(ints);
		System.out.println(string);
	}

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {

		public int[][] reconstructQueue(int[][] people) {
			Arrays.sort(people, (person1, person2) -> {
				if (person1[0] != person2[0]) {
					return person1[0] - person2[0];
				} else {
					return person2[1] - person1[1];
				}
			});
			int n = people.length;
			int[][] ans = new int[n][];
			for (int[] person : people) {
				int spaces = person[1] + 1;
				for (int i = 0; i < n; ++i) {
					if (ans[i] == null) {
						--spaces;
						if (spaces == 0) {
							ans[i] = person;
							break;
						}
					}
				}
			}
			return ans;
		}
	}
	//leetcode submit region end(Prohibit modification and deletion)

}
