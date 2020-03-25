package com.dianchao.leetcode;

import java.util.Stack;

/**
 * 这个题目说的是，给你一个单链表表示的数，你要判断它是不是一个回文数字。回文数字就是正着读和反着读都相同的数字。
 *
 * 比如说，给你的链表是：
 *
 * 4 -> 2
 *
 * 它表示 42，反过来是 24，不是一个回文数字，因此你要返回 false。
 *
 * 再比如说，给你的链表是：
 *
 * 4 -> 2 -> 2 -> 4
 *
 * 它表示 4224，反过来也是 4224，它是一个回文数字，因此你要返回 true。
 *
 * 思路：
 * 最简单的方法是创建一个辅助栈，然后将链表中的所有节点数字入栈，然后遍历单链表，同时将栈中的元素依次出栈，然后依次比较数值
 *
 * 不借助辅助栈如何实现？ 反转一半的单链表, 比如:
 * 4     2       2       4
 *       pre     cur
 * 4     2       1       2    4
 *       pre     cur
 *
 */
public class IsPalindromeList_14 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // Time: O(n), Space: O(n)
    public boolean isPalindromeUsingStack(ListNode head) {
        //定义一个存放整数的辅助栈
        Stack<Integer> s = new Stack<>();

        //遍历链表将所有元素入栈
        for (ListNode p = head; p != null; p = p.next)
            s.push(p.val);

        //再次遍历链表
        for (ListNode p = head; p != null; p = p.next)
            //比较链表当前和栈顶元素，如果不相等时说明不是回文数字
            if (p.val != s.pop())
                return false;

        //循环结束，如果没有提前结束，则表示当前栈是回文数字
        return true;
    }

    /**
     * 反转一半链表
     */
    // Time: O(n), Space: O(1)
    public boolean isPalindromeReverseList(ListNode head) {
        //初始化链表长度为0
        int len = 0;

        //计算链表长度
        for (ListNode p = head; p != null; p = p.next, ++len);

        // reverse half list
        ListNode cur = head, pre = null;
        for (int i = 0; i < len / 2; ++i) {
            //由于cur改变指向后，链表就断了，因此需要提前保存cur的next域指向的节点
            ListNode next = cur.next;
            //将cur指向它的前一个节点pre
            cur.next = pre;
            //更新pre，将pre移动到cur位置
            pre = cur;
            //更新cur，将cur移动到next位置
            cur = next;
        }

        //判断链表长度是否是奇数，如果是，则将cur向下移动一个位置
        if (len % 2 == 1) cur = cur.next;

        //将pre和cur游标各自向两端移动
        for (; pre != null && cur != null; pre = pre.next, cur = cur.next) {
            //当pre和cur指向的值不等时，返回false
            if (pre.val != cur.val) return false;
        }

        //如果循环过程中没有提前结束，则返回true
        return true;
    }
}

