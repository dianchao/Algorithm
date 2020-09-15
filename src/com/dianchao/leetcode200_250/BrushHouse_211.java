package com.dianchao.leetcode200_250;

/**
 * 描述
 * 这个题目说的是，你要用红/蓝/绿三种不同的颜色去粉刷 n 个房子，一个房子只能刷成一种颜色，并且相邻的房子不能粉刷相同的颜色。
 *
 * 现在给你一个 nx3 的费用矩阵，表示每个房子刷成红/蓝/绿 3 种颜色对应的费用。你要计算出，粉刷这 n 个房子的最小费用。注意，矩阵中的费用都为正整数。
 *
 * 比如说，给你的费用矩阵 a 是：
 *
 * 8 2 4
 * 5 7 3
 * 9 1 6
 * 4 1 9
 *
 * 对于这个例子，你要将 0 号房子刷成蓝色，将 1 号房子刷成绿色，将 2 号房子刷成蓝色，将 3 号房子刷成红色。
 *
 * 最后得到的最小费用是 2 + 3 + 1 + 4 = 10。
 *
 *    r b g
 * 0  8 2 4
 * 1  5 7 3
 * 2  9 1 6
 * 3  4 1 9
 *
 * 分析：从简单的场景进行分析找到动态规划中的状态，假设只刷0号房子，此时最省钱的方式是把它刷成蓝色，费用是2；
 * 接着加一个房子，只刷0和1号房子，对于1号房子有三种颜色可选，如果刷成红色，费用是5，这是0号房子就不能再刷成
 * 红色，于是在蓝色和绿色中挑选一种便宜的颜色，2和4中最小的费用是2，于是最小费用是5+2=7，类似1号房子如果刷
 * 成蓝色，最小费用为7+4=11，同理如果1号房子刷成绿色，最小费用为3+2=5；再增加1个房子，刷0、1、2号房子，和
 * 刚才类似如果3号房子刷成红色，那么1号房子只能刷成蓝色和绿色，最小费用为9+5=14，同理我们可以计算出粉刷0到2
 * 号房子并且2号房子刷成蓝色的最小费用，粉刷0到2号房子并且2号房子刷成绿色的最小费用，计算这3个费用的最小值
 * 就是刷0-2号房子费用的最小值。
 *
 * 定义状态：d(i,j) 表示粉刷0~i号房子，并未第i号房子使用第j种颜色的最小费用。
 * j的取值只有0/1/2三种，分别表示红/蓝/绿 3种颜色。
 * 递推式：
 * d(i,0) = min(d(i-1,1), d(i-1,2)) + a(i,0)
 * d(i,1) = min(d(i-1,0), d(i-1,2)) + a(i,1)
 * d(i,2) = min(d(i-1,0), d(i-1,1)) + a(i,2)
 * 初始状态：
 * d(0,0) = a(0,0)  d(0,1) = a(0.1) d(0,2) = a(0,2)
 *
 * 目标：求d(n-1,0)、d(n-1,1)、d(n-1,2)中的最小值，即粉刷n个房子的最小花费。
 */
public class BrushHouse_211 {
    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    // Time: O(n), Space: O(n)
    public int minCost(int[][] costs) {
        //边界处理：如果费用矩阵为空或者长度0，直接返回0
        if (costs == null || costs.length == 0) return 0;
        //房子数量
        int n = costs.length;
        //状态数组
        int[][] d = new int[n][3];
        //初始状态赋值
        d[0][0] = costs[0][0];
        d[0][1] = costs[0][1];
        d[0][2] = costs[0][2];

        for (int i = 1; i < n; ++i) {
            d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + costs[i][0];
            d[i][1] = Math.min(d[i-1][0], d[i-1][2]) + costs[i][1];
            d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + costs[i][2];
        }
        return min(d[n-1][0], d[n-1][1], d[n-1][2]);
    }

    // 优化1：把三个初始状态的计算也统一到循环中
    // 修改d(i,j)状态定义：表示粉刷0~i-1号房子，并且第i-1号房子使用第j中颜色的最小费用。这种方式的状态定义
    // 在动态规划题目中是很常见的，这样的好处是，状态数组会多出一个没有意义的状态，而这种状态正好可以充当边界
    // 状态。

    // Time: O(n), Space: O(n)
    public int minCostOpt(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        int[][] d = new int[n+1][3];

        for (int i = 1; i <= n; ++i) {
            d[i][0] = Math.min(d[i-1][1], d[i-1][2]) + costs[i-1][0];
            d[i][1] = Math.min(d[i-1][0], d[i-1][2]) + costs[i-1][1];
            d[i][2] = Math.min(d[i-1][0], d[i-1][1]) + costs[i-1][2];
        }
        return min(d[n][0], d[n][1], d[n][2]);
    }

    // 优化2
    // Time: O(n), Space: O(1)
    public int minCostO1(int[][] costs) {
        if (costs == null || costs.length == 0) return 0;
        int n = costs.length;
        int preR = 0, preB = 0, preG = 0;

        for (int i = 1; i <= n; ++i) {
            int curR = Math.min(preB, preG) + costs[i-1][0];
            int curB = Math.min(preR, preG) + costs[i-1][1];
            int curG = Math.min(preR, preB) + costs[i-1][2];
            preR = curR;
            preB = curB;
            preG = curG;
        }
        return min(preR, preB, preG);
    }
}
