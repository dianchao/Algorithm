package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你两个非空的单链表，它们代表两个非负整数，并且逆序表示。你要将这两个数求和，并将结果以链表形式返回。
 * 你不需要考虑前导 0 这种情况，也就说 3 不会表示成 003 这样子。
 *
 * 比如说给你的两个链接表是：
 *
 * 1 -> 2 -> 3
 * 6 -> 7 -> 8 -> 9
 *
 * 1 -> 2 -> 3 表示的整数是 321，
 * 6 -> 7 -> 8 -> 9 表示的整数是 9876。我们需要输出的是它们求和后的链表：
 * 7 -> 9 -> 1 -> 0 -> 1
 *
 * 思路：假设进位保存在变量carry中，并且初始化为0
 *
 * carry：0   0     0   1     1   0
 *       7 -> 9 -> 1 -> 0 -> 1
 */
public class AddTwoLinkedListNumbers_40 {
    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    // Time: O(max(m, n)), Space: O(max(m, n))
    public ListNode addTwoLinkedListNumbers(ListNode l1, ListNode l2) {
        //定义一个dummy节点，链表头节点
        ListNode dummy = new ListNode(0), p = dummy;
        //定义变量carry表示进位，初始化为0
        int carry = 0;

        //当链表不为空或者进位不为0时
        while (l1 != null || l2 != null || carry != 0) {
            //初始化sum为carry
            int sum = carry;

            //当l1不为空，sum加上l1的值，l1向后移动一个节点
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }

            //当l2不为空，sum加上l2的值，l2向后移动一个节点
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }

            //当前节点的值是sum%10的结果
            p.next = new ListNode(sum % 10);

            //p向后移动一个节点，指向新创建的当前节点
            p = p.next;

            //更新进位carry
            carry = sum / 10;
        }
        return dummy.next;
    }
}
