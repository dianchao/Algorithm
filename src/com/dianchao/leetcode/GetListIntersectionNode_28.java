package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你两个单链表，你要找到它们相交的第一个节点。如果两个链表没有相交，则返回空指针。假设链表无环，并且你不能改变它的原始结构。另外要求算法是线性时间复杂度，空间复杂度要求是 O(1)。
 *
 * 比如说，两条链表分别是：
 *
 * A:     1 -> 2
 *               \
 *                6 -> 7 -> null
 *               /
 * B: 3 -> 4 -> 5
 *
 * 你要返回的是 6 这个节点。
 *
 * 思路：
 *
 * 如果这个算法在空间复杂度上没有要求，则遍历两个链表，然后依次将他们的节点入到两个不同的栈，然后依次出队对比，找到第一个不相等的节点，
 * 然后返回的它们的下一个节点即可。空间复杂度为S：O(m+n)，m和n分别为链表长度，不满足题目要求。
 *
 * 解法1：两个链表各自遍历一次后可以得到他们各自的长度，然后使用游标p、q分别指向两个链表，游标q指向较长的链表，让它先移动两个链表
 * 长度之差，然后p和q再一次向前移动，当他们相等时，则返回他们指向的节点即可。
 *
 *
 */
public class GetListIntersectionNode_28 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // Time: O(m+n), Space: O(1)
    public ListNode getIntersectionNodeWithoutLen(ListNode headA, ListNode headB) {
        //处理边界
        if (headA == null || headB == null) return null;

        //定义两个游标分别指向p和q
        ListNode p = headA, q = headB;

        //当p和q不相等时，如果p为空则跳转到另一个链表的表头，否则p向前移动一个节点
        //同样类似的方式处理游标q。
        //p指向null q指向7，此时让p指向B链表头部
        //p指向4，q指向null，此时让q指向A链表头部
        //此时p、q一起向前移动，当p==q时，则此节点是相交节点
        while (p != q) {
            p = p == null ? headB : p.next;
            q = q == null ? headA : q.next;
        }
        return p;
    }

    // Time: O(m+n), Space: O(1)
    public ListNode getIntersectionNodeWithLen(ListNode headA, ListNode headB) {
        //初始化两个链表长度都为0
        int lenA = 0, lenB = 0;
        //计算链表长度
        for (ListNode p = headA; p != null; p = p.next, ++lenA);
        for (ListNode p = headB; p != null; p = p.next, ++lenB);

        //定义游标p和q，分别指向链表A和链表B
        ListNode p = headA, q = headB;

        //如果链表A长度大于链表B，则让p移动链表长度之差，否则让q移动链表长度之差
        if (lenA > lenB)
            for (int i = 0; i < lenA - lenB; ++i) p = p.next;
        else
            for (int i = 0; i < lenB - lenA; ++i) q = q.next;

        //接下来只要p和q不相等则一起移动
        while (p != q) {
            p = p.next;
            q = q.next;
        }
        return p;
    }
}
