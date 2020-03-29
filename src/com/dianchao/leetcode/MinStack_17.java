package com.dianchao.leetcode;

import java.util.Stack;

/**
 * 这个题目说的是，你要实现一个栈，除了提供 push，pop，top 等常用函数，还需要提供一个函数在 O(1) 时间内取得这个栈里的最小元素。
 *
 * 思路：
 * 简单实现方法：使用两个栈，其中一个栈存原始数据，另一个栈存放栈底到当前栈顶所有元素的最小值
 *
 * 另一种实现方式：使用链表实现带有min函数的栈，使用一个整型变量min保存当前最小值，当入栈数字时，当大于当前最小值，那么只需要将入栈的
 * 数据插入到当前链表的头结点即可，无需更新整数min；如果入栈的数字小于等于当前最小值，更新最小值min
 */
public class MinStack_17 {
    class MinStackWithTwoStack {
        //定义两个栈
        //第一个栈用来保存原始数据
        private Stack<Integer> stack = new Stack<>();
        //第二个栈用来保存每个阶段的最小值
        private Stack<Integer> min = new Stack<>();

        public void push(int x) {
            stack.push(x);
            //如果min栈为空或者入栈的值小于等于当前min栈的最小值，则也将值入min栈
            if (min.isEmpty() || x <= getMin()) min.push(x);
        }

        public void pop() {
            //出栈前检查下栈顶元素是否等于最小值，如果是，则需要将min栈表示当前最小值的栈顶元素出栈
            if (stack.peek() == getMin()) min.pop();
            //stack栈顶元素出栈
            stack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min.peek();
        }
    }

    class MinStackWithLinkedList {

        /**
         * 定义链表节点数据结构
         */
        private class Node {
            int val;
            Node next;

            Node(int val, Node next) {
                this.val = val;
                this.next = next;
            }
        }

        //定义链表表头，初始为null
        private Node head = null;

        //定义一个整型变量，初始化为Integer的最大值
        private int min = Integer.MAX_VALUE;


        /**
         * 如果依次入栈：5 6 1
         * 则链表为：1 -> 5 -> 6 -> 5 -> Integer.MAX_VALUE -> null
         * @param x
         */
        public void push(int x) {
            //对于x入栈时，检查它是否小于等于当前最小值min，如果是，则将当前最小值加入链表中，同时更新min值为x
            if (x <= min) {
                head = new Node(min, head);
                min = x;
            }
            //将x加入到当前链表的表头
            head = new Node(x, head);
        }

        public void pop() {
            //如果出栈的栈顶元素等于最小值，则需要将头结点下一个节点的值取出来，更新最小值，然后头结点向后移动两个节点；否则
            //头结点向后移动一个节点即可！
            if (head.val == min) {
                min = head.next.val;
                head = head.next.next;
            } else {
                head = head.next;
            }
        }

        //取栈顶元素
        public int top() {
            return head.val;
        }

        //取最小值
        public int getMin() {
            return min;
        }
    }


}
