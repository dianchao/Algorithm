package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你一个 m x n 的矩阵，你要把这个矩阵中等于 0 的元素所在的行和列都置 0。
 *
 * 比如说，给你的矩阵 a 是：
 *
 *  1, 2, 3
 *  4, 0, 6
 *  0, 8, 9
 *
 * 这个矩阵中有两个 0，把它们所在的行和列都置 0 后，得到的矩阵是：
 *
 *  0, 0, 3
 *  0, 0, 0
 *  0, 0, 0
 *
 *  分析：
 *  最简单直接的方法是使用两个数组分别存储相应的行和列是否需要置0，
 *  例子：
 *         0    1     2
 *  rows        true  true
 *  cols   true true
 *
 *  判断rows(i) || cols(j)？
 *
 *  空间复杂度可以进一步优化：
 *  考虑到我们可以改变原有矩阵中的数据，所以我们可以把这些信息直接存储在矩阵的第0行第0列中，定义两个变量r和c，表示第0行
 *  和第0列是否需要置为0
 *
 *  r: false
 *  c: true
 */
public class SetZeroInMatrix_50 {
    // Time: O(m*n), Space: O(m+n)
    public void setZeroInMatrix(int[][] a) {
        int m = a.length, n = a[0].length;

        //定义两个数组，分别表示行和列是否需要置0
        boolean[] rows = new boolean[m];
        boolean[] cols = new boolean[n];

        //遍历矩阵
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                //如果a[i][j]等于0，则将第i行和第j列标记置为true
                if (a[i][j] == 0)
                    rows[i] = cols[j] = true;

        //再遍历一次矩阵
        for (int i = 0; i < m; ++i)
            for (int j = 0; j < n; ++j)
                //如果第i行或第j列被标记为true，则将a[i][j]置0。
                if (rows[i] || cols[j])
                    a[i][j] = 0;
    }

    // Time: O(m*n), Space: O(1)
    public void setZeroInMatrixO1(int[][] a) {
        int m = a.length, n = a[0].length;

        //定义两个变量用来表示第0行和第0列是否需要置0的标记
        boolean row0 = false, col0 = false;

        //遍历第0列的元素，如果有一个元素为0，则将col0标记置为true
        for (int i = 0; i < m; ++i)
            if (a[i][0] == 0) col0 = true;

        //遍历第0行的元素，如果有一个元素为0，则将row0标记置为true
        for (int j = 0; j < n; ++j)
            if (a[0][j] == 0) row0 = true;

        //从下标1开始遍历矩阵
        for (int i = 1; i < m; ++i)
            for (int j = 1; j < n; ++j)
                //如果a[i][j]为0，则将a[i][0]和a[0][j]置为0.
                if (a[i][j] == 0)
                    a[i][0] = a[0][j] = 0;

        //再次遍历数组
        for (int i = 1; i < m; ++i)
            for (int j = 1; j < n; ++j)
                //如果a[i][0]或者a[0][j]等于0，则将a[i][j]置为0
                if (a[i][0] == 0 || a[0][j] == 0)
                    a[i][j] = 0;

        //如果row0为true，则将第一行置为0
        if (row0)
            for (int j = 0; j < n; ++j)
                a[0][j] = 0;

        //如果col0为true，则将第一列置为0
        if (col0)
            for (int i = 0; i < m; ++i)
                a[i][0] = 0;
    }

}
