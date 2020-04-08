package com.dianchao.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 这个题目说的是，给你一个单链表，你要判断它是否会形成环，也就是链表的最后一个节点指向了前面一个已经存在的节点。
 *
 * 思路：
 * 解法1：最简单直接的方法是定义一个集合Set，在遍历链表的过程中把访问到的节点加入到集合中，如果后面出现了集合中已经存在的节点，
 * 则可以判断出这个链表中存在环
 *
 * 解法2：不借助于辅助内存空间，对于有环的链表有一个特点：通过的节点的next遍历它，是无法终止的，这样我们定义快慢两个指针遍历
 * 这个链表，快指针一次走2步，慢指针一次走1步，快指针先进入到环里，在慢指针也进入到环以后，就相当于一个追击问题，它们的速度差
 * 为1，所以最后快指针一定能够追上慢指针，指向同一个节点，当这个条件出现时，说明链表存在环。
 */
public class HasCycleList_27 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // Time: O(n), Space: O(n)
    public boolean hasCycleWithHashSet(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        for (ListNode p = head; p != null; p = p.next) {
            if (set.contains(p)) return true;
            set.add(p);
        }
        return false;
    }

    // Time: O(n), Space: O(1)
    public boolean hasCycleWithTwoPointer(ListNode head) {
        //定义快、慢两个指针
        ListNode fast = head, slow = head;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) return true;
        }
        return false;
    }
}
