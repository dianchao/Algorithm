package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你两个递增排序的链表，你要把它们合成一个链表，并且保持递增排序。另外要求，新链表上的节点使用的就是旧的两个链表上的节点，不能创建新节点。
 *
 * 比如说，给你的两个链表 L1 和 L2，分别是：
 *
 * L1: 1 -> 3
 *
 * L2: 2 -> 4 -> 6
 *
 * 合并后的链表就是：
 *
 * 1 -> 2 -> 3 -> 4 -> 6
 *
 * 思路：和其他链表一样为使得头部节点和其他节点处理起来是一致的，定义一个dummy节点，由它指向合并后链表的头节点，同时
 * 定义一个游标p指向合并后链表的当前节点，如果两个链表的当前节点非空，则对比他们的大小，然后游标p指向较小的那个节点。
 */
public class MergeTwoSortedLists_18 {
    /**
     * 链表节点结构
     */
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // Time: O(m+n), Space: O(1)
    public ListNode mergeTwoSortedLists(ListNode l1, ListNode l2) {
        //创建dummy节点和游标p，p一开始指向dummy
        ListNode dummy = new ListNode(0), p = dummy;

        //如果l1和l2都不为空，则循环对比l1和l2对应节点上的值
        while (l1 != null && l2 != null) {
            //如果l1上的数比较小，则p的next指向l1, 然后l1向后移动一个节点
            if (l1.val < l2.val) {
                p.next = l1;
                l1 = l1.next;
            } else {
                //否则p的next域指向l2, 然后l2向后移动一个节点
                p.next = l2;
                l2 = l2.next;
            }

            //更新p指向p的next
            p = p.next;
        }

        //循环结束后，l1或l2非空，此时只要将非空的链表拼接到p的后面即可
        if (l1 != null) p.next = l1;
        if (l2 != null) p.next = l2;

        //返回dummy的next
        return dummy.next;
    }
}
