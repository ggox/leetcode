//给你两个数组，arr1 和 arr2， 
//
// 
// arr2 中的元素各不相同 
// arr2 中的每个元素都出现在 arr1 中 
// 
//
// 对 arr1 中的元素进行排序，使 arr1 中项的相对顺序和 arr2 中的相对顺序相同。未在 arr2 中出现过的元素需要按照升序放在 arr1 的末
//尾。 
//
// 
//
// 示例： 
//
// 输入：arr1 = [2,3,1,3,2,4,6,7,9,2,19], arr2 = [2,1,4,3,9,6]
//输出：[2,2,2,1,4,3,3,9,6,7,19]
// 
//
// 
//
// 提示： 
//
// 
// arr1.length, arr2.length <= 1000 
// 0 <= arr1[i], arr2[i] <= 1000 
// arr2 中的元素 arr2[i] 各不相同 
// arr2 中的每个元素 arr2[i] 都出现在 arr1 中 
// 
// Related Topics 排序 数组 
// 👍 132 👎 0

package com.ggox.leetcode.editor.cn;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

public class RelativeSortArray {

	public static void main(String[] args) {
		Solution solution = new RelativeSortArray().new Solution();
		int[] arr1 = new int[] { 2, 3, 1, 3, 2, 4, 6, 7, 9, 2, 19 };
        int[] arr2 = new int[] { 2, 1, 4, 3, 9, 6 };
        int[] ints = solution.relativeSortArray(arr1, arr2);
        System.out.println(Arrays.toString(ints));
    }

	//leetcode submit region begin(Prohibit modification and deletion)
	class Solution {

		public int[] relativeSortArray(int[] arr1, int[] arr2) {
			Map<Integer, Integer> map = new HashMap<>(arr2.length);
			for (int i = 0; i < arr2.length; i++) {
				map.put(arr2[i], i);
			}
			Comparator<Integer> specialComparator = (a1, a2) -> {
				Integer v1 = map.get(a1);
				Integer v2 = map.get(a2);
				if (v1 != null && v2 != null) {
					return v1 - v2;
				} else if (v1 == null && v2 != null) {
					return 1;
				} else if (v1 != null) {
					return -1;
				} else {
					return a1 - a2;
				}
			};
			return IntStream.of(arr1).boxed().sorted(specialComparator).mapToInt(Integer::intValue).toArray();
		}
	}
	//leetcode submit region end(Prohibit modification and deletion)

}
