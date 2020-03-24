package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你一个单链表，你需要反转它，然后返回。
 *
 * 比如说给你的单链表是：
 *
 * 1 -> 2 -> 3 -> 4 -> 5 -> null
 *
 * 你要返回的反转后的链表是：
 *
 * 5 -> 4 -> 3 -> 2 -> 1 -> null
 */
public class ReverseList_9 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 思路：
     * 为了反转链表需要cur节点的next域指向pre节点，但是需要注意：在做这一步之前我们需要首先保存一下当前节点的next的指向，
     * 否则一旦修改了next的指向，当前链表就断开了。然后让cur节点的next域指向pre节点，最后更新pre节点和cur节点。
     */
    // Time: O(n), Space: O(1)
    public ListNode reverseList(ListNode head) {
        //定义两个变量cur和pre，cur表示指向当前结点，pre表示指向当前节点的前一个节点
        ListNode cur = head, pre = null;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }
        return pre;
    }
}
