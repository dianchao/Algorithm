package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你两个整数，在不使用 +/- 这两个运算符的前提下，求它们的和。
 */
public class TwoNumberSum_5 {
    public static void main(String[] args) {
        int a = 9;
        int b = 11;
        //递归
        System.out.println(getSumRecursive(a, b));

        //迭代
        System.out.println(getSumIterative(a, b));
    }

    private static int getSumRecursive(int a, int b) {
        return b == 0 ? a : getSumRecursive(a ^ b, (a & b) << 1);
    }

    // Time: O(m), Space: O(1)
    private static int getSumIterative(int a, int b) {
        while (b != 0) {
            int sum = a ^ b;
            int carry = (a & b) << 1;
            a = sum;
            b = carry;
        }
        return a;
    }
}
