//编写代码，移除未排序链表中的重复节点。保留最开始出现的节点。 
//
// 示例1: 
//
// 
// 输入：[1, 2, 3, 3, 2, 1]
// 输出：[1, 2, 3]
// 
//
// 示例2: 
//
// 
// 输入：[1, 1, 1, 1, 2]
// 输出：[1, 2]
// 
//
// 提示： 
//
// 
// 链表长度在[0, 20000]范围内。 
// 链表元素在[0, 20000]范围内。 
// 
//
// 进阶： 
//
// 如果不得使用临时缓冲区，该怎么解决？ 
// Related Topics 链表


package com.ggox.leetcode.editor.cn;

import java.util.HashSet;

public class RemoveDuplicateNodeLcci{
  public static void main(String[] args) {
      Solution solution = new RemoveDuplicateNodeLcci().new Solution();
      ListNode listNode = generateList(new int[] { 1, 2, 3, 3, 2, 1 });
      System.out.println(solution.removeDuplicateNodes(listNode));
  }
  //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode(int x) { val = x; }
 * }
 */
class Solution {
    public ListNode removeDuplicateNodes(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode result = head;
        HashSet<Integer> set = new HashSet<>();
        while (head.next != null) {
            set.add(head.val);
            // 如果下一个节点重复了则移除
            while (head.next != null && set.contains(head.next.val)) {
                head.next = head.next.next;
            }
            // 下下个节点不为null,则连起来
            if (head.next != null) {
                head = head.next;
            }
        }
        return result;
    }
}
//leetcode submit region end(Prohibit modification and deletion)

    static ListNode generateList(int[] source) {
        ListNode head = null;
        for (int i = source.length - 1; i >= 0; i--) {
            ListNode now = new ListNode(source[i]);
            now.next = head;
            head = now;
        }
        return head;
    }
    static class ListNode {

        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }

        @Override
        public String toString() {
            ListNode pointer = this;
            StringBuilder sb = new StringBuilder();
            while (pointer != null) {
                sb.append(pointer.val + "->");
                pointer = pointer.next;
            }
            if (sb.length() > 0) {
                return sb.substring(0, sb.length() - 2);
            }
            return "";
        }
    }
}
