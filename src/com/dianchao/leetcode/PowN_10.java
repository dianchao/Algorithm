package com.dianchao.leetcode;

/**
 * 这个题目说的是，你要实现一个函数，用它来计算浮点数的 n 次方。
 *
 * 比如说，给你 2 和 11，你要计算出 2 的 11 次方的结果：
 *
 * f(2, 11) = 2^11
 *
 * 1011
 */
public class PowN_10 {
    // Time: O(n), Space: O(1)
    public double pow(double x, int n) {
        double result = 1;
        long N = Math.abs((long)n);

        for (int i = 0; i < N; ++i)
            result *= x;
        return n < 0 ? 1/result : result;
    }

    // Time: O(log(n)), Space: O(1)
    public double powFast(double x, int n) {
        double result = 1;
        long N = Math.abs((long)n);

        while (N != 0) {
            if ((N & 1) == 1) result *= x;
            x *= x;
            N >>= 1;
        }
        return n < 0 ? 1/result : result;
    }
}
