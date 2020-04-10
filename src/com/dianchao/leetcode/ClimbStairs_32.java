package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你一个 n 阶的楼梯，每次你可以爬 1 阶或 2 阶，你要求出爬完这个楼梯有多少种不同的方法。
 *
 * 比如说，楼梯只有 1 阶，显然你只有一种爬法，就是爬 1 阶，然后到顶。
 *
 * 再比如说，楼梯有 2 阶，那么你可以用两次 1 阶爬到顶，也可以用一次 2 阶爬到顶。共 2 种爬法。
 *
 *  n   f
 *  1   1
 *  2   2
 *
 * 思路：
 * 假设n阶楼梯爬法为f(n), 当我向上走一阶后，剩下爬发是f(n-1);当我向上爬两阶后，剩下爬法是f(n-2), 显然f(n)这两种爬法之和，
 * f(n) = f(n-1) + f(n-2) 本质上是斐波拉契数列
 *
 * 补充一个初始项，当n=0, f=1, 这样：
 *
 * f(n) = 1, n < 2
 *        f(n-1) + f(n-2)
 */
public class ClimbStairs_32 {

    public int climbstairsRecursive(int n) {
        //当n小于等于2时，直接返回1
        if (n < 2) return 1;
        //其他情况返回两数之和
        return climbstairsRecursive(n-1) + climbstairsRecursive(n-2);
    }

    // Time: O(n), Space: O(1)
    public int climbstairsIterative(int n) {
        //初始化前2项
        int first = 1, second = 1;

        for (int i = 1; i < n; ++i) {
            //将前两项之和得到第三项
            int third = first + second;
            //更新前两项值
            first = second;
            second = third;
        }
        //由于在循环内，最后第三项的值赋给了second，因此最后返回second即可
        return second;
    }

}
