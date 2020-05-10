package com.dianchao.leetcode51_100;

/**
 * 这个题目说的是，给你一个单链表和数字 n，你要移除单链表倒数第 n 个节点，然后返回单链表。
 *
 * 比如说，给你的单链表是：
 *
 * 1 -> 2 -> 4 -> 8
 *
 * 给你的数字 n 是 3，单链表中倒数第 3 个节点是 2，移除 2 以后的单链表是：
 *
 * 1 -> 4 -> 8
 *
 * 思路：
 * 使用两个游标p和q，q先走n步，然后p和q一起顺着链表走，直到q走到链表尾节点。
 * 为了统一对链表头节点的处理，这里仍然在头节点前加上dummy节点，初始化游标p和q指向dummy。
 * 最后返回dummy.next即可。
 */
public class RemoveNthFromEnd_52 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // Time: O(k), Space: O(1)
    public ListNode removeNthFromEnd(ListNode head, int n) {
        //定义一个dummy节点，并且让dummy节点指向链表第一个元素节点
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        //定义游标p和q，初始化指向dummy节点
        ListNode p = dummy, q = dummy;

        //让q先走n步：当n大于0，并且q还没有到达尾节点时，让q向后移动
        for (; n > 0 && q.next != null; --n) q = q.next;

        //移动结束后，如果n不等于0，说明n大于链表长度，直接返回dummy.next即可
        if (n != 0) return dummy.next;

        //只要q的next不为null，则不断向后移动p和q
        while (q.next != null) {
            p = p.next;
            q = q.next;
        }

        //循环结束后，跳过要删除的节点
        p.next = p.next.next;

        //返回dummy.next即可
        return dummy.next;
    }

}
