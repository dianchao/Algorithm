package com.dianchao.leetcode51_100;

/**
 * 这个题目说的是，给你一个 m x n 的整数矩阵，你要计算从矩阵的左上角到右下角的所有路径中，最小的路径和。路径的移动方向只能是向右或向下。
 *
 * 比如说，给你的矩阵 a 是：
 *
 *  1, 2, 1
 *  8, 4, 1
 * -8, 2, 1
 *
 * 对于这个矩阵，从左上角走到右下角，和最小的一条路径是 1,8,-8,2,1，它的和是 4，因此你需要返回 4。
 *
 * 思路：
 *
 * 定义状态d[i][j]表示从左上角(0,0)走到坐标(i,j)的所有路径中最小路径和，根据定义这个题目求的是d[m-1][n-1]
 *
 * 先看初始情况，
 * 由于(0,0)是起点，所以d[0][0]等于a[0][0]
 * 由于只能向右或者向下，所以第一行的值d[0][j] = d[0][j-1] + a[0][j]; 第一列的值d[i][0] = d[i-1][0] + a[i][0]
 *
 * 对于矩阵中其他的点，既可以从左边走过来也可以从上边走过来，因此最小路径和d[i][j]需要对左边和上边最小路径和求最小值，然后
 * 再加上当前元素值，于是有d[i][j] = min(d[i-1][j], d[i][j-1]) + a[i][j]
 *
 * 有了初始状态和状态转移方程，就可以一步一步求解得到最小路径和。
 */
public class MinSumOfPath_51 {
    // 使用二维数组表示状态
    // Time: O(m*n), Space: O(m*n)
    public int minSumOfPath(int[][] a) {
        int m = a.length, n = a[0].length;
        int[][] d = new int[m][n];

        d[0][0] = a[0][0];
        for (int i = 1; i < m; ++i)
            d[i][0] = d[i-1][0] + a[i][0];
        for (int j = 1; j < n; ++j)
            d[0][j] = d[0][j-1] + a[0][j];

        for (int i = 1; i < m; ++i)
            for (int j = 1; j < n; ++j)
                d[i][j] = Math.min(d[i-1][j], d[i][j-1]) + a[i][j];

        return d[m-1][n-1];
    }

    // 使用一维数组表示状态
    // Time: O(m*n), Space: O(n)
    public int minSumOfPath1dArray(int[][] a) {
        int m = a.length, n = a[0].length;
        int[] d = new int[n];

        d[0] = a[0][0];
        for (int j = 1; j < n; ++j)
            d[j] = d[j-1] + a[0][j];

        //遍历矩阵，从下标1开始
        for (int i = 1; i < m; ++i) {
            //遍历到新的一行时，需要将d[0]加上这一行的第0列的元素，表示从上边走到当前行的行首
            d[0] += a[i][0];
            //接着开始遍历当前行的元素
            for (int j = 1; j < n; ++j) {
                d[j] = Math.min(d[j], d[j-1]) + a[i][j];
            }
        }

        return d[n-1];
    }
}
