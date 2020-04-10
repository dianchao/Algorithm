package com.dianchao.leetcode;

/**
 * 这个题目说的是，给你两个字符串，你要求出由其中一个字符串转成另一个所需要的最少编辑操作次数。允许的操作有 3 种，分别是：替换一个字符，插入一个字符和删除一个字符。
 *
 * 比如说，给你的两个字符串是 car 和 ba。
 *
 * s1: car
 * s2: ba
 *
 * 你要把 car 转成 ba，需要先把 c 替换成 b，然后再删除 r。总共操作 2 次，因此它们的编辑距离是 2。
 *
 * 思路：
 * 从最简单的case入手
 *          ""  b   a
 *   ""     0   1   2
 *    c     1   1   2
 *    a     2   2   1
 *
 * 假设二维数组是d，两个字符串分别为s和t。d(i,j)表示s(0, i-1)和t(0,j-1)这两个子串的编辑距离，比如d(1,2)表示s(0,0)和t(0,1)
 * 两个子串的编辑距离，即c和ba的编辑距离。
 *
 * 通过上面的case，观察第一行和第一列，发现它们的值是0,1,2,3......这样下去，含义是两个字符串中如果有一个是空串，那么编辑距离就是
 * 把空串插入成相应长度的字符或者把相应字符串删除相应长度的字符，于是有d(i,0)=i, d(0,j)=j
 *
 * d(2,2)表示的字符串ca和ba的编辑距离，由于最后的字符a是相等的不需要操作，所以它们的编辑距离就是c和b的编辑距离，泛化公式如下：
 * s(i-1) == t(i-1) ==> d(i,j) = d(i-1,j-1)
 *
 * d(2,1)表示ca和b的编辑距离，它可以由以下三种情况变换而来：
 * ① c和空串的编辑距离d(1,0)在加上一个替换操作，将a替换为b
 * ② ca和空串的编辑距离d(2,0)再加上一个插入操作，插入b
 * ③ c和b的编辑距离d(1,1)再加上一个删除操作，删除a
 *
 * 泛化公式如下：
 * s(i-1)!=t(i-1)==> d(i,j)=min(d(i-1,j-1), d(i-1,j), d(i, j-1)) + 1
 */
public class EditDistance_30 {
    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    // Time: O(m*n), Space: O(m*n)
    public int editDistance(String s, String t) {
        //处理边界，当s或t为null时，直接返回0
        if (s == null || t == null) return 0;

        //定义二维数组的行和列长度
        int m = s.length() + 1, n = t.length() + 1;
        int[][] d = new int[m][n];

        //初始化二维数组的第一列
        for (int i = 0; i < m; ++i){
            d[i][0] = i;
        }

        //初始化二维数组的第一行
        for (int j = 0; j < n; ++j){
            d[0][j] = j;
        }


        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    d[i][j] = d[i-1][j-1];
                } else {
                    //左边、上边、左上的最小值+1
                    d[i][j] = min(d[i-1][j-1], d[i-1][j], d[i][j-1]) + 1;
                }
            }
        }

        return d[m-1][n-1];
    }


    //注：除非对空间复杂度有特别要求，否则还是使用二维数组求解，代码可读性高。
    // Time: O(m*n), Space: O(n)
    public int editDistance1dArray(String s, String t) {
        if (s == null || t == null) return 0;

        int m = s.length() + 1, n = t.length() + 1;
        int[] d = new int[n];

        for (int j = 0; j < n; ++j)
            d[j] = j;

        for (int i = 1; i < m; ++i) {
            int pre = d[0];
            d[0] = i;
            for (int j = 1; j < n; ++j) {
                int tmp = d[j];
                if (s.charAt(i-1) == t.charAt(j-1)) {
                    d[j] = pre;
                } else {
                    d[j] = min(pre, d[j], d[j-1]) + 1;
                }
                pre = tmp;
            }
        }
        return d[n-1];
    }
}
